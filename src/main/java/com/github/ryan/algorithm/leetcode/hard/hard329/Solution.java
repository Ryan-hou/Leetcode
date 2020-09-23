package com.github.ryan.algorithm.leetcode.hard.hard329;

public class Solution {

    // memorize + dfs
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int max = 1;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] memo = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                max = Math.max(max, dfs(matrix, i, j, Integer.MIN_VALUE, memo));
            }
        }
        return max;
    }

    private int dfs(int[][] mt, int i, int j, int prev, int[][] memo) {
        if (i < 0 || i >= mt.length
                || j < 0 || j >= mt[0].length
                || prev >= mt[i][j]) {
            return 0;
        }
        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        int count = 0;
        count = Math.max(count, dfs(mt, i + 1, j, mt[i][j], memo));
        count = Math.max(count, dfs(mt, i, j + 1, mt[i][j], memo));
        count = Math.max(count, dfs(mt, i - 1, j, mt[i][j], memo));
        count = Math.max(count, dfs(mt, i, j - 1, mt[i][j], memo));
        memo[i][j] = count + 1;
        return memo[i][j];
    }
}
