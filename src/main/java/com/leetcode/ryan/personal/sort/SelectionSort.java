package com.leetcode.ryan.personal.sort;

import com.leetcode.ryan.personal.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: SelectionSort
 * @date January 31,2018
 */
@Slf4j
public class SelectionSort {

    /**
     * 选择排序思路：
     * 循环一遍数组，每次把剩余数组中最小的元素交换到数组该次循环的位置
     * 时间复杂度 O(n^2)
     * @param arr
     */
    public static void selectSort(int[] arr) {
        Objects.requireNonNull(arr);

        int size = arr.length;
        for (int i  = 0; i < size; i++) {
            // 寻找[i,size)区间的最小值
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            ArrayUtil.swap(arr, i, minIndex);
        }

    }

    public static void main(String[] args) {
        int n = 10000;
        int[] arr = ArrayUtil.generateRandomArray(n, 0, n);
        selectSort(arr);
        assert ArrayUtil.isSorted(arr, n);
    }
}
