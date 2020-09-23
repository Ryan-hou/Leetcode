package com.github.ryan.data_structure.concurrent;

import com.github.ryan.data_structure.concurrent.executor.Executors;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.LockSupport;

/**
 * A cancellable asynchronous computation.
 * This class provides a base implementation of Future, with methods
 * to start and cancel a computation, query to see if the computation
 * is complete, and retrieve the result of the computation.
 * The result can only be retrieved when the computation has completed;
 * the get methods will block if the computation has not yet completed.
 *
 * A FutureTask can be used to wrap a Callable or Runnable object.
 * Because FutureTask implements Runnable, a FutureTask can be submitted
 * to an Executor for execution.
 *
 * @param <V> The result type returned by this FutureTask's get methods
 */
public class FutureTask<V> implements RunnableFuture<V> {


    /**
     * The run state of this task, initially NEW. The run state
     * transitions to a terminal state only in methods set,
     * setException, and cancel. During completion, state may take
     * on transient values of COMPLETING (while outcome is being set)
     * or INTERRUPTING (only while interrupting the runner to satisfy
     * a cancel(true). Transitions from these intermediate to final
     * states use cheaper ordered/lazy writes because values are unique
     * can cannot be future modified.)
     *
     * Possible state transitions:
     * NEW -> COMPLETING -> NORMAL
     * NEW -> COMPLETING -> EXCEPTIONAL
     * NEW -> CANCELLED
     * NEW -> INTERRUPTING -> INTERRUPTED
     */
    private volatile int state;
    private static final int NEW = 0;
    private static final int COMPLETING = 1;
    private static final int NORMAL = 2;
    private static final int EXCEPTIONAL = 3;
    private static final int CANCELLED = 4;
    private static final int INTERRUPTING = 5;
    private static final int INTERRUPTED = 6;

    // The underlying callable; nulled out after running
    private Callable<V> callable;
    // The result to return or exception to throw from get()
    private Object outcome;
    // The thread running the callable; CASed during run()
    private volatile Thread runner;
    // Treiber stack of waiting threads(参见ConcurrentStack)
    private volatile WaitNode waiters;


    /**
     * Simple linked list nodes to record waiting threads
     * in a Treiber stack. See other classes such as Phaser
     * and SynchronousQueue for more detailed explanation.
     */
    private static final class WaitNode {
        volatile Thread thread;
        volatile WaitNode next;
        WaitNode() {
            this.thread = Thread.currentThread();
        }
    }

    /**
     * Returns result or throw exception for completed task.
     *
     * @param s completed state value
     */
    private V report(int s) throws ExecutionException {
        Object x = outcome;
        if (s == NORMAL)
            return (V) x;
        if (s >= CANCELLED) {
            throw new CancellationException();
        }
        throw new ExecutionException((Throwable) x);
    }

    /**
     * Created a FutureTask that will, upon running, execute the
     * given Callable.
     *
     * @param callable the callable task
     * @throws NullPointerException if the callable is null
     */
    public FutureTask(Callable<V> callable) {
        if (callable == null) {
            throw new NullPointerException();
        }
        this.callable = callable;
        this.state = NEW; // ensure visibility of callable
    }

    /**
     * Creates a FutureTask that will, upon running, execute the
     * given Runnable, and arrange that get will return the
     * given result on successful completion.
     *
     * @param runnable the runnable task
     * @param result the result to return on successful completion.
     * if you don't need a particular result, consider using
     * constructions of the  form:
     *   Future<?> f = new FutureTask<Void>(runnable, null)
     * @throws NullPointerException if the runnable is null
     */
    public FutureTask(Runnable runnable, V result) {
        this.callable = Executors.callable(runnable, result);
        this.state = NEW; // ensure visibility of callable
    }

