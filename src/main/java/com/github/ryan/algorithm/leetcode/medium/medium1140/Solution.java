package com.github.ryan.algorithm.leetcode.medium.medium1140;

public class Solution {

    // The introduction of M (as a middle layer) make the problem complex.
    // Actually, just loop M from 1 to n/2 + 1, it didn't make the problem too complicated.
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        //sum[i] is the total number of stones from index i to the end
        int[] sum = new int[n];
        sum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            sum[i] = sum[i + 1] + piles[i];
        }

        // dp[i][j] -> max number of stones when start at index i with j as M
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int m = 1; m <= n / 2 + 1; m++) {
                for (int x = 1; x <= 2 * m && i + x <= n; x++) {
                    if (i + x == n) {
                        dp[i][m] = Math.max(dp[i][m], sum[i]);
                    } else {
                        // i + x < n
                        dp[i][m] = Math.max(dp[i][m], sum[i] - dp[i + x][Math.max(m, x)]);
                    }
                }
            }
        }
        return dp[0][1];
    }

}
