package com.github.ryan.leetcode.algorithm.easy.easy463;

public class Solution {

    public int islandPerimeter(int[][] grid) {
        int res = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) continue;
                int up = i - 1 < 0 ? 0 : grid[i - 1][j];
                int down = i + 1 >= rows ? 0 : grid[i + 1][j];
                int left = j - 1 < 0 ? 0 : grid[i][j - 1];
                int right = j + 1 >= cols ? 0 : grid[i][j + 1];
                res += (4 - up - down - left - right);
            }
        }
        return res;
    }

}
