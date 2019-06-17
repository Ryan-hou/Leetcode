package com.github.ryan.leetcode.algorithm.medium.medium221;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 17,2019
 */
public class Solution {

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length <= 0) return 0;

        int m = matrix.length;
        int n = matrix[0].length;
        // dp[i][j] represents the side length of the maximum square
        // whose bottom right corner is the cell (i - 1, j - 1) in
        // the original matrix
        int[][] dp = new int[m + 1][n + 1];
        int maxlen = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    maxlen = Math.max(maxlen, dp[i][j]);
                }
            }
        }
        return maxlen * maxlen;
    }

}
