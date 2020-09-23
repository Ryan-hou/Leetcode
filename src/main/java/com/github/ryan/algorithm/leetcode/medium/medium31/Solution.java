package com.github.ryan.algorithm.leetcode.medium.medium31;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date May 27,2019
 */
public class Solution {

    // space complexity: O(1)
    // time complexity: O(n)
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) return;

        int i = nums.length - 2;
        // find the first reverse pair
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // num[i] < nums[i + 1] or i < 0
        if (i >= 0) {
            // nums[i] < nums[i + 1]
            // find first nums[j] > nums[i] and (nums[j + 1] <= nums[i] or  j + 1 >= len)
            int j = i;
            while (j + 1 < nums.length && nums[j + 1] > nums[i]) {
                j++;
            }
            swap(nums, i, j);
        }
        // reverse descending nums from i + 1
        reverse(nums, i + 1);
    }

    private void swap(int[] nums, int i, int j) {
        if (nums[i] != nums[j]) {
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }
    }

    private void reverse(int[] nums, int n) {
        int s = n, e = nums.length - 1;
        while (s < e) {
            swap(nums, s++, e--);
        }
    }
}
