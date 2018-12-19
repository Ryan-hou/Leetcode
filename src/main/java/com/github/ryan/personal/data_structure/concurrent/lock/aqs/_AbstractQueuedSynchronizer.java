package com.github.ryan.personal.data_structure.concurrent.lock.aqs;

import sun.misc.Unsafe;

import java.util.concurrent.locks.LockSupport;

/**
 * Provides a framework for implementing blocking locks and related
 * synchronizers (semaphores, events, etc) that rely on
 * first-in-first-out (FIFO) wait queues.
 * This class is designed to be a useful basis for most kinds of synchronizers that rely on
 * a single atomic int value to represent state. Subclasses
 * must define the protected methods that change this state, and which
 * define what that state means in terms of this object being acquired
 * or released. Given these, the other methods in this class carry
 * out all queuing and blocking mechanics.
 *
 * Subclasses should be defined as non-public internal helper
 * classes that are used to implement the synchronization properties
 * of their enclosing class. Class AbstractQueuedSynchronizer does not
 * implement any synchronization interface. Instead it defines methods such
 * as #acquireInterruptibly that can be invoked as appropriate by concrete
 * locks and related synchronizers to implement their public methods.
 *
 * Usage:
 * To use this class as the basis of a synchronizer, redefine the
 * following methods, as applicable, by inspecting and/or modifying
 * the synchronization state using #getState, #setState and/or
 * #compareAndSetState:
 *
 * #tryAcquire
 * #tryRelease
 * #tryAcquireShared
 * #tryReleaseShared
 * #isHeldExclusively
 * Implementations of these methods must be internally thread-safe, and should
 * in general be short and not block. Defining these methods is the only
 * supported means of using this class. All other methods are declared
 * final because they cannot be independently varied.
 *
 * Even though this class is based on an internal FIFO queue, it does
 * not automatically enforce FIFO acquisition policies.
 *
 * Because checks in acquire are invoked before enqueuing, a newly acquiring
 * thread may barge ahead of others that are blocked and queued. However, you can
 * if desired, define tryAcquire and/or tryAcquireShared to disable barging by
 * internally invoking one or more of the inspection methods, thereby providing
 * a fair FIFO acquisition order.
 * In particular, most fair synchronizers can define tryAcquire to return
 * false if {@link #hasQueuedPredecessors} (a method specifically designed to be used
 * by fair synchronizers) returns true. Other variations are possible.
 *
 * Throughput and scalability are generally highest for the default
 * barging strategy.
 * Also, while acquires do not spin in the usual sense, they may perform
 * multiple invocations of #tryAcquire interspersed with other computations
 * before blocking. This gives most of the benefits of spins when
 * exclusive synchronization is only briefly held, without most of
 * the liabilities when it isn't.
 */
public class _AbstractQueuedSynchronizer {

    /**
     * Wait queue node class.
     *
     * The wait queue is a variant of a CLH lock queue.
     * CLH locks are normally used for spinlocks. We instead use them
     * for blocking synchronizers, but use the same basic tactic of
     * holding some of the control information about a thread in the
     * predecessor of its node.
     * A "status" field in each node keeps
     * track of whether a thread should block. A node is signalled when
     * its predecessor releases.
     * The status field does NOT control whether threads are granted locks
     * etc though.
     *
     * A thread may try to acquire if it is first in the queue.
     * But being first does not guarantee success; It only gives the right
     * to contend. So the currently released contender thread may need to rewait.
     *
     * Insertion into a CLH queue requires only a single atomic operation on
     * "tail". Similarly, dequeuing involves only updating the "head". However,
     * it takes a bit more work for nodes to determine who their successors
     * are, in part to deal with possible cancellation due to timeouts
     * and interrupts.
     *
     * The "prev" links (not used in original CLH locks), are mainly
     * needed to handle cancellation. If a node is cancelled, its successor
     * is (normally) relinked to a non-cancelled predecessor.
     *
     * We also use "next" links to implement blocking mechanics.
     * The thread id for each node is kept in its own node, so a
     * predecessor signals the next node to wake up by traversing
     * next link to determine which thread it is. Determination of
     * successor must avoid races with newly queued nodes to set
     * the "next" fields of their predecessors. This is solved when
     * necessary by checking backwards from the atomically updated
     * "tail" when a node's successor appears to be null.
     * (Or, said differently, the next-links are an optimization
     * so that we don't usually need a backward scan).
     *
     * Threads waiting on Conditions use the same nodes, but
     * use an additional link. Conditions only need to link nodes
     * in simple (non-concurrent) linked queues because they are
     * only accessed when exclusively held. Upon await, a node is
     * inserted into a condition queue. Upon signal, the node is
     * transferred to the main queue. A special value of status
     * field is used to mark which queue a node is on.
     */
    static final class Node {
        // Marker to indicate a node is waiting in shard mode
        static final Node SHARED = new Node();
        // Marker to indicate a node is waiting in exclusive mode
        static final Node EXCLUSIVE = null;

