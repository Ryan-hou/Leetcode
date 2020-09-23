package com.github.ryan.algorithm.leetcode.medium.medium994;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

    public int orangesRotting(int[][] grid) {
        // rotting oranges queue
        Queue<int[]> q = new ArrayDeque<>();
        int freshCount = 0;
        int row = grid.length;
        int col = grid[0].length;
        // Initialize data
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    freshCount++;
                } else if (grid[i][j] == 2) {
                    q.offer(new int[] {i, j});
                }
            }
        }
        // Mark the round i.e the ticker of timestamp
        q.offer(new int[] {-1, -1});

        int minutes = -1;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        // BFS
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] == -1) {
                // we finish one round of processing
                minutes++;
                if (!q.isEmpty()) {
                    q.offer(new int[] {-1, -1});
                }
            } else {
                for (int[] d : dirs) {
                    int nextRow = cur[0] + d[0];
                    int nextCol = cur[1] + d[1];
                    if (nextRow >= 0 && nextRow < row
                            && nextCol >= 0 && nextCol < col
                            && grid[nextRow][nextCol] == 1) {
                        grid[nextRow][nextCol] = 2;
                        freshCount--;
                        // this orange would then contaminate other oranges
                        q.offer(new int[] {nextRow, nextCol});
                    }
                }
            }
        } // end while
        return freshCount == 0 ? minutes : -1;
    }

}
