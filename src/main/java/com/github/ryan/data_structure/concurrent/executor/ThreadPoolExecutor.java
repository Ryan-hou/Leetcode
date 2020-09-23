package com.github.ryan.data_structure.concurrent.executor;



import java.util.HashSet;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import com.github.ryan.data_structure.concurrent.Runnable;

/**
 * An ExecutorService that executes each submitted task using one
 * of possibly several pooled threads.
 *
 * Thread pools address two different problems: they usually provide
 * improved performance when executing large numbers of asynchronous
 * tasks, due to reduced per-task invocation overhead, and they provide
 * a means of bounding and managing the resources, including threads,
 * consumed when executing a collection of tasks.
 * Each ThreadPoolExecutor also maintains some basic statistics, such
 * as the number of completed tasks.
 *
 * To be useful across a wide range of contexts, this class provides
 * many adjustable parameters and extensibility hooks.
 * Use the following guide when manually configuring and turning this
 * class:
 *
 * Core and maximum pool size:
 * A ThreadPoolExecutor will automatically adjust the pool size (see #getPoolSize)
 * according to the bounds set by corePoolSize and maximumPoolSize.
 *
 * When a new task is submitted in method #execute(Runnable),
 * and fewer than corePoolSize threads are running, a new thread is
 * created to handle the request, even if other workers threads are
 * idle. If there are more than corePoolSize but less than maximumPoolSize
 * threads running, a new thread will be created only if the queue if full.
 * By setting corePoolSize and maximumPoolSize the same, you create a
 * fixed-size thread pool. By setting maximumPoolSize to an essentially
 * unbounded value such as Integer.MAX_VALUE, you allow the pool to accommodate
 * an arbitrary number of concurrent tasks. Most typically, core and maximum pool
 * sizes are set only upon construction, but they may also be changed
 * dynamically using #setCorePoolSize and #setMaximumPoolSize.
 *
 * On-demand construction:
 * By default, even core threads are initially created and started
 * only when new tasks arrive, but this can be overridden dynamically
 * using method #prestartCoreThead or #prestartAllCoreThreads. You probably
 * want to prestart threads if you construct the pool with a non-empty
 * queue.
 *
 * Creating new threads:
 * New threads are created using a ThreadFactory. If not otherwise
 * specified, a Executors#defaultThreadFactory is used, that creates threads
 * to all be in the same ThreadGroup and with the same {@code NORM_PRIORITY}
 * priority and non-daemon status. By supplying a different ThreadFactory, you
 * can alter the thread's name, thread group, priority, daemon status, etc. If
 * a ThreadFactory fails to create a thread when asked by returning
 * null from newThread, the executor will continue, but might not be able to
 * execute any tasks. Threads should posses the "modifyThread" {@code RuntimePermission}.
 * If workers threads or other threads using the pool do not posses this
 * permission, service may be degraded: configuration changes may not
 * take effect in a timely manner, and a shutdown pool may remain in a
 * state in which termination is possible but not completed.
 *
 * Keep-alive times:
 * If the pool currently has more than corePoolSize threads,
 * excess threads will be terminated if they have been idle for
 * more than teh keepAliveTime (see #getKeepAliveTime(TimeUnit))
 * This provides a means of reducing resource consumption when the
 * pool is not being actively used. If the pool becomes more active
 * later, new threads will be constructed. This parameter can
 * also be changed dynamically using method #setKeepAliveTime(long, TimeUnit).
 * Using a value of Long.MAX_VALUE TimeUnit#NANOSECONDS effectively
 * disables idle threads from ever terminating prior to shut down.
 * By default, the keep-alive policy applies only when there are
 * more than corePoolSize threads. But method #allowCoreThreadTimeOut(boolean)
 * can be used to apply this time-out policy to core threads as well,
 * so long as the keepAliveTime value is non-zero.
 *
 * Queuing:
 * Any BlockingQueue may be used to transfer and hold submitted tasks.
 * The use of this queue interacts with pool sizing:
 *
 * If fewer than corePoolSize threads are running, the Executor
 * always prefers adding a new thread rather than queuing.
 * If corePoolSize or more threads are running, the Executor always
 * prefers queuing a request rather than adding a new thread.
 * If a request cannot be queued, a new thread is created unless
 * this would exceed maximumPoolSize, in which case, the task will be
 * reject.
 *
 * There are three general strategies for queuing:
 * Direct handoffs:
 * A good default choice for a work queue is a SynchronousQueue that hands
 * off tasks to threads without otherwise holding them. Here, an attempt to
 * queue a task will fail if no threads are immediately available to run
 * it, so a new thread will be constructed. This policy avoids lockups when
 * handling sets of requests that might have internal dependencies.
 * Direct handoffs generally require unbounded maximumPoolSize to avoid
 * rejection of new submitted tasks. This in turn admit the possibility
 * of unbounded thread growth when commands continue to arrive on average
 * faster than they can be processed.
 *
 * Unbounded queues:
 * Using an unbounded queue (for example a LinkedBlockingQueue without a predefined
 * capacity) will cause new tasks to wait in the queue when all corePoolSize threads
 * are busy. Thus, no more than corePoolSize threads will ever be created.
 * (And the value of the maximumPoolSize therefore doesn't have any effect.) This
 * may be appropriate when each task is completely independent of others, so tasks
 * cannot affect each others execution; for example, in a web page server.
 * While this style of queuing can be useful in smoothing out transient bursts of
 * requests, it admits the possibility of unbounded work queue growth when commands
 * continue to arrive on average faster than they can be processed.
 *
 * Bounded queues:
 * A bounded queue (for example, an ArrayBlockingQueue) helps prevent resource exhaustion
 * when used with finite maximumPoolSize, but can be more difficult to tune and control.
 * Queue sizes and maximum pool sizes may be traded off for each other: Using
 * large queues and small pools minimizes CPU usage, OS resource, and context-switching
 * overhead, but can lead to artificially low throughput. If tasks frequently block (for
 * example if they are I/O bound), a system may be able to schedule time for more
 * threads than you otherwise allow. Use of small queues generally requires
 * larger pool sizes, which keeps CPUs busier but may encounter unacceptable scheduling
 * overhead, which also decreases throughput.
 *
 *
 * Rejected tasks:
 * New tasks submitted in method #execute(Runnable) will be rejected when the Executor
 * has been shut down, and also when the Executor uses finite bounds for both maximum
 * threads and work queue capacity, and is saturated. In either case, the execute method
 * invokes the RejectedExecutionHandler#rejectedExecution(Runnable, ThreadPoolExecutor) method
 * of its RejectedExecutionHandler.
 *
 * It is possible to define and use other kinds of RejectedExecutionHandler classes. Doing
 * so requires some care especially when policies are designed to work only under
 * particular capacity or queuing policies.
 *
 * Hook methods:
 * This class provides {@code protected} overridable #beforeExecute(Thread, Runnable) and
 * afterExecute(Runnable, Throwable) methods that are called before and after execution of
 * each task. These can be used to manipulate the execution environment; for example,
 * reinitializing ThreadLocals, gathering statistics, or adding log entries.
 * Additionally, method #terminated can be overridden to perform any special processing
 * that needs to be done once the Executor has fully terminated.
 * If hook or callback methods throw exceptions, internal workers threads may in turn fail
 * and abruptly terminate.
 *
 * Queue maintenance:
 * Method #getQueue allows access to the work queue for purposes of monitoring and debugging.
 * Use of this method for any other purpose is strongly discouraged. Two supplied methods,
 * #remove(Runnable) and #purge are available to assist in storage reclamation when large
 * numbers of queued tasks become cancelled.
 *
 * Finalization:
 * A pool that is no longer referenced in a program AND has no remaining threads will be
 * shutdown automatically. If you would like to ensure that unreferenced pools are reclaimed
 * even if uses forget to call #shutdown, then you must arrange that unused threads eventually
 * die, by setting appropriate keep-alive time, using a lower bound of zero core threads and/or
 * setting #allowCoreThreadTimeOut(boolean).
 *
 * Extension example:
 * Most extensions of this class override one or more of the protected hook methods. For example,
 * here a a subclass that adds a simple pause/resume feature:
 *
 * class PausableThreadPoolExecutor extends ThreadPoolExecutor {
 *     private boolean isPaused;
 *     private ReentrantLock pauseLock = new ReentrantLock();
 *     private Condition unpaused = pauseLock.newCondition();
 *
 *     public PausableThreadPoolExecutor(...) { super(...) }
 *
 *     protected void beforeExecute(Thread t, Runnable r) {
 *         super.beforeExecute(t, r);
 *         pauseLock.lock();
 *         try {
 *             while (isPaused) {
 *                 unpaused.await();
 *             }
 *         } catch(InterruptedException ie) {
 *            t.interrupt();
 *         } finally {
 *             pauseLock.unlock();
 *         }
 *     }
 *
 *     public void pause() {
 *         pauseLock.lock();
 *         try {
 *            isPaused = true;
 *         } finally {
 *             pauseLock.unlock();
 *         }
 *     }
 *
 *     public void resume() {
 *         pauseLock.lock();
 *         try {
 *
 *         }
 *     }
 * }
 *
 *
 *
 */
