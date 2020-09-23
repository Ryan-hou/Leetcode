package com.github.ryan.data_structure.concurrent;

/**
 * A task that returns a result and may throw an exception.
 *
 * The Callable interface is similar to Runnable, in that both
 * are designed for classes whose instances are potentially executed
 * by another thread. A Runnable, however, does not returns a result
 * and cannot throw a checked exception.
 *
 * The Executors class contains utility methods to convert from
 * other common forms to Callable classes.
 *
 * @param <V> the result type of method call
 */

@FunctionalInterface
public interface Callable<V> {

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    V call() throws Exception;
}
