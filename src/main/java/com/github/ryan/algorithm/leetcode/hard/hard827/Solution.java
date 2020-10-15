package com.github.ryan.algorithm.leetcode.hard.hard827;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    private int[][] grid;
    private int N;
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int largestIsland(int[][] grid) {
        this.grid = grid;
        N = grid.length;
        // area[i] -> grid 中被标记为 i 的连通分量的大小
        int[] area = new int[N * N + 2];
        // i 从 2 开始，避免和 0，1 冲突
        int idx = 2;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    area[idx] = dfs(i, j, idx);
                    idx++;
                }
            }
        }

        int res = 0;
        for (int ccount : area) {
            res = Math.max(res, ccount);
        }

        // 尝试改变 0 为 1
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 0) {
                    // 记录相邻连通分量的 idx, 使用 set 避免重复计算
                    Set<Integer> seen = new HashSet<>();
                    for (int d = 0; d < dirs.length; d++) {
                        int nextx = i + dirs[d][0];
                        int nexty = j + dirs[d][1];
                        if (inArea(nextx, nexty)) {
                            seen.add(grid[nextx][nexty]);
                        }
                    }
                    int sum = 1;
                    for (int s : seen) {
                        sum += area[s];
                    }

                    res = Math.max(res, sum);
                }
            }
        }

        return res;
    }

    // 从 grid[i][j] 开始 dfs，标记同一个连通分量中的顶点值为 idx
    // 并返回连通分量中的顶点个数
    private int dfs(int x, int y, int idx) {
        grid[x][y] = idx;
        int res = 1;
        for (int d = 0; d < dirs.length; d++) {
            int nextx = x + dirs[d][0];
            int nexty = y + dirs[d][1];
            if (inArea(nextx, nexty) && grid[nextx][nexty] == 1) {
                res += dfs(nextx, nexty, idx);
            }
        }
        return res;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

}
