package com.github.ryan.data_structure.concurrent.executor;

import com.github.ryan.data_structure.concurrent.Callable;
import com.github.ryan.data_structure.concurrent.Future;
import com.github.ryan.data_structure.concurrent.Runnable;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * An Executor that provides methods to manage termination
 * and methods that can produce a Future for tracking progress
 * of one or more asynchronous tasks.
 *
 * An ExecutorService can be shut down, which will cause
 * it to reject new tasks.
 *
 * Method submit extends base method Executor#execute(Runnable) by
 * creating and returning a Future that can be used to cancel
 * execution and/or wait for completion.
 *
 * Usage Examples:
 * Here is a sketch of a network service in which threads in a
 * thread pool service incoming requests. It uses the preconfigured
 * Executors#newFixedThreadPool factory method:
 *
 * class NetworkService implements Runnable {
 *     private final ServerSocket serverSocket;
 *     private final ExecutorService pool;
 *
 *     public NetworkService(int port, int poolSize) {
 *         serverSocket = new ServerSocket(port);
 *         pool = Executors.newFixedThreadPool(poolSize);
 *     }
 *
 *     public void run() {
 *         // run the service
 *         try {
 *             for (;;) {
 *                 pool.execute(new Handler(serverSocket.accept()));
 *             }
 *         } catch(IOException ex) {
 *             pool.shutdown();
 *         }
 *     }
 * }
 *
 * class Handler implements Runnable {
 *     private final Socket socket;
 *     Handler(Socket socket) {
 *         this.socket = socket;
 *     }
 *
 *     public void run() {
 *         // read and service request on socket
 *     }
 * }
 *
 * The following method shuts down an ExecutorService in two phases,
 * first by calling {@code shutdown} to reject incoming tasks, and then
 * calling {@code shutdownNow}, if necessary, to cancel any lingering tasks:
 *
 * void shutdownAndAwaitTermination(ExecutorService pool) {
 *     pool.shutdown(); // Disable new tasks from being submitted
 *     try {
 *         // Wait a while for existing tasks to terminate
 *         if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
 *             pool.shutdownNow(); // Cancel currently executing tasks
 *             // Wait a while for tasks to respond to being cancelled
 *             if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
 *                 System.err.println("Pool did not terminate");
 *             }
 *         }
 *     } catch(InterruptedException ie) {
 *         // (Re-)Cancel if current thread also interrupted
 *         pool.shutdownNow();
 *         // Preserve interrupt status
 *         Thread.currentThread.interrupt();
 *     }
 * }
 *
 * Memory consistency effect: Actions in a thread prior to
 * the submission of a Runnable or Callable task to an ExecutorService
 * happen-before any actions taken by that task, which in turn happen-before
 * the result is retrieved via Future.get()
 *
 */
public interface ExecutorService extends Executor {

    /**
     * Initiates an orderly shutdown in which previously submitted
     * tasks are executed, but no new tasks will be accepted.
     * Invocation has no additional effect if already shut down.
     *
     * This method does not wait for previously submitted tasks to
     * complete execution. Use #awaitTermination to do that.
     */
    void shutdown();

    /**
     * Attempts to stop all actively executing tasks, halts the
     * processing of waiting tasks, and returns a list of the tasks
     * that were awaiting execution.
     *
     * This method does not wait for actively executing tasks to
     * terminate. Use #awaitTermination to do that.
     *
     * There are no guarantees beyond best-effort attempts to stop
     * processing actively executing tasks. For example, typical
     * implementations will cancel via Thread#interrupt, so any
     * task that fails to respond to interrupts may never terminate.
     *
     * @return list of tasks that never commenced execution
     */
    List<Runnable> shutdownNow();

    boolean isShutdown();

    /**
     * Returns true if all tasks have completed following shut down.
     * Note that {@code isTerminated} is never {@code true} unless
     * either {@code shutdown} or {@code shutdownNow} was called first.
     *
     * @return {@code true} if all tasks have completed following shut down
     */
    boolean isTerminated();

    /**
     * Blocks until all tasks have completed after a shutdown request,
     * or the timeout occurs, or the current thread is interrupted,
     * whichever happens first.
     *
     * @param timeout the maximum time to wait
     * @param unit the time unit of the timeout argument
     * @return {@code true} if this executor terminated and
     *      {@code false} if the timeout elapsed before termination
     *
     * @throws InterruptedException if interrupted while waiting
     */
    boolean awaitTermination(long timeout, TimeUnit unit)
        throws InterruptedException;

    /**
     * Submits a value-returning task for execution and returns a
     * Future representing the pending results of the task. The
     * Future's get method will return the task's result upon
     * successful completion.
     *
     * If you would like to immediately block waiting for a task,
     * you can use constructions of the form
     * {@code result = exec.submit(aCallable).get();}
     *
     * Note: the Executors class includes a set of methods that can
     * convert some other common closure-like objects,
     * for example, {@link java.security.PrivilegedAction} to
     * Callable form so they can be submitted.
     *
     * @param task the task to submit
     * @param <T> the type of the task's result
     * @return a Future representing pending completion of the task
     * @throws NullPointerException if the task is null
     * @throws java.util.concurrent.RejectedExecutionException
     *      if the task cannot be scheduled for execution
     */
    <T> Future<T> submit(Callable<T> task);

    <T> Future<T> submit(Runnable task, T result);

    Future<?> submit(Runnable task);

    /**
     * Executes the given tasks, returning a list of Futures holding
     * their status and results when all complete.
     * Future#isDone is true for each element of the returned list.
     * Note that a completed task could have terminated either normally
     * or by throwing an exception.
     * The results of this method are undefined if the given collection
     * is modified while this operation is in progress
     * @param task
     * @param <T>
     * @return a list of Futures representing the tasks, in the same
     *      sequential order as produced by the iterator for the
     *      given task list, each of which has completed
     * @throws InterruptedException
     */
    <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> task)
        throws InterruptedException;

    <T> T invokeAny(Collection<? extends  Callable<T>> tasks)
        throws InterruptedException, ExecutionException;

}
