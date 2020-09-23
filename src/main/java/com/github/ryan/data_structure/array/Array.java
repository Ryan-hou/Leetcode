package com.github.ryan.data_structure.array;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Array
 * @date August 06,2018
 */
public class Array<E> {

    private E[] data;
    private int size; // 当前存储的元素个数，区分capacity

    private static final int DEFAULT_CAPACITY = 10;

    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public Array() {
        this(DEFAULT_CAPACITY);
    }

    public Array(E[] arr) {
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        size = arr.length;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 均摊时间复杂度(amortized time complexity): O(n),removeLast()同理
    // 均摊的思想在工程上很有借鉴意义
    public void addLast(E e) {

        if (size == data.length) {
            resize(size * 2);
        }

        data[size++] = e;
        // or add(size, e);
    }

    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * Inserts the specified element at the specified position in this
     * array.
     * @param index index at which the specified element is to be inserted
     * @param e element to be inserted
     * @throws IndexOutOfBoundsException
     */
    public void add(int index, E e) {

        if (size == data.length) {
            resize(size * 2);
        }
        // index 可以等于size
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }


        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        // or use: System.arraycopy(data, index, data, index + 1, size - index);(ArrayList)

        data[index] = e;
        size++;
    }

    public E get(int index) {
        rangeCheck(index);
        return data[index];
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size);
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this array, or -1 if this list does not contain the element.
     */
    public int indexOf(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(E e) {
        return indexOf(e) >= 0;
    }

    /**
     * Removes the element at the specified position in this array.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the array
     * @throws IndexOutOfBoundsException
     */
    public E remove(int index) {
        rangeCheck(index);

        E ret = data[index];
        for (int i = index + 1; i < size; i++) {
           data[i - 1] = data[i];
        }
        size--;
        data[size] = null; // loitering objects != memory leak

        // 缩容：ArrayList提供了trimToSize()方法
        // 存在复杂度震荡的问题：原因在于remove时resize过于着急(eager)
//        if (size == data.length / 2) {
//            resize(data.length / 2);
//        }
        // 解决：使用lazy的方式
        // 注意：data.length / 2 != 0
        // int[] a=new int[0];可以正常运行，只是基于此例创建长度为0的数组没意义，size无法指向，
        // 并且在扩容时0乘以任何数等于0，故应加边界控制条件避免此类情况发生
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return ret;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void removeElement(E e) {
        int index = indexOf(e);
        if (index != -1) {
            remove(index);
        }
    }

    /**
     * Replaces the element at the specified position in this array with
     * the specified element.
     * @param index index of the element to replace
     * @param e element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException
     */
    public E set(int index, E e) {
        rangeCheck(index);

        E oldVal = data[index];
        data[index] = e;
        return oldVal;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        // 使用内存拷贝更高效
        // System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return String.format("Index: %d, Size: %d", index, size);
    }

    public void swap(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IllegalArgumentException("Index is illegal");
        }

        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }

    public static void main(String[] args) {

        Array<Integer> arr = new Array<>();
        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }
        System.out.println(arr);
        arr.add(1, 100);
        System.out.println(arr);

        arr.remove(0);
        System.out.println(arr);

        arr.removeElement(100);
        System.out.println(arr);

        arr.removeLast();
        System.out.println(arr);

    }
}
