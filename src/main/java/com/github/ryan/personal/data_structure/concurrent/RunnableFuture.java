package com.github.ryan.personal.data_structure.concurrent;

/**
 * A Future that is Runnable. Successful execution of the run method
 * causes completion of the Future and allows access of its results.
 *
 * @param <V> The result type returned by this Future's get method
 */
public interface RunnableFuture<V> extends Runnable, Future<V>{

    /**
     * Sets this Future to the result of its computation
     * unless it has been cancelled.
     */
    void run();
}