public class ThreadPoolExecutor extends AbstractExecutorService {

    /**
     * The main pool control state, ctl, is an atomic integer packing
     * two conceptual fields
     * workerCount, indicating the effective number of threads
     * runState,   indicating whether running, shutting down etc
     * <p>
     * In order to pack them into one int, we limit workerCount to
     * (2^29) - 1(about 500 million) threads rather than (2^31) - 1
     * (2 billion) otherwise representable.
     * <p>
     * The workerCount is the number of workers that have been
     * permitted to start and not permitted to stop. The value may be
     * transiently different from the actual number of live threads,
     * for example when a ThreadFactory fails to create a thread whenn
     * asked, and when exiting threads are still performing
     * bookkeeping before terminating. The user-visible pool size is
     * reported as the current size of the workers set.
     * <p>
     * The runState provides the main lifecycle control, taking on values:
     * <p>
     * RUNNING: Accept new tasks and process queued tasks
     * SHUTDOWN: Don't accept new tasks, but process queued tasks
     * STOP: Don't accept new tasks, don't process queued tasks,
     * and interrupt in-progress tasks
     * TIDYING: All tasks have terminated, workerCount is zero,
     * the thread transitioning to state TIDYING
     * will run the terminated() hook method
     * TERMINATED: terminate() has completed
     * <p>
     * The numerical order among these values matters, to allow
     * ordered comparisons. The runState monotonically increases over
     * time, but need not hit each state. The transitions are:
     * <p>
     * RUNNING -> SHUTDOWN
     * On invocation of shutdown(), perhaps implicitly in finalize()
     * (RUNNING or SHUTDOWN) -> STOP
     * On invocation of shutdownNow()
     * SHUTDOWN -> TIDYING
     * When both queue and pool are empty
     * STOP -> TIDYING
     * When pool is empty
     * TIDYING -> TERMINATED
     * When the terminated() hook method has completed
     * <p>
     * Threads waiting in awaitTermination() will return when the state
     * reaches TERMINATED.
     * <p>
     * Detecting the transition from SHUTDOWN to TIDYING is less
     * straightforward than you'd like because the queue may become empty
     * after non-empty and vice versa during SHUTDOWN state, but we can
     * only terminate if, after seeing that it is empty, we see
     * that workerCount is 0 (which sometimes entails a recheck -- see below)
     */
    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING = -1 << COUNT_BITS;
    private static final int SHUTDOWN = 0 << COUNT_BITS;
    private static final int STOP = 1 << COUNT_BITS;
    private static final int TIDYING = 2 << COUNT_BITS;
    private static final int TERMINATED = 3 << COUNT_BITS;

