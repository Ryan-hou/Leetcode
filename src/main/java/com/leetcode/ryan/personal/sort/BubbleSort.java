package com.leetcode.ryan.personal.sort;

import com.leetcode.ryan.personal.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: BubbleSort
 * @date February 07,2018
 */
@Slf4j
public class BubbleSort {

    public static void bubbleSort(int[] nums) {
        Objects.requireNonNull(nums);

        for (int i = nums.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                int k = j + 1;
                if (nums[j] > nums[k]) {
                    ArrayUtil.swap(nums, j, k);
                }
            }
        }
    }

    public static void bubbleSortOptimize(int[] nums) {
        int n = nums.length;
        boolean swapped;

        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (nums[i - 1] > nums[i]) {
                    ArrayUtil.swap(nums, i - 1, i);
                    swapped = true;
                }
            }
            n--;
        } while (swapped);
    }

    public static void main(String[] args) {

        int n = 1000000;
        int[] array = ArrayUtil.generateOrderedArray(n);
        long startTime = System.currentTimeMillis();
        bubbleSortOptimize(array);
        long endTime = System.currentTimeMillis();
        assert ArrayUtil.isSorted(array, n);
        System.out.println(Arrays.toString(array));
        System.out.println("Bubble sort consume " + (endTime - startTime) + " ms");
    }
}
