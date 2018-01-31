package com.leetcode.ryan.personal.sort;

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
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 交换 a[i] 与 a[minIndex] 元素
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;

        }

    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 3, 4, 8, 7};
        selectSort(arr);
        log.info("Sorted array = {}", Arrays.toString(arr));
    }
}
