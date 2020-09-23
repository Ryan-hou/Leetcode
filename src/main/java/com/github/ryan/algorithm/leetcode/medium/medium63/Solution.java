package com.github.ryan.algorithm.leetcode.medium.medium63;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 21,2019
 */
public class Solution {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] g= obstacleGrid;
        int m = g.length;
        int n = g[0].length;
        if (g[m - 1][n - 1] == 1) return 0; // core case

        // dp[i][j]: from [0,0] to [i,j]'s unique paths
        int[][] dp = new int[m][n];
        for (int i = 0; i < m && g[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n && g[0][i] == 0; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (g[i][j - 1] == 0) {
                    dp[i][j] += dp[i][j - 1];
                }
                if (g[i - 1][j] == 0) {
                    dp[i][j] += dp[i - 1][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
