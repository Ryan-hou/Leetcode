package com.github.ryan.algorithm.personal.sort;

import com.github.ryan.algorithm.personal.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: BubbleSort
 * @date February 07,2018
 */
@Slf4j
public class BubbleSort implements Sort {

    public void bubbleSort(int[] nums) {
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

    public void bubbleSortOptimize(int[] nums) {
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

    @Override
    public void sort(int[] nums, int n) {
        bubbleSortOptimize(nums);
    }
}
