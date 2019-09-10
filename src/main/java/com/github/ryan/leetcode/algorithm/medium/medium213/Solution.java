package com.github.ryan.leetcode.algorithm.medium.medium213;

public class Solution {

    public int rob(int[] nums) {
        if (nums == null || nums.length < 1) return 0;
        // dp1[i] -> rob first i houses's max value with first house
        int[] dp1 = new int[nums.length + 1];
        dp1[0] = 0;
        dp1[1] = nums[0];
        // dp2[i] -> rob first i house's max value without first house
        int[] dp2 = new int[nums.length + 1];
        dp2[0] = 0;
        dp2[1] = 0;

        for (int i = 1; i < nums.length; i++) {
            if (i == nums.length - 1) {
                dp1[i + 1] = dp1[i];
            } else {
                dp1[i + 1] = Math.max(dp1[i], dp1[i - 1] + nums[i]);
            }
            dp2[i + 1] = Math.max(dp2[i], dp2[i - 1] + nums[i]);
        }
        return Math.max(dp1[nums.length], dp2[nums.length]);
    }

}
