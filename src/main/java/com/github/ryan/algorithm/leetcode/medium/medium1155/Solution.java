package com.github.ryan.algorithm.leetcode.medium.medium1155;

public class Solution {

    public int numRollsToTarget(int d, int f, int target) {
        if (d * f < target || target < d) return 0;
        if (d * f == target) return 1;
        if (d == 1) return 1;

        long mod = 1000000007;
        // dp[i][j] -> num rolls to j with i dice
        long[][] dp = new long[d + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= d; i++) {
            for (int j = 1; j <= f; j++) {
                for (int k = j; k <= target; k++) {
                    dp[i][k] = (dp[i][k] + dp[i - 1][k - j]) % mod;
                }
            }
        }
        return (int) dp[d][target];
    }

}