    // Packing and unpacking ctl
    private static int runStateOf(int c) { return c & ~CAPACITY; }
    private static int workerCountOf(int c) { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }

    /**
     * Bit field accessors that don't require unpacking ctl.
     * These depend on the bit layout and on workerCount being never negative.
     */
    private static boolean runStateLessThan(int c, int s) { return c < s; }

    private static boolean runStateAtLeast(int c, int s) { return c >= s; }

    private static boolean isRunning(int c) {
        return c < SHUTDOWN;
    }

    /**
     * Attempts to CAS-increment the workerCount field of ctl.
     */
    private boolean compareAndIncrementWorkerCount(int expect) {
        return ctl.compareAndSet(expect, expect + 1);
    }

    /**
     * Attempts a CAS-decrement the workerCount field of ctl.
     */
    private boolean compareAndDecrementWorkerCount(int expect) {
        return ctl.compareAndSet(expect, expect - 1);
    }

    /**
     * Decrements the workerCount field of ctl. This is called only on
     * abrupt termination of a thread (see processWorkerExit). Other
     * decrements are performed withing getTask.
     */
    private void decrementWorkerCount() {
        do {} while (!compareAndDecrementWorkerCount(ctl.get()));
    }

    /**
     * The queue used for holding tasks and handing off to workers
     * threads. We do not require that workerQueue.poll() returning
     * null necessarily means that workerQueue.isEmpty(), so rely
     * solely on isEmpty to see if the queue is empty (which we must
     * do for example when deciding whether to transition from
     * SHUTDOWN to TIDYING). This accommodates special-purpose
     * queues such as DelayQueues for which poll() is allowed to return null
     * even if it may later return non-null when delays expire.
     */
    private final BlockingQueue<Runnable> workQueue;

