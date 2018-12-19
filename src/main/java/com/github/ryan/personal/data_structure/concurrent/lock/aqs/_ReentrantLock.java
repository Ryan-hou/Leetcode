package com.github.ryan.personal.data_structure.concurrent.lock.aqs;

import java.util.concurrent.TimeUnit;

/**
 * A reentrant mutual exclusion Lock with the same basic behavior
 * and semantics as the implicit monitor lock accessed using
 * synchronized methods and statements, but with extended capabilities.
 *
 *
 */
public class _ReentrantLock implements Lock {

    // Synchronizer providing all implementation mechanics
    private final Sync sync;

    /**
     * Base of synchronization control for this lock. Subclassed
     * into fair and nonfair versions below. Uses AQS state to
     * represent the number of holds on the locl.
     */
    abstract static class Sync extends _AbstractQueuedSynchronizer {
        // Performs Lock#lock. The main reason for subclassing
        // is to allow fast path for nonfair version
        abstract void lock();

        // Performs non-fair tryLock. tryAcquire is implemented in
        // subclass, but both need nonfair try for tryLock method.
        final boolean nonfairTryAcquire(int acquires) {
            final Thread current = Thread.currentThread();
            int c = getState();
            if (c == 0) {
                if (compareAndSetState(0, acquires)) {
                    // setExclusiveOwnerThread(current);
                    return true;
                }
            }
//            else if (current == getExclusiveOwnerThread()) {
//                int nextc = c + acquires;
//                if (nextc < 0) {
//                    // overflow
//                    throw new Error("Maximum lock count exceeded");
//                }
//                setState(nextc);
//                return true;
//            }
            return false;
        }

        protected final boolean tryRelease(int release) {
            int c = getState() - release;
//            if (Thread.currentThread() != getExclusiveOwnerThread())
//                throw new IllegalMonitorStateException();
            boolean free = false;
            if (c == 0) {
                free = true;
                // setExclusiveOwnerThread(null);
            }
            setState(c);
            return free;
        }

//        @Override
//        protected boolean isHeldExclusively() {
//            // return getExclusiveOwnerThread() == Thread.currentThread();
//        }
    }

    // Sync object for non-fair locks
    static final class NonfairSync extends Sync {
        /**
         * Performs lock. Try immediate barge, backing up to normal
         * acquire on failure.
         */
        final void lock() {
            if (compareAndSetState(0, 1)) {
                // setExclusiveOwnerThread(Thread.currentThread());
            }
            else {
                acquire(1);
            }
        }

        protected final boolean tryAcquire(int acquires) {
            return nonfairTryAcquire(acquires);
        }
    }

    public _ReentrantLock() {
        sync = new NonfairSync();
    }

    @Override
    public void lock() {
        sync.lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long timeout, TimeUnit unit)
            throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }
}
