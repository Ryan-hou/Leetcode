package com.github.ryan.personal.algorithm.learn.heap;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * 采用数组索引1处的元素作为堆的根节点，也可以使用索引0，父子节点间的规律需要相应的变化
 *
 * @className: MaxHeap
 * @date February 08,2018
 */
@Slf4j
public class MaxHeap<E> {

    private Object[] data;
    // 堆存储的数据量
    private int count;
    // 堆的容量
    private int capacity;

    public MaxHeap(int capacity) {
        // 数组下标从1开始
        data = new Object[capacity + 1];
        count = 0;
        this.capacity = capacity;
    }

    public MaxHeap(E[] arr, int n) {
        data = new Object[n + 1];
        capacity = n;
        for (int i = 0; i < n; i++) {
            data[i + 1] = arr[i];
        }
        count = n;

        // heapify 时间复杂度O(n)(需要数学证明)
        // count/2为第一个完全二叉树的第一个非叶节点
        for (int i = count/2; i >= 1; i--) {
            shiftDown(i);
        }
    }

    public void insert(E e) {
        assert count + 1 <= capacity;
        data[++count] = e;
        shiftUp(count);
    }

    public E extractMax() {
        assert count > 0;
        E ret = (E) data[1];

        swap(data, 1, count--);
        shiftDown(1);

        return ret;
    }

    private void shiftUp(int k) {
        while (k > 1 && ((Comparable<E>) data[k / 2]).compareTo((E) data[k]) < 0) {
            swap(data, k / 2, k);
            k /= 2;
        }
    }

    private void shiftDown(int k) {
        // 存在孩子节点
        while (2 * k <= count) {
            int j = 2 * k; // 在此轮循环中，data[k]和data[j]交换位置
            if (j + 1 <= count && ((Comparable<E>)data[j + 1]).compareTo((E) data[j]) > 0) {
                j++;
            }
            // data[j]是data[2k]和data[2k+1]中的最大值

            if (((Comparable<E>)data[k]).compareTo((E) data[j]) >= 0) break;
            // swap操作可以通过移动覆盖优化
            swap(data, k, j);
            k = j;
        }
    }

    private void swap(Object[] data, int i, int j) {
        Object temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public static void main(String[] args) {
        Random r = new Random();
        MaxHeap<Integer> heap = new MaxHeap<>(100);
        for (int i = 0; i < 20; i++) {
            heap.insert(Math.abs(r.nextInt()) % 100);
        }

        while (!heap.isEmpty()) {
            System.out.print(heap.extractMax() + "  ");
        }

        //log.info("heap's size = {}", heap.size());
        //log.info("heap = {}", Arrays.toString(heap.data));
    }
}
