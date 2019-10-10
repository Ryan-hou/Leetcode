package com.github.ryan.leetcode.algorithm.medium.medium695;

public class Solution {

    // Use DFS
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1) return 0;
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int cur = dfs(grid, i, j);
                    res = Math.max(res, cur);
                }
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length
                || j < 0 || j >= grid[0].length
                || grid[i][j] == 0) {
            return 0;
        }

        grid[i][j] = 0;
        int res = 1;
        res += dfs(grid, i + 1, j);
        res += dfs(grid, i - 1, j);
        res += dfs(grid, i, j + 1);
        res += dfs(grid, i, j - 1);
        return res;
    }

}
