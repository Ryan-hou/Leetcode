package com.github.ryan.data_structure.deque;

/**
 * A liner collection that supports element insertion and removal at
 * both ends. The name deque is short for "double ended queue"
 * and is usually pronounced "deck".
 *
 * This interface defines methods to access the elements at both
 * ends of the deque. Each of these methods exists in two forms:
 * one throw an exception if the operation fails, the other returns
 * special value (either null or false), depending on the operation.
 * The latter form of the insert operation is  designed specifically
 * for use with capacity-restricted {@code Deque} implementations; in most
 * implementations, insert operations cannot fail.
 *
 * Throws exception:
 * {@link Deque#addFirst}
 * {@link Deque#addLast}
 *
 * {@link Deque#removeFirst}
 * {@link Deque#removeLast}
 *
 * {@link Deque#getFirst}
 * {@link Deque#getLast}
 *
 * Special value:
 * {@link Deque#offerFirst}
 * {@link Deque#offerLast}
 *
 * {@link Deque#pollFirst}
 * {@link Deque#pollLast}
 *
 * {@link Deque#peekFirst}
 * {@link Deque#peekLast}
 *
 * This interface extends the {@link Queue} interface. When a deque is
 * used as a queue, FIFO behavior results. Elements are added at the end
 * of the deque and removed from the beginning. The methods
 * inherited from the {@code Queue} interface are precisely equivalent
 * to {@code Deque} as following:
 *
 * {@link Queue#add(Object)}
 * {@link Deque#addLast}
 *
 * {@link Queue#offer(Object)}
 * {@link Deque#offerLast}
 *
 * {@link Queue#remove()}
 * {@link Deque#removeFirst}
 *
 * {@link Queue#poll()}
 * {@link Deque#pollFirst}
 *
 * {@link Queue#element()}
 * {@link Deque#getFirst}
 *
 * {@link Queue#peek()}
 * {@link Deque#peekFirst}
 *
 * Deques can also be used as LIFO stacks.
 * This interface should be used in preference to the legacy
 * {@link java.util.Stack} class.
 * When a deque is used as a stack, elements are pushed and poped
 * from the beginning of the deque. Stack methods are precisely equivalent
 * to {@code Deque} methods as following:
 *
 * {@link #push(Object)}
 * {@link Deque#addFirst(Object)}
 *
 * {@link #pop()}
 * {@link Deque#removeFirst()}
 *
 * {@link #peek()}
 * {@link Deque#peekFirst()}
 *
 * Note that the {@link #peek()} method works equally well when
 * a deque is used as a queue or a stack; in either case, elements
 * are drawn from the beginning of the deque.
 *
 * Unlink the List interface, this interface does not
 * provide support for indexed access to elements.
 *
 * @param <E> the type of elements held in this collection
 */
public interface Deque<E> extends Queue<E> {


    /**
     * Inserts the specified element at the front of this deque if it is
     * possible to do so immediately without violating capacity restrictions,
     * throwing an @{code IllegalStateException} if no space is currently
     * available. When using a capacity-restricted deque, it is generally
     * preferable to use method {@link #offerFirst}.
     *
     * @param e the element to add
     * @throws IllegalStateException if the element cannot be added at this
     *          time due to capacity restrictions
     * @throws ClassCastException if the class of the specified element
     *          prevents it from being added to this deque.
     * @throws NullPointerException if the specified element is null and
     *          this deque does not permit null elements
     * @throws IllegalArgumentException if some property of the specified
     *          element prevents it from being added to this deque
     */
    void addFirst(E e);

    /**
     * @param e the element to add
     * @return {@code true} if the element was added to this deque, else
     *      {@code false}
     */
    boolean offerFirst(E e);

    void addLast(E e);

    boolean offerLast(E e);

    /**
     * Retrieves and removes the first element of this deque. This method
     * differs from {@link #pollFirst} only in that it throws an exception
     * if this deque is empty.
     *
     * @return the head of this deque
     * @throws java.util.NoSuchElementException if this deque is empty
     */
    E removeFirst();

    E pollFirst();

    E removeLast();

    E pollLast();

    /**
     * Retrieves, but does not remove, the first element of this deque.
     *
     * This method differs from {@link #peekFirst} only in that throws
     * an exception if this deque if empty.
     *
     * @return the head of this deque
     * @throws java.util.NoSuchElementException if this deque is empty
     */
    E getFirst();

    E peekFirst();

    E getLast();

    E peekLast();


    // *** Queue methods ***

    /**
     * Equivalent to {@link #addLast(Object)}
     *
     * @param e the element to add
     */
    boolean add(E e);

    /**
     * Equivalent to {@link #offerLast(Object)}
     *
     * @param e the element to add
     */
    boolean offer(E e);

    /**
     * Equivalent to {@link #removeFirst()}
     */
    E remove();

    /**
     *  Equivalent to {@link #pollFirst()}
     */
    E poll();

    /**
     * Equivalent to {@link #getFirst()}
     */
    E element();

    /**
     * Retrieves, but does not remove, the head of the queue represented by
     * this deque(in other words, the first element of this deque), or
     * returns {@code null} if this deque is empty.
     *
     * This method is equivalent to {@link #peekFirst()}
     *
     * @return the head of the deque represented by this deque, or
     *      {@code null} if this deque is empty
     */
    E peek();

    // *** Stack methods ***

    /**
     * Pushes an element onto the stack represented by the deque(in other
     * words, at the head of this deque) if it is possible to do so
     * immediately without violating capacity restrictions, throwing an
     * {@code IllegalStateException} if no space is currently available.
     *
     * This method is equivalent to {@link #addFirst(Object)}
     *
     * @param e the element to push
     * @throws IllegalStateException if the element cannot be added at this
     *      time due to capacity restrictions
     * @throws NullPointerException if the specified element is null and this
     *      deque does not permit null elements
     */
    void push(E e);


    /**
     * Pops an element from the stack represented by this deque. In other
     * words, removes and returns the first element of this deque.
     *
     * This method is equivalent to {@link #removeFirst()}
     *
     * @return the element at the front of this deque (which is the top)
     *          of the stack represented by this deque)
     * @throws java.util.NoSuchElementException if this deque is empty
     */
    E pop();

    // Equivalent to peekFirst()
    // E peek();


}
