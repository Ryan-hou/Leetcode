package com.github.ryan.data_structure.concurrent;

import java.lang.Runnable;
import java.security.AccessControlContext;

import static java.lang.Thread.currentThread;

/**
 * When a Java Virtual Machine starts up, there is usually a single
 * non-daemon thread (which typically calls the method named main of
 * some designated class). The Java Virtual Machine continues to execute
 * threads until either of the following occurs:
 *
 *  1.The exit method of class Runtime has been called and the security
 *  manager has permitted the exit operation to take place.
 *  2.All threads that are not daemon threads have died, either by
 *  returning from the call to run method or by throwing an exception
 *  that propagates beyond the run method.
 *
 */
public class Thread1 implements java.lang.Runnable {

    // What will be run.
    private Runnable target;

    // Whether or not the thread is a daemon thread
    private boolean daemon = false;

    private int priority;

    // The group of this thread
    private ThreadGroup group;
    // The context ClassLoader for this thread
    private ClassLoader contextClassLoader;

    // Thread ID
    private long tid;

    // ThreadLocal values pertaining to this thread. This map is maintained
    // by the ThreadLocal class.
    // ThreadLocal.ThreadLocalMap threadLocals = null;

    /**
     * Java thread status for tools,
     * initialized to indicate thread 'not yet started'
     */
    private volatile int threadStatus = 0;

    /**
     * Causes this thread to begin execution; the Java Virtual Machine
     * calls the run method of this thread.
     * The result is that two threads are running concurrently: the
     * current thread (which returns from the call to the start method)
     * and the other thread (which executes its run method).
     *
     * It is never legal to start a thread more than once.
     * In particular, a thread may not be restarted once it has completed
     * execution.
     *
     * @throws IllegalThreadStateException if the thread was already started.
     * @see #run()
     */
    public synchronized void start() {
        /**
         * This method is not invoked for the main method or "system"
         * group threads created/set up by the VM. Any new functionality
         * added to this method in the future may have to also be
         * added to the VM.
         *
         * A zero status value corresponds to state "NEW".
         */
        if (threadStatus != 0) {
            throw new IllegalThreadStateException();
        }

        // start0();
    }

    @Override
    public void run() {
        if (target != null) {
            target.run();
        }
    }

//    public Thread(Runnable target) {
//        init(null, target, "Thread-" + nextThreadNum(), 0);
//    }

    /**
     * Initializes a Thread.
     *
     * @param g teh Thread group
     * @param target the object whose run() method gets called
     * @param name the name of the new Thread
     * @param stackSize the desired stack size for the new thread, or
     *                  zero to indicate that this parameter is to be ignored.
     * @param accessControlContext the AccessControlContext to inherit, or
     *                             AccessController.getContext if null
     */
    private void init(ThreadGroup g, Runnable target, String name,
                      long stackSize, AccessControlContext accessControlContext) {
        if (name == null) {
            throw new NullPointerException("name cannot be null");
        }

        Thread parent = currentThread();
        if (g == null) {
            // ...
            if (g == null) {
                g = parent.getThreadGroup();
            }
        }
        // ...
        this.group = g;
        this.daemon = parent.isDaemon();
        this.priority = parent.getPriority();
        this.target = target;
        // setPriority(priority);
        // this.stackSize = stackSize;
        // tid = nextThreadID();
//        if (parent.inheritableThreadLocals != null) {
//           this.inheritableThreadLocals =
//               ThreadLocal.createInheritedMap(parent.inheritableThreadLocals);
//        }
    }

    /**
     * Returns an array of stack trace elements representing the stack dump
     * of this thread. This method will return a zero-length array if
     * this thread has not started, has started but has not yet been
     * scheduled to run by the system, or has terminated.
     * If the returned array is of non-zero length then the first element
     * of the array represents the top of the stack, which is the most recent
     * method invocation in the sequence. The last element of the array
     * represents the bottom of the stack, which is the least recent method
     * invocation in the sequence.
     *
     * @return an array of StackTraceElement each represents on stack frame.
     */
    public StackTraceElement[] getStateTrace() {
        return null;
    }

    /**
     * Returns a reference to the currently executing thread object.
     *
     * @return the currently executing thread.
     */
    // public static native Thread currentThread();

