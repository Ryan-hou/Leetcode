package com.github.ryan.personal.data_structure.concurrent.executor;

import com.github.ryan.personal.data_structure.concurrent.Callable;
import com.github.ryan.personal.data_structure.concurrent.Future;
import com.github.ryan.personal.data_structure.concurrent.FutureTask;
import com.github.ryan.personal.data_structure.concurrent.RunnableFuture;
import com.github.ryan.personal.data_structure.concurrent.Runnable;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Provides default implementations of ExecutorService execution methods.
 * This class implements the submit, invokeAny and invokeAll methods
 * using a {@link com.github.ryan.personal.data_structure.concurrent.RunnableFuture}
 * returned by {@code newTaskFor}, which defaults to the {@link com.github.ryan.personal.data_structure.concurrent.FutureTask}
 * class provided in this package. For example, the implementation of {@code submit(Runnable)}
 * creates an associated {@code RunnableFuture} that is executed
 * and returned. Subclasses may override the {@code newTaskFor} methods
 * to return {@code RunnableFuture} implementations other than
 * {@code FutureTask}
 *
 * Extension example. Here is a sketch of a class that customizes
 * ThreadPoolExecutor to use a CustomTask class instead of the default
 * FutureTask:
 *
 * public class CustomThreadPoolExecutor extends ThreadPoolExecutor {
 *     static class CustomTask<V> implements RunnableFuture<V> {...}
 *
 *     protected <V> RunnableFuture<V> newTaskFor(Callable<V> c) {
 *         return new CustomTask<V>(c);
 *     }
 *
 *     protected <V> RunnableFuture<V> newTaskFor(Runnable r, V v) {
 *         return new CustomTask<V>(r, v);
 *     }
 *
 *     // ... add constructors, etc.
 * }
 *
 */
public abstract class AbstractExecutorService implements ExecutorService {

    /**
     * Returns a RunnableFuture for the given runnable and default
     * value.
     *
     * @param runnable the runnable task being wrapped
     * @param value the default value for the returned future
     * @param <T> the type of the given value
     * @return a RunnableFuture which, when run, will run the
     * underlying runnable and which, as a Future, will yield
     * the given value as its result and provide for cancellation
     * of the underlying task
     */
    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
        return new FutureTask<>(runnable, value);
    }

    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new FutureTask<>(callable);
    }

    /**
     * @throws NullPointerException
     * @throws java.util.concurrent.RejectedExecutionException
     */
    @Override
    public <T> Future<T> submit(Callable<T> task) {
        if (task == null) throw new NullPointerException();
        RunnableFuture<T> ftask = newTaskFor(task);
        execute(ftask);
        return ftask;
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        if (task == null) throw new NullPointerException();
        RunnableFuture<T> ftask = newTaskFor(task, result);
        execute(ftask);
        return ftask;
    }

    @Override
    public Future<?> submit(Runnable task) {
        if (task == null) throw new NullPointerException();
        RunnableFuture<Void> ftask = newTaskFor(task, null);
        execute(ftask);
        return ftask;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> task)
            throws InterruptedException {
        // todo
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks)
            throws InterruptedException, ExecutionException {
        // todo
        return null;
    }
}
