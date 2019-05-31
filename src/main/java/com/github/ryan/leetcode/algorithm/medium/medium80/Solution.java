package com.github.ryan.leetcode.algorithm.medium.medium80;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date May 31,2019
 */
public class Solution {

    // 时间复杂度:O(n) 空间复杂度:O(1)
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int k = 0; // [0,k]:at most twice duplicate array
        int n = nums[0];
        int dup = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != n) {
                n = nums[i];
                dup = 1;
                swap(nums, ++k, i);
            } else {
                // nums[i] == n
                dup++;
                if (dup <= 2) {
                    swap(nums, ++k, i);
                }
            }
        }
        return k + 1;
    }

    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }
    }
}
