package com.github.ryan.algorithm.leetcode.medium.medium650;

public class Solution {

    public int minSteps(int n) {
        if (n <= 1) return 0;
        // dp[i] -> minimum number of steps to get i 'A'
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = i; // maximum number of steps to get i 'A'
            for (int j = 1; j <= i / 2; j++) {
                if (i % j == 0) {
                    dp[i] = Math.min(dp[i], dp[j] + i / j);
                }
            }
        }
        return dp[n];
    }

}
