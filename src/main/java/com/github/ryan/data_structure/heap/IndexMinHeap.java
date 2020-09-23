package com.github.ryan.data_structure.heap;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: IndexMinHeap
 * @date February 13,2018
 */
public class IndexMinHeap<E extends Comparable<E>> {

    private int[] indexes;
    private int[] reverse;
    private Object[] data;

    private int count;
    private int capacity;

    public IndexMinHeap(int capacity) {
        data = new Object[capacity + 1];
        indexes = new int[capacity + 1];
        reverse = new int[capacity + 1];

        count = 0;
        this.capacity = capacity;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void insert(int index, E item) {
        assert  count + 1 <= capacity;
        assert  index + 1 >= 1 && index + 1 <= capacity;

        index += 1;
        data[index] = item;
        indexes[count + 1] = index;
        reverse[index] = count + 1;
        count++;
        shiftUp(count);
    }

    public E extractMin() {
        assert count > 0;
        E ret = (E) data[indexes[1]];
        swap(indexes, 1, count);
        reverse[indexes[count]] = 0;
        reverse[indexes[1]] = 1;
        count--;
        shiftDown(1);
        return ret;
    }

    public int extractMinIndex() {
        assert count > 0;

        int ret = indexes[1] - 1;
        swap(indexes, 1, count);
        reverse[indexes[count]] = 0;
        reverse[indexes[1]] = 1;
        count--;
        shiftDown(1);
        return ret;
    }

    public int getMinIndex() {
        assert count > 0;
        return indexes[1] - 1;
    }

    public E getMin() {
        assert count > 0;
        return (E) data[indexes[1]];
    }

    public boolean contain(int index) {
        return reverse[index + 1] != 0;
    }

    public E getItem(int index) {
        assert (contain(index));
        return (E) data[index + 1];
    }

    public void change(int index, E item) {
        assert (contain(index));

        index += 1;
        data[index] = item;

        shiftUp(reverse[index]);
        shiftDown(reverse[index]);
    }

    private void shiftUp(int k) {
        while (k > 1 && ((E) data[indexes[k / 2]])
                .compareTo((E)data[indexes[k]]) > 0) {
            swap(indexes, k / 2, k);
            reverse[indexes[k/2]] = k/2;
            reverse[indexes[k]] = k;
            k /= 2;
        }
    }

    private void shiftDown(int k) {
        while (2 * k <= count) {
            int j = 2 * k;
            // 有bug，注意data数组与indexes数组的取值关系data[indexes[x]]
//            if (j + 1 <= count &&
//                    ((E) data[indexes[j]]).compareTo((E) data[j + 1]) > 0) {
//                j += 1;
//            }
            if (j + 1 <= count &&
                    ((E) data[indexes[j]]).compareTo((E) data[indexes[j + 1]]) > 0) {
                j += 1;
            }

            if (((E) data[indexes[j]]).compareTo((E) data[indexes[k]]) >= 0) {
                break;
            }

            swap(indexes, k, j);
            reverse[indexes[k]] = k;
            reverse[indexes[j]] = j;
            k = j;
        }
    }

    private void swap(int[] data, int i, int j) {
        int temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

}
