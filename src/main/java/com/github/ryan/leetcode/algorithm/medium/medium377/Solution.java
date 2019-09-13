package com.github.ryan.leetcode.algorithm.medium.medium377;

public class Solution {

    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length < 1 || target <= 0) return 0;

        Arrays.sort(nums);
        if (nums[0] > target) return 0;
        // dp[i] -> combination sum of i
        int[] dp = new int[target + 1];
        dp[nums[0]] = 1;
        for (int i = nums[0] + 1; i <= target; i++) {
            int tmp = 0;
            for (int n : nums) {
                if (n > i) break;
                if (dp[i - n] != 0) tmp += dp[i - n];
                if (i == n) tmp += 1;
            }
            dp[i] = tmp;
        }
        return dp[target];
    }

}
