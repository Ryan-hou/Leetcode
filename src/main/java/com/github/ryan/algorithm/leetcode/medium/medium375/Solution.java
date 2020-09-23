package com.github.ryan.algorithm.leetcode.medium.medium375;

public class Solution {

    // top down dp
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        return get(dp, 1, n);
    }

    private int get(int[][] dp, int start, int end) {
        if (start >= end) return 0;
        if (dp[start][end] != 0) return dp[start][end];

        dp[start][end] = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            int cost = i + Math.max(get(dp, start, i - 1), get(dp, i + 1, end));
            dp[start][end] = Math.min(dp[start][end], cost);
        }
        return dp[start][end];
    }
}
