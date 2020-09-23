package com.github.ryan.algorithm.leetcode.hard.hard675;

import java.util.ArrayDeque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

    public int cutOffTree(List<List<Integer>> forest) {

        // int[0] -> height, int[1] -> x, int[2] -> y
        // min heap
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int rows = forest.size();
        int cols = forest.get(0).size();
        int[][] f = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                f[i][j] = forest.get(i).get(j);
                if (f[i][j] > 1) {
                    pq.offer(new int[] { f[i][j], i, j });
                }
            }
        }

        int sum = 0;
        int startX = 0, startY = 0;
        while (!pq.isEmpty()) {
            int[] next = pq.poll();
            int endX = next[1], endY = next[2];
            int steps = bfs(f, startX, startY, endX, endY);
            if (steps == -1) {
                return -1;
            } else {
                sum += steps;
                startX = endX;
                startY = endY;
            }
        }
        return sum;
    }

    private int bfs(int[][] f, int startX, int startY, int endX, int endY) {
        int rows = f.length, cols = f[0].length;
        boolean[][] visited = new boolean[rows][cols];
        // queue elements:
        // int[0] -> steps from (startX, StartY) to (endX, endY)
        // int[1] -> x, int[2] -> y
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { 0, startX, startY });
        while (!q.isEmpty()) {
            int[] next = q.poll();
            if (next[1] < 0 || next[1] > rows - 1
                    || next[2] < 0 || next[2] > cols - 1
                    || visited[next[1]][next[2]]
                    || f[next[1]][next[2]] == 0) continue;

            visited[next[1]][next[2]] = true;
            if (next[1] == endX && next[2] == endY) {
                return next[0];
            } else {
                q.offer(new int[] { next[0] + 1, next[1] + 1, next[2] });
                q.offer(new int[] { next[0] + 1, next[1] - 1, next[2] });
                q.offer(new int[] { next[0] + 1, next[1], next[2] + 1 });
                q.offer(new int[] { next[0] + 1, next[1], next[2] - 1 });
            }
        }
        return -1;
    }
}
