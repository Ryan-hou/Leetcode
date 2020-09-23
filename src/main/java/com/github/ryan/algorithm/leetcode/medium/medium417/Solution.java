package com.github.ryan.algorithm.leetcode.medium.medium417;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    private int row, col;
    private int[][] dirs = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
    private boolean[][] visited;
    private int[][] mt;

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }

        mt = matrix;
        row = matrix.length;
        col = matrix[0].length;
        visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                resetVisited();
                if (dfs(i, j, true)) {
                    resetVisited();
                    if (dfs(i, j, false)) {
                        res.add(Arrays.asList(i, j));
                    }
                }
            }
        }
        return res;
    }

    private boolean dfs(int x, int y, boolean isPacific) {
        if (isPacific && canGoPacific(x, y)) return true;
        if (!isPacific && canGoAtlantic(x, y)) return true;

        for (int i = 0; i < 4; i++) {
            int newx = dirs[i][0] + x;
            int newy = dirs[i][1] + y;
            if (inArea(newx, newy)
                    && !visited[newx][newy]
                    && mt[newx][newy] <= mt[x][y]) {
                visited[newx][newy] = true;
                if (dfs(newx, newy, isPacific)) return true;
                visited[newx][newy] = false;
            }
        }
        return false;
    }

    private boolean canGoPacific(int m, int n) {
        return m == 0 || n == 0;
    }

    private boolean canGoAtlantic(int m, int n) {
        return m == row - 1 || n == col - 1;
    }

    private boolean inArea(int m, int n) {
        return m >= 0 && m < row && n >= 0 && n < col;
    }

    private void resetVisited() {
        for (int i = 0; i < visited.length; i++) {
            Arrays.fill(visited[i], false);
        }
    }

}
