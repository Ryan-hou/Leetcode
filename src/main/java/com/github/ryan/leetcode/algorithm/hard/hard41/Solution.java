package com.github.ryan.leetcode.algorithm.hard.hard41;

import java.util.Random;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 06,2019
 */
public class Solution {

    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length < 1) return 1;
        // 随机化处理, 避免数组有序的情况下算法退化为 O(n^2)
        int r = Math.abs(new Random().nextInt()) % (nums.length);
        swap(nums, 0, r);
        int search = 1;
        int idx = 0;
        while (idx < nums.length) {
            if (search == nums[idx]) {
                search += 1;
                idx = 0;
            } else {
                idx++;
            }
        }
        return search;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
