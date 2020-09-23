package com.github.ryan.data_structure.concurrent.lock.aqs;

import java.util.concurrent.TimeUnit;

/**
 * Lock 提供了更多的灵活性和功能，但是也对使用要求更高
 * 而且Lock还可以提供额外的语义，比如公平性，重入性，死锁检测等
 *
 * Lock implementation provide more extensive locking operations
 * than can be obtained using synchronized methods and statements.
 * They allow more flexible structuring, may have quite different
 * properties, and may support multiple associated Condition objects.
 *
 * A lock is a tool for controlling access to a shard resource by
 * multiple threads. Commonly, a lock provides exclusive access to a
 * shared resource: only one thread at a time can acquire the lock and
 * all access to the shared resource requires that the lock be acquired
 * first. However, some locks may allow concurrent access to
 * a shared resource, such as the read lock of a ReadWriteLock.
 *
 * The use of synchronized methods or statements provides access to the
 * implicit monitor lock associated with every object, but forces all lock
 * acquisition and release to occur in a block-structured way:
 * when multiple locks are acquired they must be released in the opposite
 * order, and all locks must be released in the same lexical scope in which
 * they were acquired.
 *
 * Implementation of the Lock interface allow a lock to be acquired and released
 * in different scopes, and allowing multiple locks to be acquired and released
 * in any order.
 * With this increased flexibility comes additional responsibility. The absence of
 * block-structured locking removes the automatic release of locks that occurs with
 * synchronized methods and statements. In most cases, the following idiom should
 * be used:
 * Lock l = ...
 * l.lock();
 * try {
 *     // access the resource protected by this lock
 * } finally {
 *     l.unlock();
 * }
 *
 * Lock implementation provide additional functionality over the use of
 * synchronized methods and statements by providing a non-blocking
 * attempt to acquire a Lock(#tryLock()), an attempt to acquire the lock
 * that can be interrupted(#lockInterruptibly), and an attempt to acquire
 * the lock that can timeout(#tryLock(long, TimeUnit)).
 *
 * A Lock class can also provide behavior and semantics that is quite
 * different from that of the implicit monitor lock,
 * such as guaranteed ordering, non-reentrant usage, or deadlock detection.
 * If an implementation provides such specialized semantics then the
 * implementation must document those semantics.
 *
 * Memory Synchronization:
 * All Lock implementations must enforce the same memory synchronization
 * semantics as provided by the built-in monitor lock
 */
public interface Lock {

    /**
     * Acquires the lock.
     *
     * If the lock is not available then the current thread becomes
     * disabled for thread scheduling purposes and lies dormant until
     * the lock has been acquire.
     */
    void lock();

    void lockInterruptibly() throws InterruptedException;

    /**
     * Acquires the lock only if it is free at the time of invocation.
     *
     * Acquires the lock if it is available and returns immediately
     * with the value true.
     * If the lock is not available then this method will return immediately
     * with the value false.
     * A typical usage idiom for this method would be:
     * Lock lock = ...;
     * if (lock.tryLock()) {
     *     try {
     *         // manipulate protected state
     *     } finally {
     *         lock.unlock();
     *     }
     * } else {
     *     // perform alternative actions
     * }
     *
     * @return true if the lock was acquired and false otherwise
     */
    boolean tryLock();

    boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException;

    /**
     * Release the lock.
     *
     * Implementation Considerations
     * A Lock implementation will usually impose restrictions on which
     * thread can release a lock (typically only the holder of the lock
     * can release it) and may throw an (unchecked) exception if the
     * restriction is violated.
     * Any restrictions and the exception type must be
     * documented by that Lock implementation.
     */
    void unlock();

    /**
     * Returns a new Condition instance that is bound to this Lock instance.
     * @return
     */
    // Condition newCondition();
}
