package com.github.ryan.algorithm.leetcode.medium.medium1219;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    private int[][] g;
    private int R;
    private int C;
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private boolean[][] visited;
    private int res;
    private Set<Integer> empty;

    public int getMaximumGold(int[][] grid) {
        this.g = grid;
        R = grid.length;
        C = grid[0].length;
        visited = new boolean[R][C];
        empty = new HashSet<>();


        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (g[i][j] == 0) {
                    empty.add(i * C + j);
                }
            }
        }

        res = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (g[i][j] != 0) {
                    res = Math.max(res, dfs(i, j));
                }
            }
        }
        return res;
    }


    // 从 g[i][j] 开始dfs，求解同一个连通分量中的最大值
    private int dfs(int i, int j) {
        visited[i][j] = true;

        int next = 0;
        for (int d = 0; d < dirs.length; d++) {
            int nextx = i + dirs[d][0];
            int nexty = j + dirs[d][1];
            if (inArea(nextx, nexty)
                    && !visited[nextx][nexty]
                    && !empty.contains(nextx * C + nexty)) {
                next = Math.max(next, dfs(nextx, nexty));
            }
        }

        // backtracking
        visited[i][j] = false;
        return next + g[i][j];
    }


    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