        // waitStatus value to indicate thread has cancelled
        static final int CANCELLED = 1;
        // waitStatus value to indicate successor's thread needs unparking
        static final int SIGNAL = -1;
        // waitStatus value to indicate thread is waiting on condition
        static final int CONDITION = -2;
        // waitStatus value to indicate the next acquireShared should
        // unconditionally propagate
        static final int PROPAGATE = -3;

        /**
         * Status field, taking on only the values:
         *  SIGNAL:     The successor of this node is (or will soon be)
         *           blocked (via park), so the current node must
         *           unpark its successor when it releases or cancels.
         *           To avoid races, acquire methods must first indicate
         *           they need a signal, then retry the atomic acquire, and
         *           then, on failure, block.
         *  CANCELLED:   This node is cancelled due to timeout or interrupt.
         *          Nodes never leave this state. In particular,
         *          a thread with cancelled node never again blocks.
         *  CONDITION:   This node is currently on a condition queue.
         *          It will not be used as a sync queue node until
         *          transferred, at which time the status will be set to 0.
         *          (Use of this value here has nothing to do with the other
         *          uses of the field, but simplifies mechanics.)
         *  PROPAGATE:   A releaseShared should be propagated to other
         *          nodes. This is set (for head node only) in doReleaseShared
         *          to ensure propagation continues, even if other operations have
         *          since intervened.
         *  0:      None of the above
         *
         *  The values are arranged numerically to simplify use.
         *  Non-negative values mean that a node doesn't need to
         *  signal. So, most code doesn't need to check for particular
         *  values, just for sing.
         *
         *  The field is initialized to 0 for normal sync nodes, and
         *  CONDITION for condition nodes. It is modified using CAS
         *  (or when possible, unconditional volatile writes).
         */
        volatile int waitStatus;

        /**
         * Link to predecessor node that current node/thread relies on
         * for checking waitStatus. Assigned during enqueuing, and nulled
         * out (for sake of GC) only upon dequeuing.
         * Also, upon cancellation of a predecessor, we short-circuit while
         * finding a non-cancelled one, which will always exist
         * because the head node is never cancelled:
         * A node becomes
         * head only as a result of successful acquire. A cancelled thread
         * never succeeds in acquiring, and a thread only cancels itself,
         * not any other node.
         */
        volatile Node prev;

        /**
         * Link to the successor node that the current node/thread
         * unparks upon release.
         * Assigned during enqueuing, adjusted when bypassing
         * cancelled predecessors, and nulled out (for sake of GC) when dequeued.
         *
         * The enq operation does not assign next field of a predecessor until
         * after attachment, so seeing a null next field does not necessarily mean
         * that node is at end of queue. However, if a next field appears
         * to be null, we can scan prev's from the tail to double-check.
         * The next field of cancelled nodes is set to point to the node itself
         * instead of null, to make life easier for isOnSyncQueue.
         */
        volatile Node next;

        // The thread that enqueued this node.
        // Initialized on construction and nulled out after use.
        volatile Thread thread;

        /**
         * Link to next node waiting on condition, or the special
         * value SHARED.
         * Because condition queues are accessed only
         * when holding in exclusive mode, we just need a simple
         * linked queue to hold nodes while they are waiting on
         * conditions.
         * They are then transferred to the queue to
         * re-acquire. And because conditions can only be exclusive,
         * we save a field by using special value to indicate shared
         * mode.
         */
        Node nextWaiter;

        // Returns true if node is waiting in shared mode.
        final boolean isShared() { return nextWaiter == SHARED; }

