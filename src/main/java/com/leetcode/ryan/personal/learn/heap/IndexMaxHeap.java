package com.leetcode.ryan.personal.learn.heap;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: IndexMaxHeap
 * @date February 09,2018
 */

import com.leetcode.ryan.personal.util.ArrayUtil;

import java.util.Random;

/**
 * 在索引堆的基础上，维护原数组数据和索引的关系
 * 实现思路：
 * 通过引入中间层－－indexes数组，来实现原数组索引与数据的分离
 * indexes数组来构建堆，原数组索引及数据保持不变；
 * indexes数组的值对应根据原数组的数据构建堆后，原数组数据的索引下标
 *
 * 数组下标从1开始使用
 */
public class IndexMaxHeap {

    // 索引堆的数据
    private int[] data;
    // 根据索引堆数据构建的真正的堆
    private int[] indexes;
    // 反向查找表
    // reverse[i]表示索引i在indexes(堆)中的位置
    // indexes[i] = j, reverse[j] = i;
    // indexes[reverse[i]] = i, reverse[indexes[i]] = i
    private int[] reverse;

    // 索引堆存储的数据量
    private int count;
    // 索引堆的容量
    private int capacity;

    public IndexMaxHeap(int capacity) {

        data = new int[capacity + 1];
        indexes = new int[capacity + 1];
        reverse = new int[capacity + 1];
        for (int i = 0; i <= capacity; i++) {
            reverse[i] = 0;
        }

        count = 0;
        this.capacity = capacity;
    }


    // 传入的i对于用户而言，是从0索引的
    public void insert(int i, int item) {
        assert (count + 1 <= capacity);
        assert (i + 1 >= 1 && i + 1 <= capacity);

        data[++i] = item;
        indexes[count + 1] = i;
        reverse[i] = count + 1;
        count++;

        shiftUp(count);
    }

    private void shiftUp(int k) {
        while (k > 1 && data[indexes[k / 2]] < data[indexes[k]]) {
            ArrayUtil.swap(indexes, k / 2, k);
            reverse[indexes[k/2]] = k/2;
            reverse[indexes[k]] = k;
            k /= 2;
        }
    }

    public int extractMax() {
        assert count > 0;

        int ret = data[indexes[1]];
        ArrayUtil.swap(indexes, 1, count);
        reverse[indexes[count]] = 0;
        reverse[indexes[1]] = 1;
        count--;
        shiftDown(1);
        return ret;
    }

    private void shiftDown(int k) {
        // 存在孩子节点
        while (2 * k <= count) {
            int j = 2 * k;
            if (j + 1 <= count && data[indexes[j + 1]] > data[indexes[j]]) {
                j += 1;
            }

            if (data[indexes[k]] >= data[indexes[j]]) {
                break;
            }

            ArrayUtil.swap(indexes, k, j);
            reverse[indexes[k]] = k;
            reverse[indexes[j]] = j;
            k = j;
        }
    }

    public void change(int i, int newItem) {
        assert contain(i);

        i += 1;
        data[i] = newItem;

        // 找到indexes[j]=i,j表示data[i]在堆中的位置
        // 之后shiftUp(j),再shiftDown(j)
        // 时间复杂度为 O(nlogn)，后续会通过反向查找表优化为 O(logn)
//        for (int j = 1; j <= count; j++) {
//            if (indexes[j] == i) {
//                shiftUp(j);
//                shiftDown(j);
//                return;
//            }
//        }

        int j = reverse[i];
        shiftUp(j);
        shiftDown(j);
    }

    private boolean contain(int i) {
        assert i + 1 >= 1&& i + 1 <= capacity;
        return reverse[i + 1] != 0;
    }


    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int getMax() {
        assert count > 0;
        return data[indexes[1]];
    }

    public int getMaxIndex() {
       assert count > 0;
       return indexes[1] - 1;
    }

    public int getItem(int i) {
        assert contain(i);
        return data[i + 1];
    }

    public static void main(String[] args) {

        Random random = new Random();
        IndexMaxHeap indexMaxHeap = new IndexMaxHeap(100);
        for (int i = 0; i < 15; i++) {
            indexMaxHeap.insert(i, Math.abs(random.nextInt()) % 100);
        }
        indexMaxHeap.change(3, 1000);
        indexMaxHeap.insert(20, 800);

        while (!indexMaxHeap.isEmpty()) {
            System.out.print(indexMaxHeap.extractMax() + "  ");
        }
    }

}
