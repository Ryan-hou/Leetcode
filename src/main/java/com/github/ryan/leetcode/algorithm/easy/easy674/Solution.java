package com.github.ryan.leetcode.algorithm.easy.easy674;

public class Solution {

    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length < 1) return 0;
        int start = nums[0];
        int res = 1;
        int cur = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > start) {
                cur++;
                start = nums[i];
            } else {
                res = Math.max(res, cur);
                start = nums[i];
                cur = 1;
            }
        }
        return Math.max(res, cur);
    }

}