        /**
         * Returns previous node, or throws NullPointerException if null.
         * Use when predecessor cannot be null. The null check could
         * be elided, but is present to help the VM.
         *
         * @return the predecessor of this node
         * @throws NullPointerException
         */
        final Node predecessor() throws NullPointerException {
           Node p = prev;
            if (p == null) {
                throw new NullPointerException();
            } else {
                return p;
            }
        }

        Node() { // Used to established initial head or SHARED marker
        }

        Node(Thread thread, Node mode) {
            // Used by addWaiter
            this.nextWaiter = mode;
            this.thread = thread;
        }

        Node(Thread thread, int waitStatus) {
            // Used by Condition
            this.waitStatus = waitStatus;
            this.thread = thread;
        }
    }

    /**
     * Head of the wait queue, lazily initialized. Except for
     * initialization, it is modified only via method setHead.
     * Note: If head exists, its waitStatus is guaranteed not
     * to be CANCELLED.
     */
    private transient volatile Node head;

    /**
     * Tail of the wait queue, lazily initialized. Modified only via
     * method enq to add new wait node.
     */
    private transient volatile Node tail;


    // ------------------------- Synchronization state operation -----------------
    // The synchronization state.
    private volatile int state;

    /**
     * Returns the current value of synchronization state.
     * This operation has memory semantics of a volatile read.
     * @return the current value
     */
    protected final int getState() {
        return state;
    }

    /**
     * Sets the value of synchronization state.
     * This operation has memory semantic of a volatile write.
     * @param newState the new state value
     */
    protected final void setState(int newState) {
        state = newState;
    }

