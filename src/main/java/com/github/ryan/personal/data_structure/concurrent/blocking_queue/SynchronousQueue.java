package com.github.ryan.personal.data_structure.concurrent.blocking_queue;

/**
 * A BlockingQueue in which each insert operation must wait for
 * a corresponding remove operation by another thread, and
 * vice versa. A synchronous queue does not have any internal
 * capacity, not even a capacity of one. You cannot peek
 * at a synchronous queue because an element is only
 * present when you try to remove it; you cannot not
 * insert an element (using any method) unless another thread
 * is trying to remove it; you cannot iterate as there is
 * nothing to iterate. The head of the queue is the element
 * that the first queued inserting thread is trying to ad to
 * the queue; if there is no such queued thread then no element
 * is available for removal and poll() will return null.
 * For purposes of other Collection methods (for example contains),
 * a SynchronousQueue acts as an empty collection. This queue
 * does not permit null elements.
 *
 * Synchronous queues are similar to rendezvous channels used in
 * CSP and Ada. They are well suited for handoff designs, in which
 * an object running in one thread must sync up with an object running
 * in another thread in order to hand it some information, event, or
 * task.
 *
 * This class supports an optional fairness policy for ordering waiting
 * producer and consumer threads. By default, this ordering
 * is not guaranteed. However, a queue constructed with fairness set
 * to tru grants threads access in FIFO order.
 *
 * @param <E> the type of elements held in this collection
 */
public class SynchronousQueue<E> implements BlockingQueue<E> {

    /**
     * This class implements extensions of the dual stack and dual
     * queue algorithm described in "Nonblocking Concurrent Objects
     * with Condition Synchronized"
     * The stack is used for non-fair mode, and the queue
     * for fair mode. The performance of the two is generally
     * similar. FIFO usually supports higher throughput under
     * contention but LIFO maintains higher thread locality in common
     * applications.
     *
     * A dual queue (and similar stack) is one that at any given
     * time either holds "data" -- items provided by put operations,
     * or "requests" -- slots representing take operations, or is empty.
     * A call to "fulfill" (i.e. a call requesting an item
     * from a queue holding data or vice versa) dequeues a
     * complementary node. The most interesting feature of these
     * queues is that any operation can figure out which mode the
     * queue is in, and act accordingly without needing locks.
     *
     * Both the queue and stack extend abstract class Transfer
     * defining the single method transfer that does a put or a
     * take. These are unified into a single method because in dual
     * data structures, the put and take operations are symmetrical,
     * so nearly all code can be combined. The resulting transfer
     * methods are on the long side, but are easier to follow than
     * they would be if broken up into nearly-duplicated parts.
     *
     * The algorithm here differ from the versions in the above paper
     * in extending them for use in synchronous queues, as well as
     * dealing with cancellation. The main differences include:
     *
     *  1. The original algorithms used bit-marked pointers, but
     *     the ones here use mode bits in nodes, leading to a number
     *     of further adaptations.
     *  2. SynchronousQueues must block threads waiting to be become
     *      fulfilled.
     *  3. Support for cancellation via timeout and interrupts,
     *      including cleaning out cancelled nodes/threads
     *      from lists to avoid garbage retention and memory depletion.
     *
     *  Blocking in mainly accomplished using LockSupport park/unpark,
     *  except that nodes that appear to be the next ones to become
     *  fulfilled first spin a bit (on multiprocessors only). On very
     *  synchronous queues, spinning can dramatically improve
     *  throughput. And on less less busy ones, the amount of spinning
     *  is small enough not to be noticeable.
     */

    // Shared internal API for dual stacks and queues
    abstract static class Transferer<E> {
        /**
         * Performs a put or take.
         *
         * @param e if non-null, the item to be handed to a consumer;
         *          if null, requests that transfer return an item
         *          offered by producer.
         * @param timed if this operation should timeout
         * @param nanos the timeout, in nanoseconds
         * @return if non-null, the item provided or received; if null,
         *      the operation failed due to timeout or interrupt --
         *      the caller can distinguish which of these occurred
         *      by checking Thread.interrupted.
         */
        abstract E transfer(E e, boolean timed, long nanos);
    }

    // The numbers of CPUs, for spin control
    static final int NCPU = Runtime.getRuntime().availableProcessors();

    /**
     * The number of times to spin before blocking in timed waits.
     * The value is empirically derived -- it works well across a
     * variety of processors and OSes. Empirically, the best value
     * seems not to vary with number of CPUs (beyond 2) so is just
     * a constant.
     */
    static final int maxTimedSpins = (NCPU < 2) ? 0 : 32;

    /**
     * The number of times to spin before blocking in untimed waits.
     * This is greater than timed value because untimed waits spin
     * faster since they don't need to check times on each spin.
     */
    static final int maxUntimedSpins = maxTimedSpins * 16;

    /**
     * The number of nanoseconds for which it is faster to spin
     * rather than to use timed park. A rough estimate suffices.
     */
    static final long spinForTimeoutThreshold = 1000L;

    private transient volatile Transferer<E> transferer;

    @Override
    public void put(E e) throws InterruptedException {
        if (e == null) throw new NullPointerException();
        if (transferer.transfer(e, false, 0) == null) {
            Thread.interrupted();
            throw new InterruptedException();
        }
    }

    @Override
    public E take() throws InterruptedException {
        E e = transferer.transfer(null, false, 0);
        if (e != null) {
            return e;
        }
        Thread.interrupted();
        throw new InterruptedException();
    }
}
