package com.github.ryan.personal.data_structure.deque;

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
 */
public interface Deque<E> extends Queue<E> {

}