    /**
     * Atomically sets synchronization state to the given updated
     * value if the current state value equals the expected value.
     * This operation has memory semantics of a volatile read and write.
     *
     * @param expect the expected value
     * @param update the new value
     * @return true if successful. False return indicates that the actual
     *      value was not equal to the expected value.
     */
    protected final boolean compareAndSetState(int expect, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

    // ---------------------------------- Queuing utilities --------------------

    /**
     * The number of nanoseconds for which it is faster to spin
     * rather than to use timed park. A rough estimate suffices
     * to improve responsiveness with very short timeouts.
     */
    static final long spinForTimeoutThreshold = 1000L;
    /**
     * Inserts node into queue, initializing if necessary.
     * @param node the node to insert
     * @return node's predecessor
     */
    // 队尾入队
    private Node enq(final Node node) {
        for (;;) {
            Node t = tail;
            if (t == null) { // Must initialize
                if (compareAndSetHead(new Node())) {
                    tail = head; // dummy head/tail
                }
            } else {
                node.prev = t;
                if (compareAndSetTail(t, node)) {
                    // An AbstractQueuedSynchronizer queue node contains a next link to its successor.
                    // But because there are no applicable techniques for lock-free atomic
                    // insertion of double-linked list nodes using compareAndSet,
                    // this link is not atomically set as part of insertion; it is simply assigned:

                    // maybe need double-check, why?
                    // The enq operation does not assign next field of a predecessor until
                    // after attachment, so seeing a null next field does not necessarily mean
                    // that node is at end of queue. However, if a next field appears
                    // to be null, we can scan prev's from the tail to double-check.
                    t.next = node;
                    return t;
                }
            }
        }
    }

    // 当前线程构造node节点，入队
    /**
     * Creates and enqueues node for current thread and given mode.
     *
     * @param mode Node.EXCLUSIVE for exclusive, Node.SHARED for shared
     * @return the new node
     */
    private Node addWaiter(Node mode) {
        Node node = new Node(Thread.currentThread(), mode);
        // Try the fast path of enq; backup to full enq on failure
        Node pred = tail;
        if (pred != null) {
            node.prev = pred;
            if (compareAndSetTail(pred, node)) {
                pred.next = node;
                return node;
            }
        }

        // 队尾入队
        enq(node);
        return node;
    }

    // 队首出队
    /**
     * Sets head of queue to be node, thus dequeuing.
     * Called only by acquire methods. Also nulls out unused fields
     * for sake of GC and to suppress unnecessary signals and traversals.
     *
     * @param node the node
     */
    private void setHead(Node node) {
        head = node;
        node.thread = null;
        node.prev = null;
    }

    /**
     * Wakes up node's successor, if one exists.
     *
     * @param node the node
     */
    private void unparkSuccessor(Node node) {
        /**
         * If status is negative (i.e., possibly needing signal) try
         * to clear in anticipation of signalling. It is OK if this
         * fails or if status is changed by waiting thread.
         */
        int ws = node.waitStatus;
        if (ws < 0) {
            compareAndSetWaitStatus(node, ws, 0);
        }

        /**
         * Thread to unpark is held in successor, which is normally
         * just the next node. But if cancelled or apparently null,
         * traverse backwards from tail to find the actual
         * non-cancelled successor.
         */
        Node s = node.next;
        if (s == null || s.waitStatus > 0) {
            s = null;
            for (Node t = tail; t != null && t != node; t = t.prev) {
                if (t.waitStatus <= 0) {
                    s = t;
                }
            }
        }
        if (s != null) {
            LockSupport.unpark(s.thread);
        }
    }

    // Convenience method to interrupt current thread.
    static void selfInterrupt() {
        Thread.currentThread().interrupt();
    }

    // Convenience method to park and then check if interrupted
    private final boolean parkAndCheckInterrupt() {
        LockSupport.park(this);
        return Thread.interrupted();
    }

    /**
     * Checks and updates status for a node that failed to acquire.
     * Returns true if thread should block. This is the main signal
     * control in all acquire loops. Requires that pre == node.prev.
     *
     * @param pred node's predecessor holding status
     * @param node the node
     * @return true if thread should block
     */
    private static boolean shouldParkAfterFailedAcquire(Node pred, Node node) {
        int ws = pred.waitStatus;
        if (ws == Node.SIGNAL) {
            // This node has already set status asking a release
            // to signal it, so it can safely park.
            return true;
        }
        if (ws > 0) {
            // Predecessor was cancelled. Skip over predecessors and
            // indicate retry
            do {
                pred = pred.prev;
                node.prev = pred;
            } while (pred.waitStatus > 0);
            pred.next = node;
        } else {
            // waitStatus must be 0 or PROPAGATE. Indicate that we
            // need a signal, but don't park yet. Caller will need to
            // retry to make sure it cannot acquire before parking.
            compareAndSetWaitStatus(pred, ws, Node.SIGNAL);
        }
        return false;
    }

    /**
     * Various flavors of acquire, varying in exclusive/shared and
     * control modes. Each is mostly the same, but annoyingly
     * different.
     */


    // 入队线程尝试acquire
    /**
     * Acquires in exclusive uninterruptible mode for thread already in
     * queue. Used by condition wait methods as well as acquire.
     *
     * @param node
     * @param arg
     * @return true if interrupted while waiting
     */
    final boolean acquireQueued(final Node node, int arg) {
        boolean failed = true;
        try {
            boolean interrupted = false;
            for (;;) {
                final Node p = node.predecessor();
                if (p == head && tryAcquire(arg)) {
                    setHead(node);
                    p.next = null; // help GC
                    failed = false;
                    return interrupted;
                }

                if (shouldParkAfterFailedAcquire(p, node) &&
                        parkAndCheckInterrupt()) {
                    interrupted = true;
                }
            }
        } finally {
            if (failed) {
                // cancelAcquire(node);
            }
        }
    }

    // Acquires in exclusive interruptible mode
    private void doAcquireInterruptibly(int arg)
            throws InterruptedException {
        final Node node = addWaiter(Node.EXCLUSIVE);
        boolean failed = true;
        try {
            for (;;) {
                final Node p = node.predecessor();
                if (p == head && tryAcquire(arg)) {
                    setHead(node);
                    p.next = null; // help GC
                    failed = false;
                    return;
                }
                if (shouldParkAfterFailedAcquire(p, node) &&
                        parkAndCheckInterrupt()) {
                    // 抛出 InterruptedException (checked exception)
                    throw new InterruptedException();
                }
            }
        } finally {
            if (failed) {
                // cancelAcquire(node);
            }
        }
    }

    /**
     * Acquires in exclusive time mode.
     *
     * @param arg
     * @param nanosTimeout max wait time
     * @return true if acquired
     * @throws InterruptedException
     */
    private boolean doAcqurieNanos(int arg, long nanosTimeout)
            throws InterruptedException {
        if (nanosTimeout <= 0L) {
            return false;
        }
        final long deadline = System.nanoTime() + nanosTimeout;
        final Node node = addWaiter(Node.EXCLUSIVE);
        boolean failed = true;
        try {
            for (;;) {
                final Node p = node.predecessor();
                if (p == head && tryAcquire(arg)) {
                    setHead(node);
                    p.next = null; // help GC
                    failed = false;
                    return true;
                }
                nanosTimeout = deadline - System.nanoTime();
                if (nanosTimeout <= 0L) {
                    return false;
                }
                if (shouldParkAfterFailedAcquire(p, node)
                        && nanosTimeout > spinForTimeoutThreshold) {
                    LockSupport.parkNanos(this, nanosTimeout);
                }
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }
            }
        } finally {
            if (failed) {
                // cancelAcquire(node);
            }
        }
    }

