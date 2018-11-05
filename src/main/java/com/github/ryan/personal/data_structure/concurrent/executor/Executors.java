package com.github.ryan.personal.data_structure.concurrent;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Executors
 * @date October 22,2018
 */
public class Executors {


    /**
     * Returns a Callable object that, when called, runs the given task
     * and returns the given result.
     * This can be useful when applying methods requiring a
     * Callable to an otherwise resultless action
     *
     * @param task the task to run
     * @param result the result to return
     * @param <T> the type of the result
     * @return a callable object
     * @throws NullPointerException if task null
     */
    public static <T> Callable<T> callable(Runnable task, T result) {
        if (task == null) {
            throw new NullPointerException();
        }
        return new RunnableAdapter<>(task, result);
    }

    /**
     * Returns a Callable object that, when called, runs the given
     * task and returns null.
     *
     * @param task the task to run
     * @return a callable object
     * @throws NullPointerException if task null
     */
    public static Callable<Object> callable(Runnable task) {
        if (task == null) {
            throw new NullPointerException();
        }
        return new RunnableAdapter<>(task, null);
    }

    // Non-public classes supporting the public methods

    /**
     * A callable that runs given task and returns given result
     */
    static final class RunnableAdapter<T> implements Callable<T> {

        final Runnable task;
        final T result;

        RunnableAdapter(Runnable task, T result) {
            this.task = task;
            this.result = result;
        }

        @Override
        public T call() throws Exception {
            task.run();
            return result;
        }
    }
}
