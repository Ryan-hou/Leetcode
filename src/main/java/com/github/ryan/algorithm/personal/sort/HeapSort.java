package com.github.ryan.algorithm.personal.sort;

import com.github.ryan.data_structure.heap.MaxHeap;
import com.github.ryan.algorithm.personal.util.ArrayUtil;

import java.util.stream.IntStream;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: HeapSort
 * @date February 08,2018
 */
public class HeapSort implements Sort {

    @Override
    public void sort(int[] nums, int n) {
        heapSort2(nums, n);
    }

    private void heapSort(int[] nums, int n) {
//        MaxHeap<Integer> maxHeap = new MaxHeap<>(n);
//        for (int i = 0; i < n; i++) {
//            maxHeap.insert(nums[i]);
//        }

        // 自动装箱拆箱影响性能
        Integer[] integers = IntStream.of(nums).boxed().toArray(Integer[]::new);
        // heapify
        MaxHeap<Integer> maxHeap = new MaxHeap<>(integers, n);

        for (int i = n - 1; i >= 0; i--) {
            nums[i] = maxHeap.extractMax();
        }
    }

    // 原地排序，数组索引0处为堆的根节点
    private void heapSort2(int[] nums, int n) {
        // heapify
        for (int i = (n-1)/2; i >= 0; i--) {
            shiftDown(nums, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            ArrayUtil.swap(nums, 0, i);
            shiftDown(nums, i, 0);
        }
    }

    private void shiftDown(int[] nums, int n, int k) {
        // 子节点存在
        while (2 * k + 1 < n) {
            int j = 2*k + 1; // 在此轮循环中,arr[k]和arr[j]交换位置
            if (j + 1 < n && nums[j + 1] > nums[j]) {
                j += 1;
            }

            if (nums[k] >= nums[j]) {
                break;
            }

            ArrayUtil.swap(nums, k, j);
            k = j;
        }
    }
}