    /**
     * Lock held on access to workers set and related bookkeeping.
     * While we could use a concurrent set of some sort, it turns out
     * to be generally preferable to use a lock. Among the reasons is
     * that this serializes interruptIdleWorkers, which avoids unnecessary
     * interrupt storms, especially during shutdown.
     * Otherwise existing threads would concurrently interrupt those
     * that have not yet interrupted. It also simplifies some of the
     * associated statistics bookkeeping of largestPoolSize etc. We
     * also hold mainLock on shutdown and shutdownNow, for the sake of
     * ensuring workers set is stable while separately checking
     * permission to interrupt and actually interrupting.
     */
    private final ReentrantLock mainLock = new ReentrantLock();

    /**
     * Wait condition to support awaitTermination
     */
    private final Condition termination = mainLock.newCondition();

    /**
     * Set containing all workers threads in pool. Accessed only when
     * holding mainLock.
     */
    private final HashSet<Worker> workers = new HashSet<>();

    /**
     * Counter for completed tasks. Undated only on termination of
     * workers threads. Accessed only under mainLock.
     */
    private long completedTaskCount;

    /**
     * All user control parameters are declared as volatile so that
     * ongoing actions are based on freshest values, but without need
     * for locking, since no internal invariants depend on them
     * changing synchronously with respect to other actions.
     */

    /**
     * Factory for new threads. All threads are created using this
     * factory (via method addWorker). All callers must be prepared
     * for addWorker to fail, which may reflect a system or user's
     * policy limiting the number of threads. Even though it is not
     * treated as an error, failure to create threads may result in
     * new tasks being rejected or existing ones remaining stuck in
     * the queue.
     *
     * We go further and preserve pool invariants even in the face of
     * errors such as OutOfMemoryError, that might be thrown while
     * trying to create threads. Such errors are rather common due to
     * the need to allocate a native stack in Thread.start(), and users
     * will want to perform clean pool shutdown to clean up. There
     * will likely be enough memory available for the cleanup code to
     * complete without encountering yet another OutOfMemoryError.
     */
    private volatile ThreadFactory threadFactory;

    /**
     * Handler called when saturated or shutdown in execute.
     */
    private volatile RejectedExecutionHandler handler;

    /**
     * Timeout in nanoseconds for idle threads waiting for work.
     * Threads use this timeout when there are more than corePoolSize
     * present or if allowCoreThreadTimeOut. Otherwise they wait
     * forever for new work.
     */
    private volatile long keepAliveTime;

    /**
     * If false (default), core threads stay alive even when idle.
     * If true, core threads use keepAliveTime to time out waiting
     * for work.
     */
    private volatile boolean allowCoreThreadTimeOut;

    /**
     * Core pool size is the minimum number of workers to keep alive
     * (and not allow to time out etc) unless allowCoreThreadTimeOut
     * is set, in which case the minimum is zero.
     */
    private volatile int corePoolSize;

    /**
     * Maximum pool size. Note that the actual maximum is internally
     * bounded by CAPACITY.
     */
    private volatile int maximumPoolSize;

    /**
     * The default rejected execution handler
     */
    private static final RejectedExecutionHandler defaultHandler =
            new AbortPolicy();