    /**
     * Causes the currently executing thread to sleep (temporarily cease
     * execution) for the specified number of milliseconds, subject to
     * the precision and accuracy of system timers and schedulers. The
     * thread does not lose ownership of any monitors.
     *
     * @param millis the length of time to sleep in milliseconds.
     * @throws IllegalArgumentException if the value of millis is negative
     * @throws InterruptedException
     *      if any thread has interrupted the current thread. The
     *      interrupted status of the current thread is cleared
     *      when this exception is thrown.
     */
    public static native void sleep(long millis) throws InterruptedException;

    // Tests if this thread is alive, a thread is alive if it has
    // been started and has not yet died.
    public final native boolean isAlive();

    /**
     * Waits at most millis milliseconds for this thread to
     * die. A timeout of {@code 0} means to wait forever.
     *
     * This implementation uses a loop of {@code this.wait} calls
     * conditioned on {@code this.isAlive}. As a thread terminates the
     * {@code this.notifyAll} method is invoked. It is recommended that
     * applications not use wait, notify, or notifyAll on Thread instance
     *
     * @param millis the time to wait in milliseconds
     * @throws InterruptedException
     *      if any thread has interrupted the current thread. The
     *      interrupted status of the current thread is
     *      cleared when this exception is thrown.
     */
    public final synchronized void join(long millis) throws InterruptedException {
        // ...
        if (millis < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }

        // My question is: where is (and who calls) the notify() or notifyAll()
        // when the thread completes, so that it wakes up the calling thread?
        // Answer:
        // notifyAll (or its native equivalent) is called in ensure_join in
        // src/share/vm/runtime/thread.cpp, at line 1526 in the current version,
        // which is called from JavaThread::exit in the same file.
        if (millis == 0) {
            while (isAlive()) {
                // Causes the current thread to wait until ....
                // 当前线程是调用 t.join() 线程方法的线程
                // 当前线程会在 t 对象的 wait set进行等待
                wait(0);
            }
        } else {

        }
    }

    /**
     * A thread state. A thread can be in one of the following states:
     *  NEW:
     *  A thread that has not yet started is in this state.
     *
     *  RUNNABLE:
     *  A thread executing in the Java virtual machine is in this state.
     *
     *  BLOCKED:
     *  A thread that is blocked waiting for a monitor lock is in this state.
     *
     *  WAITING:
     *  A thread that is waiting indefinitely for another thread to
     *  perform a particular action is in this state.
     *
     *  TIMED_WAITING:
     *  A thread that is waiting for another thread to perform an action
     *  for up to a specified waiting time is in this state.
     *
     *  TERMINATED:
     *  A thread that has exited is in this state.
     *
     *  A thread can be in only one state at a given point in time.
     *  These states are virtual machine states which do not reflect
     *  any operating system thread states.
     *
     *  @see #getState
     */
    public enum State {
        /**
         * Thread state for a thread which has not yet started.
         */
        NEW,
        /**
         * Thread state for a runnable thread. A thread in the runnable
         * state is executing in the Java virtual machine but it may
         * be waiting for other resources from the operating system
         * such as processor.
         */
        RUNNABLE,
        /**
         * Thread state for a thread blocked waiting for a monitor lock.
         * A thread in the blocked state is waiting for a monitor lock
         * to enter a synchronized block/method or
         * reenter a synchronized block/method after calling
         * Object.wait().
         */
        BLOCKED,
        /**
         * Thread state for a waiting thread.
         * A thread is in teh waiting state due to calling one of the
         * following methods:
         *  Object.wait with no timeout
         *  Thread.join with no timeout
         *  LockSupport.park
         *
         * A thread in the waiting state is waiting for another thread to
         * perform a particular action.
         *
         * For example, a thread that has called Object.wait() on an
         * object is waiting for another thread to call Object.notify()
         * or Object.notifyAll() on that object. A thread that has called
         * Thread.join() is waiting for a specified thread to terminate.
         */
        WAITING,
        /**
         * Thread state for a waiting thread with a specified waiting time.
         * A thread is in the timed waiting state due to calling on of
         * the following methods with a specified positive waiting time:
         *  Thread.sleep();
         *  Object.wait with timeout
         *  Thread.join with timeout
         *  LockSupport#parkNanos
         *  LockSupport#parkUntil
         */
        TIMED_WAITING,
        /**
         * Thread state for a terminated thread.
         * The thread has completed execution.
         */
        TERMINATION;
    }

    /**
     * Returns the state of this thread.
     * This method is designed for use in monitoring of the system state,
     * not for synchronization control.
     *
     * @return this thread's state.
     */
    public State getState() {
        return null;
        // get current thread state
        // return sun.misc.VM.toThreadState(threadStatue);
    }

}
