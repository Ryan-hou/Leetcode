package com.github.ryan.leetcode.algorithm.medium.medium694;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    private int[][] grid;
    private boolean[][] seen;
    private List<Integer> path;

    public int numDistinctIslands(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        this.grid = grid;
        seen = new boolean[row][col];
        // Hash by path
        Set<List<Integer>> set = new HashSet<>();
        // DFS
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                path = new ArrayList<>();
                dfs(i, j, 0);
                if (!path.isEmpty()) {
                    // use set to remove duplicate path
                    set.add(path);
                }
            }
        }
        return set.size();
    }

    private void dfs(int row, int col, int dir) {
        if (0 <= row && row < grid.length
                && 0 <= col && col < grid[0].length
                && grid[row][col] == 1
                && !seen[row][col]) {
            seen[row][col] = true;
            path.add(dir);
            dfs(row + 1, col, 1);
            dfs(row - 1, col, 2);
            dfs(row, col + 1, 3);
            dfs(row, col - 1, 4);
            // be careful:
            // have to track the exit state of the path.
            // otherwise, a different shape can have the same path
            path.add(0);
        }
    }

}
