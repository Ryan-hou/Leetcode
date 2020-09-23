package com.github.ryan.algorithm.leetcode.easy.easy724;

public class Solution {

    public int pivotIndex(int[] nums) {
        if (nums == null || nums.length < 1) return -1;
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }

        int left = 0;
        int right = sum;
        for (int i = 0; i < nums.length; i++) {
            if (left == right - nums[i]) {
                return i;
            } else {
                left += nums[i];
                right -= nums[i];
            }
        }
        return -1;
    }

}
