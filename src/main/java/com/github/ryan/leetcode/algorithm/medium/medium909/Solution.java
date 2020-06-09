package com.github.ryan.leetcode.algorithm.medium.medium909;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

    public int snakesAndLadders(int[][] board) {
        int row = board.length;
        int target = row * row;
        boolean[] visited = new boolean[target + 1];
        Queue<int[]> q = new ArrayDeque<>();
        // int[0] -> num, int[1] -> moves to this num
        q.offer(new int[] {1, 0});
        visited[1] = true;
        // BFS
        while (!q.isEmpty()) {
            int[] e = q.poll();
            for (int i = 1; i <= 6; i++) {
                if (e[0] + i > target) {
                    return -1;
                }
                if (visited[e[0] + i]) {
                    continue;
                }
                visited[e[0] + i] = true;
                int nextVal = nextVal(board, e[0] + i);
                int nextNum = (nextVal == -1 ? e[0] + i : nextVal);
                if (nextNum == target) {
                    return e[1] + 1;
                }
                q.offer(new int[] {nextNum, e[1] + 1});
            }
        }
        return -1;
    }

    private int nextVal(int[][] board, int num) {
        int size = board.length;
        // be careful! num start from 1
        int row = (num - 1)/ size;
        int col = (num - 1) % size;
        if (row % 2 == 0) {
            return board[size - 1 - row][col];
        } else {
            return board[size - 1 - row][size - 1 - col];
        }
    }

}
