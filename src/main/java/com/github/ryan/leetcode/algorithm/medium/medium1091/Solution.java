package com.github.ryan.leetcode.algorithm.medium.medium1091;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

    private int[][] dirs = { {-1, 0}, {-1, 1}, {0, 1}, {1, 1},
                            {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    private boolean[][] visited;
    private int[][] dis;
    private int N;

    public int shortestPathBinaryMatrix(int[][] grid) {
        this.N = grid.length;
        if (grid[0][0] == 1 || grid[N - 1][N - 1] == 1) return -1;
        if (N == 1) return 1; // corner case

        visited = new boolean[N][N];
        dis = new int[N][N];

        // BFS
        // int[] -> x, y
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0});
        visited[0][0] = true;
        dis[0][0] = 1;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < dirs.length; d++) {
                int nextx = cur[0] + dirs[d][0];
                int nexty = cur[1] + dirs[d][1];
                if (inArea(nextx, nexty)
                        && !visited[nextx][nexty]
                        && grid[nextx][nexty] == 0) {
                    q.offer(new int[] {nextx, nexty});
                    visited[nextx][nexty] = true;
                    dis[nextx][nexty] = dis[cur[0]][cur[1]] + 1;

                    if (nextx == N - 1 && nexty == N - 1) {
                        return dis[nextx][nexty];
                    }
                }
            }
        } // end while
        return -1;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

}
