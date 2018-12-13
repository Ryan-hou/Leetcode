package com.github.ryan.personal.data_structure.concurrent.list;

/**
 * An ordered collection (also known as a sequence). The user of this interface
 * has precise control over where in the list each element is inserted.
 * The user can access elements by their integer index (position in the list),
 * and search for elements in the list.
 */
public interface List<E> {

    // Query Operations

    // Returns the number of elements in this list.
    int size();

    boolean isEmpty();

    /**
     * Returns true if this list contains the specified element.
     * More formally, returns true if and only if this list contains
     * at least one element e such that
     * (o == null ? e == null : o.equals(e))
     *
     * @param o element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     * @throws ClassCastException if the type of the specified element
     *      is incompatible with this list (optional)
     * @throws NullPointerException if the specified element is null and this
     *      list does not permit null elements (optional)
     */
    //boolean contains(Object o);

    // Returns an iterator over the elements in this list in proper sequence.
    //Iterator<E> iterator();

    //Object[] toArray();

    /**
     * Returns an array containing all of the elements in this list in
     * proper sequence (from first to last element); the runtime type of
     * the returned array is that of the specified array. If the list fits
     * in the specified array, it is returned therein. Otherwise, a new
     * array is allocated with the runtime type of the specified array and
     * the size of this list.
     *
     * If the list fits in the specified array with room to spare (i.e., the
     * array has more elements than the list), the element in the array
     * immediately following the end of the list is set to null.
     *
     * List the {@link #toArray()} methods, this method acts as a bridge between
     * array-based and collection-based APIs. Further, this method allows precise
     * control over the runtime type of the output array, and may, under certain
     * circumstances, be used to save allocation costs.
     *
     * Suppose x is a list known to certain only strings.
     * The following code can be used to dump the list into a newly
     * allocated array of String:
     * String[] y = x.toArray(new String[0]);
     *
     * @param a the array into which the elements of this list are to
     *          be stored, if it is big enough; otherwise, a new array of the
     *          same runtime type is allocated for this purpose.
     * @param <T>
     * @return an array containing the elements of this list
     * @throws NullPointerException if the specified array is null
     * @throws ArrayStoreException if the runtime type of the specified array
     *          is not a supertype of the runtime type of every element
     *          in this list
     */
    <T> T[] toArray(T[] a);

    // Modification Operations
    boolean add(E e);

    //boolean remove(Object o);

    // Positional Access Operations
    E get(int index);

    // E set(int index, E element);
    // void add(int index, E element);
    // E remove(int index);
}
