package com.github.ryan.leetcode.algorithm.easy.easy746;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date December 04,2018
 */
public class Solution {

    // 使用动态规划
    public int minCostClimbingStairs(int[] cost) {

        assert cost != null && cost.length >= 2;
        int n = cost.length;
        // dp[i]为经过cost[i]的最小花费
        int[] dp = new int[n];
        dp[0] = cost[0]; // 跳一步，经过cost[0]
        dp[1] = cost[1]; // 跳两步，经过cost[1]
        for (int i = 2; i < n; i++) {
            dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        }
        return Math.min(dp[n - 2], dp[n - 1]); // 注意dp[i]的语义为经过i的花费
    }
}
