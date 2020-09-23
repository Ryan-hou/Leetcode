package com.github.ryan.data_structure.concurrent.blocking_queue;

/**
 * @author ryan.houyl@gmail.com
 * @description
 *
 * A Queue that additionally supports operations that wait for
 * the queue to become non-empty when retrieving an element, and
 * wait for space to become available in the queue when storing
 * an element.
 *
 * BlockingQueue methods come in four forms, with different ways
 * of handling operations that cannot be satisfied immediately, but
 * may be satisfied at some point in the future:
 * one throws an exception, the second returns special value (either
 * null or false, depending on the operation), the third
 * blocks the current thread indefinitely until the operation can succeed,
 * and the fourth blocks for only a given maximum time limit before
 * giving up.
 *
 * // Insert
 * add(e)
 * offer(e)
 * put(e)
 * offer(e, time, unit)
 *
 * // Remove
 * remove()
 * poll()
 * take()
 * poll(time, unit)
 *
 * // Examine
 * element()
 * peek()
 *
 * A BlockingQueue does not accept null elements.
 * Implementations throw NullPointerException on attempts
 * to add, put or offer a null. A null is used as
 * a sentinel value to indicate failure of poll operations.
 *
 * BlockingQueue implementations are designed to be used
 * primarily for producer-consumer queues, but additionally
 * support the Collection interface.
 *
 * BlockingQueue implementations are thread-safe. All queuing
 * methods achieve their effects atomically using internal locks
 * or other forms of concurrency control.
 * However, the bulk Collection operations addAll, containsAll,
 * retainAll and removeAll are not necessarily performed atomically
 * unless specified otherwise in an implementation. So it is
 * possible, for example, for addAll(c) to fail (throwing an exception)
 * after adding only some of the elements in c.
 *
 * A BlockingQueue does not intrinsically support any kind of close or
 * shutdown operation to indicate that no more items will be added.
 * The needs and usage of such feature tend be implementation-dependent.
 * For example, a common tactic is for producers to insert special
 * end-of-stream or poison objects, that are interpreted accordingly
 * when taken by consumers.
 *
 * @className BlockingQueue
 * @date October 18,2018
 */
public interface BlockingQueue<E> {

    /**
     * Inserts the specified element into this queue, waiting if necessary
     * for space to become available.
     *
     * @param e the element to add
     * @throws InterruptedException if interrupted while waiting
     * @throws ClassCastException if the class of the specified element
     *          prevents it from being added to this queue
     * @throws NullPointerException if the specified element is null
     * @throws IllegalArgumentException if some property of the specified
     *          element prevents it from being added to this queue
     */
    void put(E e) throws InterruptedException;


    /**
     * Retrieve and removes the head of this queue, waiting if necessary
     * until an element becomes available.
     *
     * @return the head of this queue
     * @throws InterruptedException if interrupted while waiting
     */
    E take() throws InterruptedException;
}
