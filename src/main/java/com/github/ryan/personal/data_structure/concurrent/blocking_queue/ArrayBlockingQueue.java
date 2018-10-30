package com.github.ryan.personal.data_structure.concurrent.blocking_queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * A bounded BlockingQueue backed by an array. This queue orders elements
 * FIFO. The head of the queue is that element that has been on the
 * queue the longest time. The tail of the queue is that
 * element that has been on the queue the shortest time. New
 * elements are inserted at the tail of the queue, and the
 * queue retrieval operations obtain elements at the head of
 * the queue.
 *
 * This is a classic bounded buffer, in which a fixed-sized array
 * holds elements inserted by producers and extracted by consumer.
 * Once created, the capacity cannot be changed.
 *
 * This class supports an optional fairness policy for ordering
 * waiting producer and consumer thread. By default, this ordering
 * is not guaranteed. However, a queue constructed with fairness set
 * to true grants threads access in FIFO order. Fairness
 * generally decreases throughout but reduces variability and avoids
 * starvation.
 *
 * @param <E> the type of elements held in this collection
 * @className ArrayBlockingQueue
 * @date October 18,2018
 */
public class ArrayBlockingQueue<E> implements BlockingQueue<E> {


    /** The queued items **/
    final Object[] items;

    /** items index for next remove, take, poll or peek**/
    int takeIndex;

    /** items index for next add, put, offer**/
    int putIndex;

    /** Number of elements in the queue. **/
    int count;

    /**
     * Concurrency control uses the classic two-condition algorithm
     * found in any textbook.
     */
    /** Main lock guarding all access **/
    final ReentrantLock lock;

    /** Condition for waiting takes **/
    private final Condition notEmpty;

    /** Condition for waiting puts **/
    private final Condition notFull;

    public ArrayBlockingQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.items = new Object[capacity];
        lock = new ReentrantLock(false);
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    private static void checkNotNull(Object v) {
        if (v == null) {
            throw new NullPointerException();
        }
    }

    @Override
    public void put(E e) throws InterruptedException {
        checkNotNull(e);
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            // use while avoid spurious wakeup
            while (count == items.length) {
                notFull.await();
            }
            enqueue(e);
        } finally {
            lock.unlock();
        }

    }

    /**
     * Inserts element at current put position, advances, and signals.
     * Call only when holding lock.
     */
    private void enqueue(E x) {
//        assert lock.getHoldCount() == 1;
//        assert items[putIndex] == null;
        final Object[] items = this.items;
        items[putIndex] = x;
        if (++putIndex == items.length) {
            putIndex = 0;
        }
        count++;
        notEmpty.signal();
    }

    @Override
    public E take() throws InterruptedException {

        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            return dequeue();
        } finally {
            lock.unlock();
        }
    }

    /**
     *  Extracts element at current take position, advances, and signals.
     *  Call only when holding lock.
     */
    private E dequeue() {
//        assert lock.getHoldCount() == 1;
//        assert items[takeIndex] != null;
        final Object[] items = this.items;
        E x = (E) items[takeIndex];
        items[takeIndex] = null;
        if (++takeIndex == items.length) {
            takeIndex = 0;
        }
        count--;
        notFull.signal();
        return x;
    }
}
