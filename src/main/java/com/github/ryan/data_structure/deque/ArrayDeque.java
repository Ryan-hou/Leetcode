package com.github.ryan.data_structure.deque;

import java.util.NoSuchElementException;

/**
 * Resizable-array implementation of the {@link Deque} interface. Array
 * deques have no capacity restrictions; they grow as necessary to support
 * usage. They are not thread-safe; in the absence of external
 * synchronization, they do not support concurrent access by multiple threads.
 * Null elements are prohibited(Not like {@link LinkedList}).
 * This class is likely to be faster than {@link java.util.Stack}
 * (ArrayDeque be used in preference to Stack) when used as
 * a stack, and faster than {@link LinkedList} when used as a queue.
 *
 * Most ArrayDeque operations run in amortized constant time.
 * Exceptions include remove(Object), contains etc and bulk operations,
 * all of which run in liner time.
 */
public class ArrayDeque<E> implements Deque<E> {

    /**
     * The array in which the elements of deque are stored.
     * The capacity of the deque is the length of this array,
     * which is always a power of two. The array is never allowed
     * to become full, except transiently within an addX method where
     * it is resized (see doubleCapacity) immediately upon becoming full,
     * thus avoiding head and tail wrapping around to equal each
     * other. We also guarantee that all array cells not holding
     * deque elements are always null.
     */
    private Object[] elements;

    private int head; // 指向队首(下一个待出队元素的下标位置)
    private int tail; // 指向队尾元素的下一个位置，即下一次入队操作放置元素的位置

    private static final int MIN_INITIAL_CAPACIY = 8;

    public boolean isEmpty() {
        return head == tail;
    }

    public int size() {
        return (tail - head) & (elements.length - 1);
    }

    // ---------- Deque methods ----------


    @Override
    public void addFirst(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        elements[head = ((head - 1) & (elements.length - 1))] = e;
        if (head == tail) {
            doubleCapacity();
        }
    }

    @Override
    public void addLast(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        elements[tail] = e;
        if ((tail = (tail + 1) & (elements.length - 1)) == head) {
            doubleCapacity();
        }
    }

    @Override
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    @Override
    public E getFirst() {
        E result = (E) elements[head];
        if (result == null) {
            throw new NoSuchElementException();
        }
        return result;
    }

    @Override
    public E getLast() {
        E result = (E) elements[(tail - 1) & (elements.length - 1)];
        if (result == null) {
            throw new NoSuchElementException();
        }
        return result;
    }

    @Override
    public E peekFirst() {
        // elements[head] is null if deque empty
        return (E) elements[head];
    }

    @Override
    public E peekLast() {
        return (E) elements[(tail - 1) & (elements.length - 1)];
    }

    @Override
    public E pollFirst() {
        int h = head;
        E result = (E) elements[h];
        // Element is null if deque empty
        if (result == null) {
            return null;
        }

        elements[h] = null; // Must null out slot
        head = (h + 1) & (elements.length - 1);
        return result;
    }

    @Override
    public E pollLast() {
        int t = (tail - 1) & (elements.length - 1);

        E result = (E) elements[t];
        if (result == null) {
            return null;
        }

        elements[t] = null;
        tail = t;
        return result;
    }

    @Override
    public E removeFirst() {
        E x = peekFirst();
        if (x == null) {
            throw new NoSuchElementException();
        }
        return x;
    }

    @Override
    public E removeLast() {
        E x = pollLast();
        if (x == null) {
            throw new NoSuchElementException();
        }
        return x;
    }

    // ------------- Queue methods ---------------

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public boolean offer(E e) {
        addLast(e);
        return true;
    }

    @Override
    public E remove() {
        return removeFirst();
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E element() {
        return getFirst();
    }

    @Override
    public E peek() {
        return peekFirst();
    }

    // ------------- Stack methods ---------------

    @Override
    public void push(E e) {
        addFirst(e);
    }

    @Override
    public E pop() {
        return removeFirst();
    }

    // ***** Array allocation and resizing utilities *****
    private void allocateElements(int numElements) {
        // check ArrayDeque implementation in java.util.ArrayDeque
    }

    private void doubleCapacity() {
        assert head == tail;
        int p = head;
        int n = elements.length;
        int r = n - p; // number of elements to the right of p
        int newCapacity = n << 1;
        if (newCapacity < 0) {
            throw new IllegalStateException("Sorry, deque too big");
        }
        Object[] a = new Object[newCapacity];
        System.arraycopy(elements, p, a, 0, r);
        System.arraycopy(elements, 0, a, r, p);
        elements = a;
        head = 0;
        tail = n;
    }
}
