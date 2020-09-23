package com.github.ryan.data_structure.concurrent;

import com.github.ryan.data_structure.concurrent.executor.Executor;

import java.util.concurrent.ExecutionException;

/**
 * A Future represents the result of an asynchronous computation.
 * Methods are provided to check if the computation is complete,
 * to wait for its completion, and to retrieve the result of
 * the computation. The result can only be retrieved using method
 * get when the computation has completed, blocking if necessary
 * until it is ready.
 *
 * Sample Usage:
 *
 * interface ArchiveSearcher { String search(String target); }
 * class App {
 *  ExecutorService executor = ...
 *  ArchiveSearcher searcher = ...
 *  void showSearch(final String target)
 *      throws InterruptedException {
 *      Future<String> future
 *         = executor.submit(new Callable<String>() {
 *      public String call() {
 *      return searcher.search(target);
 *      }
 *  });
 *  displayOtherThings(); // do other things while searching
 *  try {
 *     displayText(future.get()); // use future
 *  } catch (ExecutionException ex) {
 *     cleanup();
 *     return;
 *   }
 *  }
 * }
 *
 * The FutureTask class is an implementation of Future that implements
 * Runnable, and so may be executed by an Executor.
 * For example, the above construction with submit could be replaced
 * by:
 *  FutureTask<String> future =
 *     new FutureTask<String>(new Callable<String>() {
 *     public String call() {
 *      return searcher.search(target);
 *     }
 *  });
 *  executor.execute(future);
 *
 * Memory consistency effects: Actions taken by the asynchronous computation
 * happen-before actions following the corresponding Future.get() in another
 * thread.
 *
 * ///// From geeksforgeeks ///////
 * Think of a Future as an object that holds the result - it may not hold
 * it right now, but it will do so in the future(once the Callable returns)
 * Thus, a Future is basically one way the main thread can keep track of
 * the progress and result from other threads.
 *
 * Observe that Callable and Future do two different things - Callable is similar
 * to Runnable, in that it encapsulates a task that is meant to run on another
 * thread, whereas a Future is used to store a result obtained from a different
 * thread. In fact, the Future can be made to work with Runnable as well,
 * which is something that will become clear when Executors come into the picture.
 *
 * To create the thread, a Runnable is required. To obtain the result, a Future
 * is required.
 * The Java library has the concrete type FutureTask, which implements Runnable and Future,
 * combining both functionality conveniently.
 * A FutureTask can be created by providing its constructor with a Callable. Then the
 * FutureTask object is provided to the constructor of Thread to create the Thread object.
 * Thus, indirectly, the thread is created with a Callable.
 * For further emphasis, note that there is no way to create the thread
 * directly with a Callable.
 *
 *
 * @param <V> the result type returned by this Future's get method
 * @see java.util.concurrent.FutureTask
 * @see Executor
 * @since 1.5
 */
public interface Future<V> {

    // boolean cancel(boolean mayInterruptIfRunning);

    // boolean isCancelled();

    /**
     * Returns true if this task completed.
     *
     * Completion may be due to normal termination, an exception, or
     * cancellation -- in all of these cases, this method will return
     * true
     */
    // boolean isDone();

    /**
     * Waits if necessary for the computation to complete, and then
     * retrieves its result.
     *
     * @return the computed result
     * @throws InterruptedException
     *      if the current thread was interrupted
     * @throws ExecutionException
     *      if the computation threw an exception
     * @throws java.util.concurrent.CancellationException
     *      if the computation was cancelled
     */
    V get() throws InterruptedException, ExecutionException;

//    V get(long timeout, TimeUnit unit)
//            throws InterruptedException, ExecutionException, TimeoutException;

}
