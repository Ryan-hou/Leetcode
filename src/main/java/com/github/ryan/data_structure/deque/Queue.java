package com.github.ryan.data_structure.deque;

/**
 * A collection designed for holding elements prior to processing.
 * Queues provide additional inserting, extraction, and inspection operations.
 * Each of these methods exists in two forms: one throws an
 * exception if the operation fails, the other returns a special
 * value(either {@code null} or {@code false}, depending on the
 * operation.) The latter form of the insert operation is designed
 * specifically for use with capacity-restricted {@code Queue}
 * implementation; in most implementations, insert operations cannot
 * fail.
 *
 * Throws exception:
 * Insert: {@link Queue#add add(e)}
 * Remove: {@link Queue#remove remove()}
 * Examine: {@link Queue#element element()}
 *
 * Returns special value:
 * Insert: {@link Queue#offer offer(e)}
 * Remove: {@link Queue#poll poll()}
 * Examine: {@link Queue#peek peek()}
 *
 * Queues typically, but do not necessarily, order elements in a
 * FIFO manner. Among the exceptions are priority queue, which order
 * elements according to a supplied comparator, or the elements'
 * natural ordering, and LIFO queues(or stacks) which order the
 * elements LIFO.
 * Whatever the ordering used, the head of the queue is that
 * element which would be removed by a call to {@link #remove()}
 * or {@link #poll()}. In a FIFO queue, all new elements are inserted
 * at the tail of the queue. Other kinds of queues may use different
 * placement rules. Every {@code Queue} implementation
 * must specify its ordering properties.
 *
 * {@code Queue} implementations generally do not allow insertion
 * of {@code null} elements, although some implementations, such as
 * {@link java.util.LinkedList}, do not prohibit insertion of {@code null}.
 * Even in the implementations that permit it, {@code null} should
 * not be inserted into a {@code Queue}, as {@code null} is also
 * used as a special return value by the {@code poll} method to
 * indicate that the queue contains no elements.
 *
 */
public interface Queue<E> {

    /**
     * Inserts the specified element into this queue if it is possible to do so
     * immediately without violating capacity restrictions, returning
     * {@code true} upon success and throwing and {@code IllegalStateException}
     * if no space is currently available.
     *
     * @param e the element to add
     * @return {@code true}
     * @throws IllegalStateException if the element cannot be added at this
     *         time due to capacity restrictions
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this queue
     * @throws NullPointerException if the specified element is null and
     *         this queue does not permit null elements
     * @throws IllegalArgumentException if some property of this element
     *         prevents it from being added to this queue
     */
    boolean add(E e);

    /**
     * Inserts the specified element into this queue if it is possible to
     * do so immediately without violating capacity restriction.
     * When using a capacity-restricted queue, this method is generally
     * preferable to {@link #add}, which can fail to insert an element
     * only by throwing an exception.
     *
     * @param e the element to add
     * @return {@code true} if the element was added to this queue, else
     *      {@code false}
     */
    boolean offer(E e);

    /**
     * Retrieves and removes the head of this queue.
     * This method differs from {@link #poll poll} only in that it throws
     * an exception if this queue is empty.
     *
     * @return the head of this queue
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    E remove();

    /**
     * Retrieves and removes the head of this queue.
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this
     * queue is empty
     */
    E poll();

    /**
     * Retrieves, but does not remove, the head of this queue. This method
     * differs from {@link #peek} only in that it throws an exception
     * if this queue is empty.
     *
     * @return the head of this queue
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    E element();

    /**
     * Retrieves, but does not remove, the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    E peek();
}