    /**
     * Class Worker mainly maintains interrupt control state for
     * threads running tasks, along with other minor bookkeeping.
     * The class opportunistically extends AbstractQueuedSynchronizer
     * to simplify acquiring and releasing a lock surrounding each
     * task execution. This protects against interrupts that are
     * intended to wake up a workers thread waiting for a task from
     * instead interrupting a task being run.
     * We implement a simple non-reentrant mutual exclusion lock rather
     * than use ReentrantLock because we do not want workers tasks to
     * be able to reacquire the lock when they invoke pool control methods
     * like setCorePoolSize. Additionally, to suppress interrupts until
     * the thread actually starts running tasks, we initialize lock
     * state to a negative value, and clear it upon start (in
     * runWorker).
     */
    private final class Worker
        extends AbstractQueuedSynchronizer
        implements Runnable {

        /** Thread this workers is running in. Null if factory fails. */
        final Thread thread;
        /** Initial task to run. Possibly null. */
        Runnable firstTask;
        /** Per-thread task counter */
        volatile long completedTasks;

        /**
         * Creates with given first task and thread from ThreadFactory.
         *
         * @param firstTask the first task (null if none)
         */
        Worker(Runnable firstTask) {
            setState(-1); // inhibit interrupts until runWorker
            this.firstTask = firstTask;
            // this.thread = getThreadFactory().newThread(this);
            this.thread = getThreadFactory().newThread(() -> {}); // use front line
        }

        @Override
        // Delegates main run loop to outer runWorker
        public void run() {
           runWorker(this);
        }

        // Lock methods
        // The value 0 represents the unlocked state.
        // The value 1 represents the locked state.

        protected boolean tryAcquire(int unused) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        protected boolean tryRelease(int unused) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        public void lock() { acquire(1); }
        public boolean tryLock() { return tryAcquire(1); }
        public void unlock() { release(1); }
    }

    /**
     * Returns the thread factory used to create new threads.
     *
     * @return the current thread factory
     * @see #setThreadFactory(ThreadFactory)
     */
    public ThreadFactory getThreadFactory() {
        return threadFactory;
    }

    public void setThreadFactory(ThreadFactory threadFactory) {
        if (threadFactory == null) throw new NullPointerException();
        this.threadFactory = threadFactory;
    }

    /**
     * A handler for rejected tasks that throws a
     * RejectedExecutionException
     */
    public static class AbortPolicy implements RejectedExecutionHandler {

        public AbortPolicy() {}

        @Override
        /**
         * Always throws RejectedExecutionException
         *
         * @param r the runnable task requested to be executed
         * @param e the executor attempting to execute this task
         * @throws java.util.concurrent.RejectedExecutionException always
         */
        public void rejectedExecution(java.lang.Runnable r, java.util.concurrent.ThreadPoolExecutor e) {
            throw new RejectedExecutionException("Task " + r.toString() +
                    " rejected from " + e.toString());
        }
    }

    /**
     * Invokes the rejected execution handler for the given command.
     * Package-protected for use by ScheduledThreadPoolExecutor.
     */
    final void reject(Runnable command) {
        // handler.rejectedExecution(command, this);
    }

    /**
     * Transitions to TERMINATED state if either (SHUTDOWN and pool
     * and queue empty) or (STOP and pool empty). If otherwise
     * eligible to terminate but workerCount is nonzero, interrupts
     * an idle worker to ensure that shutdown signals propagate.
     * This method must be called following any action that might make
     * termination possible -- reducing worker count or removing tasks
     * from the queue during shutdown. The method is non-private to
     * allow access from ScheduledThreadPoolExecutor.
     */
    final void tryTerminate() {
        for (;;) {
            int c = ctl.get();
            if (isRunning(c) ||
                    runStateAtLeast(c, TIDYING) ||
                    (runStateOf(c) == SHUTDOWN && !workQueue.isEmpty())) {
                return;
            }

            if (workerCountOf(c) != 0) {
                // Eligible to terminate
                // interruptIdleWorkers(ONLY_ONE);
                return;
            }

            final ReentrantLock mainLock = this.mainLock;
            mainLock.lock();
            try {
                if (ctl.compareAndSet(c, ctlOf(TIDYING, 0))) {
                    try {
                         terminated();
                    } finally {
                        ctl.set(ctlOf(TERMINATED, 0));
                        termination.signalAll();
                    }
                    return;
                }
            } finally {
                mainLock.unlock();
            }
            // else retry on failed CAS
        }
    }


    /**
     * Methods for creating, running and cleaning up after workers
     */

