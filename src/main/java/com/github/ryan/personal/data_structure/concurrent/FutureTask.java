package com.github.ryan.personal.data_structure.concurrent;

import java.util.concurrent.ExecutionException;

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

    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        return null;
    }
}
