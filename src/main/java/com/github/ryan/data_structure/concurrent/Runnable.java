package com.github.ryan.data_structure.concurrent;

/**
 * The Runnable interface should be implemented by any
 * class whose instances are intended to be executed by a thread.
 *
 * This interface is designed to provide a common protocol
 * for objects that wish to execute code while they are active.
 * Being active simply means that a thread has been started and
 * has not yet been stopped.
 *
 * In most cases, the Runnable interface should be used
 * if you are only planning to override the run() method
 * and no other Thread methods.
 * This is important because classes should not be subclassed
 * unless the programmer intends on modify or enhancing the
 * fundamental behavior of the class.
 *
 * @className Runnable
 * @date October 22,2018
 */
@FunctionalInterface
public interface Runnable {

    /**
     * When an object implementing interface Runnable is used
     * to create a thread, starting the thread causes the object's
     * run method to be called in that separately executing
     * thread.
     */
    void run();
}