    /**
     * Checks if a new worker can be added with respect to current
     * pool state and the given bound (either core or maximum). If so,
     * the worker count is adjusted accordingly, and, if possible, a
     * new worker is created and started, running firstTask as its
     * first task. This method returns false if the pool is stopped or
     * eligible to shut down. It also returns false if the thread
     * factory fails to create a thread when asked. If the thread
     * creation fails, either due to the thread factory returning
     * null, or due to an exception (typically OutOfMemoryError in
     * Thread.start()), we roll back cleanly.
     *
     * @param firstTask the task the new thread should run first (or
     * null if none.) Workers are created with an initial first task
     * (in method execute()) to bypass queuing when there are fewer
     * than corePoolSize threads (in which case we always start one),
     * or when the queue is null (in which case we must bypass queue).
     * Initially idle threads are usually created via
     * prestartCoreThread or to replace other dying workers.
     *
     * @param core if true use corePoolSize as bound, else
     * maximumPoolSize. (A boolean indicator is used here rather than a
     * value to ensure reads of fresh values after checking other pool
     * state)
     * @return true if successful
     */
    private boolean addWorker(Runnable firstTask, boolean core) {
        retry:
        for (;;) {
            int c = ctl.get();
            int rs = runStateOf(c);

            // Check if queue empty only if necessary
            if (rs >= SHUTDOWN &&
                    ! (rs == SHUTDOWN &&
                            firstTask == null &&
                            !workQueue.isEmpty())) {
               return false;
            }

            for (;;) {
                int wc = workerCountOf(c);
                if (wc > CAPACITY ||
                        wc >= (core ? corePoolSize : maximumPoolSize)) {
                    return false;
                }

                if (compareAndIncrementWorkerCount(c)) {
                    // 退出双重循环
                    break retry;
                }

                c = ctl.get();
                if (runStateOf(c) != rs) {
                    continue retry;
                }
                // else CAS failed due to workerCount change; retry inner loop
            }
        }

        boolean workerStarted = false;
        boolean workerAdded = false;
        Worker w = null;
        try {
            w = new Worker(firstTask);
            final Thread t = w.thread;
            if (t != null) {
                final ReentrantLock mainLock = this.mainLock;
                mainLock.lock();

                try {
                    // Recheck while holding lock
                    // Back out on ThreadFactory failure or if
                    // shut down before lock acquired.
                    int rs = runStateOf(ctl.get());

                    if (rs < SHUTDOWN ||
                            (rs == SHUTDOWN && firstTask == null)) {
                        if (t.isAlive()) {
                            // precheck that t is startable
                            throw new IllegalThreadStateException();
                        }
                        workers.add(w);
                        // int s = workers.size();
                        // if (s > largestPoolSize) { largestPoolSize = s; }
                        workerAdded = true;
                    }
                } finally {
                    mainLock.unlock();
                }
                if (workerAdded) {
                    // 启动线程，执行 Worker的run方法
                    // run方法实际会委托给 runWorker(this)
                    t.start();
                    workerStarted = true;
                }
            }
        } finally {
            if (!workerStarted) {
                addWorkerFailed(w);
            }
        }
        return workerStarted;
    }

