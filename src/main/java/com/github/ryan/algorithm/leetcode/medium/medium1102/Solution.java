package com.github.ryan.algorithm.leetcode.medium.medium1102;

import java.util.PriorityQueue;

public class Solution {

    // BFS + pq
    public int maximumMinimumPath(int[][] A) {
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int row = A.length;
        int col = A[0].length;
        boolean[][] visited = new boolean[row][col];
        // max heap
        PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> a2[2] - a1[2]);
        pq.offer(new int[] {0, 0, A[0][0]});
        visited[0][0] = true;
        while (!pq.isEmpty()) {
            int[] ele = pq.poll();
            int curRow = ele[0];
            int curCol = ele[1];
            int curMaxPath = ele[2];
            if (curRow == row - 1 && curCol == col - 1) {
                return curMaxPath;
            }

            for (int i = 0; i < dirs.length; i++) {
                int nextRow = curRow + dirs[i][0];
                int nextCol = curCol + dirs[i][1];
                if (nextRow < 0 || nextRow > row - 1
                        || nextCol < 0 || nextCol > col - 1
                        || visited[nextRow][nextCol]) {
                    continue;
                }

                pq.offer(new int[] {nextRow, nextCol, Math.min(curMaxPath, A[nextRow][nextCol])});
                visited[nextRow][nextCol] = true;
            }
        }
        throw new IllegalArgumentException("Invaid input.");
    }

}
