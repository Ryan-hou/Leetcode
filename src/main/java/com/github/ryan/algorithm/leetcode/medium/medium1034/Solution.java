package com.github.ryan.algorithm.leetcode.medium.medium1034;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    private int[][] grid;
    private int R;
    private int C;
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int oldColor;
    private boolean[][] visited;
    // 存储待染色的 border 的坐标值
    private List<int[]> border;

    public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
        this.grid = grid;
        R = grid.length;
        C = grid[0].length;
        oldColor = grid[r0][c0];
        if (oldColor == color) {
            return grid;
        }
        visited = new boolean[R][C];
        border = new ArrayList<>();

        dfs(r0, c0);
        // 染色
        for(int[] data : border) {
            grid[data[0]][data[1]] = color;
        }
        return grid;
    }

    // 从 grid[x][y] 开始 dfs，查找(x,y) 的连通分量，并记录位于 border 的坐标
    private void dfs(int x, int y) {
        visited[x][y] = true;
        if (needDye(x, y)) {
            border.add(new int[] {x, y});
        }

        for (int d = 0; d < dirs.length; d++) {
            int nextx = x + dirs[d][0];
            int nexty = y + dirs[d][1];
            if (inArea(nextx, nexty)
                    && !visited[nextx][nexty]
                    && grid[nextx][nexty] == oldColor) {
                dfs(nextx, nexty);
            }
        }
    }

    private boolean needDye(int x, int y) {
        return inBorder(x, y) || nextToDiffColor(x, y);
    }

    private boolean nextToDiffColor(int x, int y) {
        for (int d = 0; d < dirs.length; d++) {
            int nextx = x + dirs[d][0];
            int nexty = y + dirs[d][1];
            if (inArea(nextx, nexty)
                    && grid[nextx][nexty] != oldColor) {
                return true;
            }
        }
        return false;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    private boolean inBorder(int x, int y) {
        return x == 0 || y == 0 || x == R - 1 || y == C - 1;
    }

}
