package com.github.ryan.leetcode.algorithm.medium.medium1277;

public class Solution {

    public int countSquares(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        // dp[i][j] -> count of squares from [0, 0] to [i - 1, j - 1]
        int[][] dp = new int[row + 1][col + 1];
        int res = 0;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (matrix[i - 1][j - 1] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    res += dp[i][j];
                }
            }
        }
        return res;
    }

}