    @Override
    public void run() {
        if (state != NEW ||
                !UNSAFE.compareAndSwapObject(this, runnerOffset, null, Thread.currentThread())) {
            return;
        }
        // CAS 保证只有一个线程可以执行下面代码逻辑

        try {
            Callable<V> c = callable;
            if (c != null && state == NEW) {
                V result;
                boolean ran;
                try {
                    result = callable.call();
                    ran = true;
                } catch (Throwable ex) {
                    result = null;
                    ran = false;
                    setException(ex);
                }
                if (ran) {
                    set(result);
                }
            }
        } finally {
            // runner must be non-null until state is settled to
            // prevent concurrent calls to run()
            runner = null;
            // ...
        }
    }

    /**
     * Sets the result of this future to the given value unless
     * this future has already been set or has been cancelled.
     *
     * This method is invoked internally by the run method
     * upon successful completion of the computation.
     * @param v the value
     */
    protected void set(V v) {
        if (UNSAFE.compareAndSwapInt(this, stateOffset, NEW, COMPLETING)) {
            outcome = v;
            UNSAFE.putOrderedInt(this, stateOffset, NORMAL); // final state
            finishCompletion();
        }
    }

    /**
     * Causes this future to report an ExecutionException
     * with the given throwable as its cause, unless this
     * future has already been set or has been cancelled.
     *
     * This method is invoked internally by the run method
     * upon failure of the computation.
     *
     * @param t the cause of failure
     */
    protected void setException(Throwable t) {
        if (UNSAFE.compareAndSwapInt(this, stateOffset, NEW, COMPLETING)) {
            outcome = t;
            UNSAFE.putOrderedInt(this, stateOffset, EXCEPTIONAL); // final state
            finishCompletion();
        }
    }

    /**
     * Removes and signals all waiting threads, invokes done(),
     * and nulls out callable.
     */
    private void finishCompletion() {
        // assert state > COMPUTING
        for (WaitNode q; (q = waiters) != null;) {
            if (UNSAFE.compareAndSwapObject(this, waitersOffset, q, null)) {
                for (;;) {
                    Thread t = q.thread;
                    if (t != null) {
                        q.thread = null;
                        LockSupport.unpark(t);
                    }
                    WaitNode next = q.next;
                    if (next == null) {
                        break;
                    }
                    q.next = null; // unlink to help gc
                    q = next;
                }
            }
        }

        callable = null; // to reduce footprint
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        int s = state;
        if (s <= COMPLETING) {
            s = awaitDone(false, 0L);
        }
        return report(s);
    }

    /**
     * Awaits completion or aborts on interrupt or timeout.
     *
     * @param timed true if use timed waits
     * @param nanos time to wait, if timed
     * @return state upon completion
     */
    private int awaitDone(boolean timed, long nanos) throws InterruptedException {
        final long deadline = timed ? System.nanoTime() + nanos : 0L;
        WaitNode q = null;
        boolean queued = false;
        for (;;) {
            if (Thread.interrupted()) {
               // removeWaiter(q);
                throw new InterruptedException();
            }

            int s = state;
            if (s > COMPLETING) {
                if (q != null)
                    q.thread = null;
                return s;
            }
            else if (s == COMPLETING) // cannot time out yet
                Thread.yield();
            else if (q == null)
                q = new WaitNode();
            else if (!queued)
                queued = UNSAFE.compareAndSwapObject(this, waitersOffset,
                       q.next = waiters, q);
            else if (timed) {
                nanos = deadline - System.nanoTime();
                if (nanos <= 0) {
                    // removeWaiter(q)
                    return state;
                }
                LockSupport.parkNanos(this, nanos);
            }
            else
                LockSupport.park(this);
        }
    }

    // Unsafe mechanics
    private static final sun.misc.Unsafe UNSAFE;
    private static final long stateOffset;
    private static final long runnerOffset;
    private static final long waitersOffset;
    static {
        try {
            UNSAFE = sun.misc.Unsafe.getUnsafe();
            Class<?> k = FutureTask.class;
            stateOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("state"));
            runnerOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("runner"));
            waitersOffset = UNSAFE.objectFieldOffset(k.getDeclaredField("waiters"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
