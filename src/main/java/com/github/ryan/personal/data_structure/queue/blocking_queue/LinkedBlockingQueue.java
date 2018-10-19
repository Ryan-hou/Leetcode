package com.github.ryan.personal.data_structure.queue.blocking_queue;

import com.github.ryan.personal.data_structure.queue.blocking_queue.BlockingQueue;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * An optionally-bounded BlockingQueue based on linked nodes.
 * This queue orders elements FIFO.
 * The head of the queue is that element that has been on the
 * queue the longest time.
 * The tail of the queue is that element that has been
 * on the queue the shortest time. New elements are inserted at
 * the tail of the queue, and the queue retrieval operations
 * obtain elements at the head of the queue.
 * Linked queues typically have higher throughput than array-based
 * queues but less predictable performance in most concurrent applications.
 *
 * The optional capacity bound constructor argument serves as a
 * way to prevent excessive queue expansion. The capacity, if unspecified,
 * is equal to Integer.MAX_VALUE. Linked nodes are dynamically created upon
 * each insertion unless this would bring the queue above capacity.
 *
 * @className LinkedBlockingQueue
 * @date October 18,2018
 */
public class LinkedBlockingQueue<E> implements BlockingQueue<E> {


    /**
     *  A variant of the "two lock queue" algorithm. The putLock gates
     *  entry to put (and offer), and has an associated condition for
     *  waiting puts. Similarly for the takeLock. The "count" field
     *  that they both rely on is maintained as an atomic to avoid
     *  needing to get both locks in most cases. Also, to minimize need
     *  for puts to get takeLock and vice-versa, cascading notifies are
     *  used. When a put notices that is has enabled at least one take,
     *  it signals taker. That taker in turn signals others if more
     *  items have been entered since the signal. And symmetrically for
     *  takes signalling puts. Operations such as remove(Object) and
     *  iterators acquire both locks.
     *
     *  Visibility between writers and readers is provided as follows:
     *
     *  Whenever an element is enqueued, the putLock is acquired and
     *  count updated. A subsequent reader guarantees visibility to the
     *  enqueued Node by either acquiring the putLock (vi fullyLock)
     *  or by acquiring the takeLock, and then reading n = count.get();
     *  this gives visibility to the first n items.
     *
     */

    /**
     * Linked list node class
     */
    static class Node<E> {
        E item;

        /**
         * One of:
         * - the real successor Node
         * - null, meaning there is no successor (this is the last node)
         * - this Node, meaning the successor is head.next
         *
         */
        Node<E> next;
        Node(E x) { item = x; }
    }

    /** The capacity bound, or Integer.MAX_VALUE if none **/
    private final int capacity;

    /**
     * Current number of elements
     **/
    private final AtomicInteger count = new AtomicInteger();

    /**
     * Head of linked list.
     * Invariant: head.item == null
     */
    transient Node<E> head;

    /**
     * Tail of linked list.
     * Invariant: last.next == null
     */
    private transient Node<E> last;

    /** Lock head by take, poll, etc **/
    private final ReentrantLock takeLock = new ReentrantLock();

    /** Wait queue for waiting takes **/
    private final Condition notEmpty = takeLock.newCondition();

    /** Lock held by put, offer, etc **/
    private final ReentrantLock putLock = new ReentrantLock();

    /** Wait queue for waiting puts **/
    private final Condition notFull = putLock.newCondition();

    public LinkedBlockingQueue() {
        this.capacity = Integer.MAX_VALUE;
        last = head = new Node<>(null);
    }

    @Override
    public void put(E e) throws InterruptedException {
        if (e == null) throw new NullPointerException();

        // Note: convention in all put/take/etc is to preset local var
        // holding count negative to indicate failure unless set.
        int c = -1;
        Node<E> node = new Node<>(e);
        final ReentrantLock putLock = this.putLock;
        final AtomicInteger count = this.count;
        putLock.lockInterruptibly();
        try {

            /**
             * Note that count is used in wait guard even thought
             * is is not protected by lock. This works because count
             * can only decrease at this point( all other puts are shut
             * out by lock), and we (or some other waiting put) are
             * signaled if it is ever changes from capacity. Similarly
             * for all other uses of count in other wait guards.
             */
            while (count.get() == capacity) {
                notFull.await();
            }

            enqueue(node);
            c = count.getAndIncrement();
            if (c + 1 < capacity) {
                notFull.signal();
            }
        } finally {
            putLock.unlock();
        }
        if (c == 0) {
            signalNotEmpty();
        }
    }

    /**
     * Signals a waiting take. Called only from put/offer (which do not
     * otherwise ordinary lock takeLock.)
     */
    private void signalNotEmpty() {
        final ReentrantLock takeLock = this.takeLock;
        takeLock.lock();
        try {
            notEmpty.signal();
        } finally {
            takeLock.unlock();
        }

    }

    /**
     * Signals a waiting put. Called only from take/poll.
     */
    private void signalNotFull() {
        final ReentrantLock putLock = this.putLock;
        putLock.lock();
        try {
            notFull.signal();
        } finally {
            putLock.unlock();
        }
    }

    /**
     * Links node at end of queue.
     *
     * @param node
     */
    private void enqueue(Node<E> node) {
        // assert putLock.isHeldByCurrentThread();
        // assert last.next == null;
        last = last.next = node;
    }

    /**
     * Removes a node from head of queue.
     *
     * @return the node
     */
    private E dequeue() {
//        assert takeLock.isHeldByCurrentThread();
//        assert head.item == null;
        Node<E> h = head;
        Node<E> first = h.next;
        h.next = h; // help GC
        head = first;
        E x = first.item;
        first.item = null;
        return x;
    }

    @Override
    public E take() throws InterruptedException {
        E x;
        int c = -1;
        final AtomicInteger count = this.count;
        final ReentrantLock takeLock = this.takeLock;
        takeLock.lockInterruptibly();
        try {
            while (count.get() == 0) {
               notEmpty.await();
            }
            x = dequeue();
            c = count.getAndIncrement();
            if (c > 1) {
                notEmpty.signal();
            }
        } finally {
            takeLock.unlock();
        }
        if (c == capacity) {
            signalNotFull();
        }
        return x;
    }
}
