package com.github.ryan.algorithm.leetcode.medium.medium75;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date January 11,2018
 */
@Slf4j
public class Solution {

    /**
     * 计数排序的思想
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     * @param nums
     */
    public static void sortColorsOne(int[] nums) {
        int[] count = new int[3]; // 存放0，1，2三个元素的频率
        for (int i = 0; i < nums.length; i++) {
            assert (0 <= nums[i] && nums[i] <= 2);
            count[nums[i]]++;
        }

        int index = 0;

        for (int i = 0; i < count.length; i++) {
           for (int j = 0; j < count[i]; j++) {
               nums[index++] = i;
           }
        }

//        for (int i = 0; i < count[0]; i++) {
//            nums[index++] = 0;
//        }
//        for (int i = 0; i < count[1]; i++) {
//            nums[index++] = 1;
//        }
//        for (int i = 0; i < count[2]; i++) {
//            nums[index++] = 2;
//        }
    }

    /**
     * 使用三路快排的思路
     * 时间复杂度：O(n) 且只用一次循环
     * 空间复杂度：O(1)
     * @param nums
     */
    public static void sortColors(int[] nums) {
        int zero = -1; // nums[0,zero] == 0
        int two = nums.length; // nums[two,n-1] == 2
        for (int i = 0; i < two; ) {
            if (nums[i] == 1) {
                i++;
            } else if (nums[i] == 2) {
                swap(nums, i, --two);
            } else {
                assert (nums[i] == 0);
//                zero++;
//                swap(nums, zero, i);
//                i++;
                swap(nums, ++zero, i++);
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 2, 1, 1, 0, 0};
        sortColors(nums);
        log.info("nums = {}", Arrays.toString(nums));
    }
}