    /**
     * Rolls back the worker thread creation.
     * - removes worker from workers, if present
     * - decrements worker count
     * - rechecks for termination, in case the existence of this
     * worker was holding up termination
     */
    private void addWorkerFailed(Worker w) {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            if (w != null) {
                workers.remove(w);
            }
            decrementWorkerCount();
            // tryTerminate();
        } finally {
            mainLock.unlock();
        }
    }

    /**
     * Performs blocking or time wait for a task, depending on current
     * configuration settings, or return null if this workers must
     * exit because of any of:
     *  1. There are more than maximumPoolSize workers (due to
     *      a call to setMaximumPoolSize)
     *  2. The pool is stopped.
     *  3. The pool is shutdown and the queue is empty.
     *  4. This workers time out waiting for a task, and timed-out
     *      workers are subject to termination (that is,
     *      allowCoreThreadTimeOut || workerCount > corePoolSize)
     *      both before and after the timed wait, and if the queue is
     *      non-empty, this workers is not the last thread in the pool.
     *
     * @return task, or null if the workers must exit, in which case
     *     workerCount is decremented
     */
    private Runnable getTask() {
        boolean timedOut = false; // Do the last poll() time out?

        for (;;) {
            int c= ctl.get();
            int rs = runStateOf(c);

            // Check if the queue empty only if necessary
            if (rs >= SHUTDOWN && (rs >= STOP || workQueue.isEmpty())) {
                // (rs == SHUTDOWN && workQueue.isEmpty()) || rs >= STOP
                decrementWorkerCount();
                return null;
            }

            int wc = workerCountOf(c);

            // Are workers subject to culling?
            boolean timed = allowCoreThreadTimeOut || wc > corePoolSize;
            if ((wc > maximumPoolSize || (timed && timedOut))
                    && (wc > 1 || workQueue.isEmpty())) {
                if (compareAndDecrementWorkerCount(c)) {
                    return null;
                }
                continue;
            }

            try {
               Runnable r = timed ?
                        workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) :
                        workQueue.take();
               if (r != null) {
                   return r;
               }
               timedOut = true;
            } catch (InterruptedException retry) {
                timedOut = false;
            }
        }
    }

    /**
     * Main workers run loop. Repeatedly gets tasks from queue and executes them,
     * while coping with a number of issues:
     *
     * 1. We may start out with an initial task, in which case we don't
     * need to get the first one. Otherwise, as long as pool is running,
     * we get tasks from getTask. If it returns null then the workers exits
     * due to changed pool state or configuration parameter. Other exits result
     * from exception throws in external code, in which case completedAbruptly
     * holds, which usually leads processWorkerExit to replace this thread.
     *
     * 2. Before running any task, the lock is acquired to prevent other
     * pool interrupts while the task is executing, and then we ensure that
     * unless pool is stopping, this thread does not have its interrupt set.
     *
     * 3. Each task run is preceded a call to beforeExecute, which might throw
     * an exception, in which case we cause thread to die
     * (breaking loop with completedAbruptly true) without processing the task.
     *
     * 4. Assuming beforeExecute completes normally, we run the task, gathering any
     * of its thrown exceptions to send to afterExecute.
     * We separately handle RuntimeException, Error (both of which the specs guarantee
     * that we trap) and arbitrary Throwables.
     * Because we cannot rethrow Throwables within Runnable.run, we wrap
     * them within Errors on the way out (to the thread's UncaughtExceptionHandler).
     * Any thrown exception also conservatively causes thread to die.
     *
     * 5. After task.run completes, we call afterExecute, which may
     * also throw an exception, which will also cause thread to die.
     * According to JLS Sec 14.20, this exception is the one that will
     * be in effect even if task.run throws.
     *
     * The net effect of the exception mechanics is that afterExecute
     * and the thread's UncaughtExceptionHandler have as accurate
     * information as we can provide about anyy problems encounterd by
     * user code.
     *
     * @param w the workers
     */
    final void runWorker(Worker w) {
        Thread wt = Thread.currentThread();
        Runnable task = w.firstTask;
        w.firstTask = null;
        w.unlock(); // allow interrupts
        boolean completedAbruptly = true;
        try {
            while (task != null || (task = getTask()) != null) {
                w.lock();
                // If pool is stopping, ensure thread is interrupted.
                // If not, ensure thread is not interrupted. This
                // requires a recheck in second case to deal with
                // shutdownNoe race while clearing interrupt
                if ((runStateAtLeast(ctl.get(), STOP) ||
                        Thread.interrupted() &&
                                runStateAtLeast(ctl.get(), STOP)) &&
                        !wt.isInterrupted()) {
                    wt.interrupt();
                }

                try {
                    beforeExecute(wt, task);
                    Throwable thrown = null;
                    try {
                        task.run(); // !!!!!! finally see it !!!
                    } catch (RuntimeException x) {
                        thrown = x; throw x;
                    } catch (Error x) {
                        thrown = x; throw x;
                    } catch (Throwable x) {
                        thrown = x; throw new Error(x);
                    } finally {
                        afterExecute(task, thrown);
                    }
                } finally {
                    task = null;
                    w.completedTasks++;
                    w.unlock();
                }
            }
        } finally {
            processWorkerExit(w, completedAbruptly);
        }
    }

    /**
     * Performs cleanup and bookkeeping for a dying workers. Called
     * only from workers threads. Unless completedAbruptly is set,
     * assumes that workerCount has already been adjusted to account
     * for exit. This method removes thread from workers set, and
     * possibly terminates the pool or replaces the workers if either
     * it exited due to user task exception or if fewer than corePoolSize
     * workers are running or queue is non-empty but there are no workers.
     *
     * @param w the workers
     * @param completedAbruptly if the workers died due to user exception
     */
    private void processWorkerExit(Worker w, boolean completedAbruptly) {
        if (completedAbruptly) { // If abrupt, then workerCount wasn't abjusted
            decrementWorkerCount();
        }
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            completedTaskCount += w.completedTasks;
            workers.remove(w);
        } finally {
            mainLock.unlock();
        }

        // tryTerminate();
        // ... try addWorker(null, false);

    }

    // Extension hooks

    protected void beforeExecute(Thread t, Runnable runnable) {

    }

    protected void afterExecute(Runnable r, Throwable t) {}

    protected void terminated() {}

    // Public constructors and methods

    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
                Executors.defaultThreadFactory(), defaultHandler);
    }

    /**
     * Creates a new ThreadPoolExecutor with the given initial parameters.
     *
     * @param corePoolSize the number of threads to keep in the pool, even
     *           if they are idle, unless {@code allowCoreThreadTimeOut} is
     *           set
     * @param maximumPoolSize the maximum number of threads to allow in the pool
     * @param keepAliveTime when the number of threads is greater than
     *            the core, this is the maximum time that excess idle threads
     *            will wait for new tasks before terminating,
     * @param unit the time unit for the {@code keepAliveTime} argument
     * @param workQueue the queue to use for holding tasks before they are
     *          executed. This queue will hold only the {@code Runnable}
     *          tasks submitted by the {@code execute} method.
     * @param threadFactory the factory to use when the executor
     *           creates a new thread
     * @param handler the handler to use when execution is blocked
     *          because the thread bounds and queue capacities are reached.
     * @throws IllegalArgumentException if one of the following holds:
     *      corePoolSize < 0
     *      keepAliveTime < 0
     *      maximumPoolSize <= 0
     *      maximumPoolSize < corePoolSize
     * @throws NullPointerException if workQueue or threadFactory or handler is null
     */
    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {
        if (corePoolSize < 0 ||
                maximumPoolSize <= 0 ||
                maximumPoolSize < corePoolSize ||
                keepAliveTime < 0) {
            throw new IllegalArgumentException();
        }
        if (workQueue == null || threadFactory == null || handler == null) {
            throw new NullPointerException();
        }
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
    }

    /**
     * Executes the given task sometime in the future. The task
     * may execute in a new thread or in an existing pooled thread.
     *
     * If the task cannot be submitted for execution, either because this
     * executor has been shutdown or because its capacity has been reached,
     * the task is handled by the current {@code RejectedExecutionHandler}.
     *
     * @param command the task to execute
     * @throws RejectedExecutionException at discretion of
     *      {@code RejectedExecutionHandler}, if the task
     *      cannot be accepted for execution
     * @throws NullPointerException if {@code command} is null
     */
    public void execute(Runnable command) {
        if (command == null) {
            throw new NullPointerException();
        }

        /**
         * Proceed in 3 steps:
         *
         * 1. If fewer than corePoolSize threads are running, try to
         * start a new thread with the given command as its first
         * task. The call to addWorker atomically checks runState and
         * workerCount, and so prevents false alarms that wound add
         * threads when it shouldn't, by returning false.
         *
         * 2. If a task can be successfully queued, then we still need
         * to double-check whether we should have added a thread
         * (because existing ones died since last checking) or that
         * the pool shut down since entry into this method. So we
         * recheck state and if necessary roll back the enqueuing if
         * stopped, or start a new thread if there are none.
         *
         * 3. If we cannot queue task, then we try to add a new
         * thread. If it fails, we know we are shut down or saturated
         * and so reject the task.
         */
        int c = ctl.get();
        if (workerCountOf(c) < corePoolSize) {
            if (addWorker(command, true)) {
                return;
            }
            c = ctl.get();
        }
        if (isRunning(c) && workQueue.offer(command)) {
            int recheck = ctl.get();
            if (!isRunning(recheck) )
                    // && remove(command))
                reject(command);
            else if (workerCountOf(recheck) == 0) {
                addWorker(null, false);
            }
        } else if (!addWorker(command, false)) {
            reject(command);
        }
    }

    @Override
    public List<Runnable> shutdownNow() {
        // todo
        return null;
    }

    public boolean isShutdown() {
        return !isRunning(ctl.get());
    }

    public boolean isTerminated() {
        return runStateAtLeast(ctl.get(), TERMINATED);
    }

    /**
     * Initiates an orderly shutdown in which previously submitted
     * tasks are executed, but no new tasks will be accepted.
     * Invocation has no additional effect if already shut down.
     *
     * This method does not wait for previously submitted tasks to
     * complete execution. Use #awaitTermination to do that.
     */
    @Override
    public void shutdown() {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            // checkShutdownAccess();
            // advanceRunState(SHUTDOWN);
            // interruptIdleWorkers();
            // onShutdown(); // hook for ScheduledThreadPoolExecutor
        } finally {
            mainLock.unlock();
        }
        tryTerminate();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit)
            throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            for (;;) {
                if (runStateAtLeast(ctl.get(), TERMINATED)) {
                    return true;
                }
                if (nanos <= 0) {
                    return false;
                }
                nanos = termination.awaitNanos(nanos);
            }
        } finally {
            mainLock.unlock();
        }
    }
}