    /**
     * Attempts to acquire in exclusive mode, aborting if interrupted,
     * and failing if the given timeout elapses. Implemented by first
     * checking interrupt status, then invoking at least once #tryAcquire
     * return on success. Otherwise, the thread is queued, possibly repeatedly
     * blocking and unblocking, invoking #tryAcquire until success or the thread
     * is interrupted or the timeout elapses. This method can be used to implement
     * method Lock#tryLock(long, TimeUnit).
     *
     * @param arg          the acquire argument.
     * @param nanosTimeout the maximum number of nanoseconds to wait
     * @return true if acquired; false if timed out
     * @throws InterruptedException if the current thread is interrupted
     */
    public final boolean tryAcquireNanos(int arg, long nanosTimeout)
            throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        return tryAcquire(arg) ||
                doAcqurieNanos(arg, nanosTimeout);

    }

    // ----------------------------- Main exported methods ---------------------------

    /**
     * Attempts to acquire in exclusive mode. This method should query
     * if the state of the object permits it to be acquired in the
     * exclusive mode, and if so to acquire it.
     *
     * This method is always invoked by the thread performing
     * acquire. If this method reports failure, the acquire method
     * may queue the thread, if it is not already queued, until it is
     * signalled by a release from some other thread. This can be used
     * to implement method Lock#tryLock()
     *
     * @param arg
     * @return
     */
    protected boolean tryAcquire(int arg) {
        throw new UnsupportedOperationException();
    }

    /**
     * Attempts to set the state to reflect a release in exclusive
     * mode.
     * This method is always invoked by the thread performing release.
     * @param arg
     * @return
     */
    protected boolean tryRelease(int arg) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns true if synchronization is held exclusively with
     * respect to the current (calling) thread. This method is invoked
     * upon each call to a non-waiting ConditionObject method.
     * (Waiting methods instead invoke #release)
     * @return
     */
    protected boolean isHeldExclusively() {
        throw new UnsupportedOperationException();
    }

    /**
     * Acquires in exclusive mode, ignoring interrupts. Implemented
     * by invoking at least once #tryAcquire,
     * returning on success. Otherwise the thread is queued, possibly
     * repeatedly blocking and unblocking, invoking #tryAcquire until
     * success. This method can be used to implement method Lock#lock
     *
     * @param arg the acquire argument. This value is conveyed to
     *            tryAcquire but is otherwise uninterpreted and
     *            can represent anything you like.
     */
    public final void acquire(int arg) {
        if (!tryAcquire(arg) &&
                acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
            selfInterrupt();
    }

    /**
     * Acquires in exclusive mode, aborting if interrupted.
     * Implemented by first checking interrupt status, then invoking
     * at least once #tryAcquire, returning on success.
     * Otherwise the thread is queued, possibly repeatedly blocking
     * and unblocking, invoking #tryAcquire until success
     * or the thread is interrupted. This method can be used to
     * implement method Lock#lockInterruptibly.
     *
     * @param arg the acquire argument. This value is conveyed to
     *            #tryAcquire but is otherwise uninterrupted
     *            and can represent anything you like.
     * @throws InterruptedException if the current thread is interrupted
     */
    public final void acquireInterruptibly(int arg)
            throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        if (!tryAcquire(arg)) {
            doAcquireInterruptibly(arg);
        }
    }

    /**
     * Releases in exclusive mode. Implemented by unblocking one or
     * more threads if #tryRelease returns true.
     * This method can be used to implement method Lock#unlock.
     *
     * @param arg the release argument. This value is conveyed to
     *            #tryRelease but is otherwise uninterpreted and
     *            can represent anything you like
     * @return the value returned from #tryRelease
     */
    public final boolean release(int arg) {
        if (tryRelease(arg)) {
            Node h = head;
            if (h != null && h.waitStatus != 0) {
                unparkSuccessor(h);
            }
            return true;
        }
        return false;
    }

    // ------------------------ Queue inspection methods -------------------------

    /**
     * Queries whether any threads are waiting to acquire. Note that
     * because cancellations due to interrupts and timeouts may occur
     * at any time, a true return does not guarantee that any other
     * thread will ever acquire.
     *
     * @return true if there may be other threads waiting to acquire
     */
    public final boolean hasQueuedThreads() { return head != tail; }

    /**
     * Returns true if the given thread is currently queued.
     * @param thread
     * @return true if the given thread is on the queue
     * @throws NullPointerException if the thread is null
     */
    public final boolean isQueued(Thread thread) {
        if (thread == null)
            throw new NullPointerException();
        for (Node p = tail; p != null; p = p.prev) {
            if (p.thread == thread) {
                return true;
            }
        }
        return false;
    }

    /**
     * Queries whether any threads have been waiting to acquire longer
     * than the current thread.
     *
     * An invocation of this method is equivalent to (but may be more
     * efficient than):
     * getFirstQueuedThread() != Thread.currentThread() &&
     * hasQueuedThreads()
     *
     * Note that because cancellations due to interrupts and timeouts
     * may occur at any time, a true return does not
     * guarantee that some other thread will acquire before the current
     * thread. Likewise， it is possible for another thread to win a
     * race to enqueue after this method has returned false, due to
     * the queue being empty.
     *
     * This method is designed to be used by a fair synchronizer to
     * avoid barging.
     * Such a synchronizer's tryAcquire method should return false, and
     * its tryAcquireShared method should return a negative value, if this
     * method returns true (unless this is a reentrant acquire). For example,
     * the tryAcquire method for a fair, reentrant, exclusive mode
     * synchronizer might look like this:
     * protected boolean tryAcquire(int arg) {
     *     if (isHeldExclusively()) {
     *         // A reentrant acquire; increment hold count
     *         return true;
     *     } else if (hasQueuedPredecessors()) {
     *         return false;
     *     } else {
     *         // try to acquire normally
     *     }
     * }
     *
     *
     * @return true if there is a queued thread preceding the current
     * thread, and false if the current thread is at the head of the queue
     * or the queue is empty
     */
    public final boolean hasQueuedPredecessors() {
        // The correctness of this depends on head being initialized
        // before tail and on head.next being accurate if the current
        // thread is first in queue.
        Node t = tail; // Read fields in reverse initialization order
        Node h = head;
        Node s;
        return h != t &&
                ((s = h.next) == null || s.thread != Thread.currentThread());
    }

    // ----------------------- CAS operation -----------------------------------------------

    // Setup to support compareAndSet. We need to natively implement
    // this here: For the sake of permitting future enhancements, we
    // cannot explicitly subclass AtomicInteger, which would be
    // efficient and useful otherwise. So, as the lesser of evils,
    // we natively implement using hotspot intrinsics API.
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final long stateOffset;
    private static final long headOffset;
    private static final long tailOffset;
    private static final long waitStatusOffset;
    static {
        try {
            stateOffset = unsafe.objectFieldOffset
                    (_AbstractQueuedSynchronizer.class.getDeclaredField("state"));
            headOffset = unsafe.objectFieldOffset
                    (_AbstractQueuedSynchronizer.class.getDeclaredField("head"));
            tailOffset = unsafe.objectFieldOffset
                    (_AbstractQueuedSynchronizer.class.getDeclaredField("tail"));
            waitStatusOffset = unsafe.objectFieldOffset
                    (_AbstractQueuedSynchronizer.class.getDeclaredField("waitStatus"));
        } catch (Exception ex) { throw new Error(ex); }
    }

    // CAS head field. Used only by enq
    private final boolean compareAndSetHead(Node update) {
        return unsafe.compareAndSwapObject(this, headOffset, null, update);
    }

    // CAS tail field. Used only by enq.
    private final boolean compareAndSetTail(Node expect, Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }

    // CAS waitStatus field of a node
    private static final boolean compareAndSetWaitStatus(Node node, int expect, int update) {
        return unsafe.compareAndSwapInt(node, waitStatusOffset, expect, update);
    }
}
