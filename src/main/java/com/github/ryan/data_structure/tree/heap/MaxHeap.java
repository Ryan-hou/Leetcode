package com.github.ryan.data_structure.tree.heap;

import com.github.ryan.data_structure.array.Array;

import java.util.NoSuchElementException;
import java.util.Random;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className MaxHeap
 * @date August 14,2018
 */
public class MaxHeap<E extends Comparable<E>> {

    // 使用动态数组存储完全二叉树，从数组下标0开始
    private Array<E> data;

    public MaxHeap() {
        data = new Array<>();
    }

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    // heapify: 将任意数组整理成堆的形状
    // 直观的方式：扫描一遍数组，然后把数组元素加入到堆中：O(nlogn)
    // heapify: 先找到第一非叶子节点(过滤掉近一半元素)，然后从该节点开始到根节点，进行shiftDown操作
    // heapify: 时间复杂度O(n)
    public MaxHeap(E[] arr) {
        data = new Array<>(arr);

        // heapify
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            shiftDown(i);
        }
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    // 向堆中添加元素
    public void add(E e) {
        data.addLast(e);
        shiftUp(data.size() - 1);
    }


    private void shiftUp(int index) {

        while (index > 0 && data.get(parent(index)).compareTo(data.get(index)) < 0) {
            data.swap(index, parent(index));
            index = parent(index);
        }
    }

    public E peekMax() {
        if (data.size() == 0) {
            throw new NoSuchElementException("Can't peekMax when heap is empty");
        }

        return data.get(0);
    }

    // 取出堆中最大元素
    public E extractMax() {

        E ret = peekMax();

        // 把最大元素与最后一个元素交换，然后再shiftDown
        data.swap(0, data.size() - 1);
        data.removeLast();
        shiftDown(0);

        return ret;
    }

    private void shiftDown(int index) {

        while (leftChild(index) < data.size()) {
            int j = leftChild(index);
            if (j + 1 < data.size()
                    && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j++;
            }
            // data[j] 是 leftChild 和 rightChild 中的最大值

            if (data.get(index).compareTo(data.get(j)) >= 0) {
                break;
            }

            data.swap(index, j);
            index = j;
        }
    }

    // replace:取出最大元素后，放入一个新元素
    // 实现：可以先extractMax再add，两次O(logn)操作
    // 或者可以直接将堆顶元素替换，然后shiftDown，只需要一次O(logn)操作
    public E replace(E e) {

        E ret = peekMax();
        data.set(0, e);
        shiftDown(0);

        return ret;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return (index - 1) / 2;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChilde(int index) {
        return index * 2 + 2;
    }

    public static void main(String[] args) {

        int n = 1000000;

        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
        }

        for (int i = 1; i < n; i++) {
            if (arr[i - 1] < arr[i]) {
                throw new IllegalStateException("Error!");
            }
        }

        System.out.println("Test MaxHeap completed.");
    }
}
