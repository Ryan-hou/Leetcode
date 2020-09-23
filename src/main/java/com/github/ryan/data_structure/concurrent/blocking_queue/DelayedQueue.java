package com.github.ryan.data_structure.concurrent.blocking_queue;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * An unbounded BlockingQueue of Delayed elements, in which an element
 * can only be taken when its delay has expired. The head of the queue
 * is that Delayed element whose delay expired furthest in the past.
 * If no delay has expired there is no head and poll will return null.
 * Expiration occurs when an element's getDelay(TimeUnit.NANOSECONDS) methods
 * returns a value less than or equal to zero. Even though unexpired elements
 * cannot be removed using take or poll, they are otherwise treated
 * as normal elements. For example, the size method returns the count of
 * both expired and unexpired elements.
 * This queue dode not permit null elements.(Actually PriorityQueue does not
 * permit null elements)
 *
 * @param <E> the type of elements held in this collection
 */
public class DelayedQueue<E extends Delayed> implements BlockingQueue<E> {


    private final transient ReentrantLock lock = new ReentrantLock();
    private final PriorityQueue<E> q = new PriorityQueue<>();

    /**
     * Condition signalled when a newer element becomes available
     * at the head of the queue or a new thread may need to
     * become leader.
     */
    private final Condition available = lock.newCondition();

    /**
     * Thread designated to wait for the element at the head of
     * the queue. This variant of the Leader-Follower pattern
     * serves to minimize unnecessary timed waiting. When a thread
     * becomes the leader, it waits only for next delay to elapse,
     * but other threads await indefinitely. The leader thread must
     * signal some other thread before returning from take() or poll(...),
     * unless some other thread becomes leader in the interim. Whenever
     * the head of the queue is replaced with an element with an
     * earlier expiration time, the leader field is invalidated by being
     * reset to null, and some waiting thread, but not necessarily the
     * current leader, is signalled. So waiting threads must be prepared
     * to acquire and lost leadership while waiting.
     */
    private Thread leader = null;

    public DelayedQueue() {}


    /**
     * Inserts the specified element into this delay queue. As the queue is
     * unbounded this method will never block.
     *
     * @param e the element to add
     * @throws NullPointerException
     */
    @Override
    public void put(E e) {
        offer(e);
    }

    /**
     * Inserts the specified element into this delay queue.
     *
     * @param e the element to add
     * @return true
     * @throws NullPointerException if the specified element is null
     */
    public boolean offer(E e) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            q.offer(e);
            // 新插入的元素在堆顶，也就是过期时间最长的元素
            if (q.peek() == e) {
                leader = null;
                available.signal();
            }
            return true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Retrieves and removes the head of this queue, or returns null
     * if this queue has no elements with an expired delay.
     *
     * @return the head of this queue, or null if this queue
     *      has no elements with an expired delay
     */
    public E poll() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            E first = q.peek();
            if (first == null || first.getDelay(NANOSECONDS) > 0) {
                return null;
            } else {
                return q.poll();
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Retrieves and removes the head of this queue, waiting if necessary
     * until an element with an expired delay is available on this queue.
     *
     * @return the head of this queue
     * @throws InterruptedException
     */
    // core method
    @Override
    public E take() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
           for (;;) {
               E first = q.peek();
               if (first == null) {
                   available.await();
               } else {
                   long delay = first.getDelay(NANOSECONDS);
                   if (delay <= 0) {
                       return q.poll();
                   }
                   first = null; // don't retain ref while waiting
                   if (leader != null) {
                       available.await();
                   } else {
                       Thread thisThread = Thread.currentThread();
                       leader = thisThread;
                       try {
                           available.awaitNanos(delay);
                       } finally {
                           if (leader == thisThread) {
                               leader = null;
                           }
                       }
                   }
               }
           }
        } finally {
            if (leader == null && q.peek() != null) {
                available.signal();
            }
            lock.unlock();
        }
    }
}
