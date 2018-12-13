package com.github.ryan.personal.data_structure.concurrent.list;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A thread-safe variant of java.util.ArrayList in which all mutative
 * operations add, set, and so on are implemented by making a fresh
 * copy of the underlying array.
 *
 * This is ordinarily too costly, but may be more efficient
 * than alternatives when traversal operations vastly outnumber
 * mutations, and is useful when you cannot or don't want to
 * synchronize traversals, yet need to preclude interference among
 * concurrent threads. The "snapshot" style iterator method uses
 * a reference to the state of the array at the point that the iterator
 * was created. This array never changes during the lifetime of the
 * iterator, so interference is impossible and the iterator is
 * guaranteed not to throw ConcurrentModificationException.
 * The iterator will not reflect additions, removals, or changes to
 * the list since the iterator was created. Element-changing
 * operations on iterators themselves remove, set, and add are not
 * supported. These methods throw UnsupportedOperationException.
 *
 * All elements are permitted, including null.
 *
 * Memory consistency effects:
 * As with other concurrent collections, actions in a thread prior to
 * placing an object into a CopyOnWriteArrayList happen-before
 * actions subsequent to the access or removal of that element from
 * the CopyOnWriteArrayList in another thread.
 *
 * @param <E> the type of elements held in this collection
 */
public class CopyOnWriteArrayList<E> implements List<E> {

    // The lock protecting all mutators
    final transient ReentrantLock lock = new ReentrantLock();

    // The array, accessed only via getArray/setArray.
    private transient volatile Object[] array;

    // Gets the array. Non-private so as to also be accessible
    // from CopyOnWriteArraySet class.
    final Object[] getArray() {
        return array;
    }

    final void setArray(Object[] a) {
        array = a;
    }

    // Creates an empty list.
    public CopyOnWriteArrayList() {
        setArray(new Object[0]);
    }

    /**
     * Creates a list holding a copy of the given array.
     *
     * @param toCopyIn the array (a copy of this array is used as the internal array)
     * @throws NullPointerException if the specified array is null
     */
    public CopyOnWriteArrayList(E[] toCopyIn) {
        setArray(Arrays.copyOf(toCopyIn, toCopyIn.length, Object[].class));
    }

    public int size() {
        return getArray().length;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    //    public boolean contains(Object o) {
//        Object[] elements = getArray();
//        return indexOf(o, elements, 0, elements.length) >= 0;
//    }
    public <T> T[] toArray(T[] a) {
        Object[] elements = getArray();
        int len = elements.length;
        if (a.length < len) {
            return (T[]) Arrays.copyOf(elements, len, a.getClass());
        } else {
            System.arraycopy(elements, 0, a, 0, len);
            if (a.length > len) {
                a[len] = null;
            }
            return a;
        }
    }

    private E get(Object[] a, int index) {
        return (E) a[index];
    }

    // throws IndexOutOfBoundsException
    // lock-free
    public E get(int index) {
        return get(getArray(), index);
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param e element to be appended to this list
     * @return
     */
    public boolean add(E e) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            Object[] newElements = Arrays.copyOf(elements, len + 1);
            newElements[len] = e;
            setArray(newElements);
            return true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices). Returns the element that was removed from the list.
     *
     * @throws IndexOutOfBoundsException
     * @param index
     * @return
     */
    public E remove(int index) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            E oldValue = get(elements, index);
            int numMoved = len - index - 1;
            if (numMoved == 0) {
                setArray(Arrays.copyOf(elements, len - 1));
            } else {
                Object[] newElements = new Object[len - 1];
                System.arraycopy(elements, 0, newElements, 0, index);
                System.arraycopy(elements, index + 1, newElements, index, numMoved);

                setArray(newElements);
            }
            return oldValue;
        } finally {
            lock.unlock();
        }
    }

}
