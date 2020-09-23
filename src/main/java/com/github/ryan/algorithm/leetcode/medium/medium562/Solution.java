package com.github.ryan.algorithm.leetcode.medium.medium562;

public class Solution {

    public int longestLine(int[][] M) {
        if (M == null || M.length < 1 || M[0].length < 1) return 0;
        int row = M.length;
        int col = M[0].length;
        int res = 0;
        // dp[i][j][0~4]: longest line to M[i][j] in horizontal, vertical, diagonal, anti-diagonal
        int[][][] dp = new int[row][col][4];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (M[i][j] == 1) {
                    dp[i][j][0] = j > 0 ? dp[i][j - 1][0] + 1 : 1;
                    dp[i][j][1] = i > 0 ? dp[i - 1][j][1] + 1 : 1;
                    dp[i][j][2] = (i > 0 && j > 0) ? dp[i - 1][j - 1][2] + 1 : 1;
                    dp[i][j][3] = (i > 0 && j < col - 1) ? dp[i - 1][j + 1][3] + 1 : 1;
                    res = Math.max(res,
                            Math.max(Math.max(dp[i][j][0], dp[i][j][1]), Math.max(dp[i][j][2], dp[i][j][3])));
                }
            }
        }
        return res;
    }

}
