package com.github.ryan.algorithm.leetcode.hard.hard773;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Solution {

    // BFS
    public int slidingPuzzle(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        String solved = "123450";
        // up/down/left/right
        int[][] dirs = new int[][] { {1, 0}, {-1, 0}, {0, -1}, {0, 1} };
        Queue<String> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        StringBuilder start = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                start.append(board[i][j]);
            }
        }
        if (solved.equals(start.toString())) return 0;
        q.offer(start.toString());
        visited.add(start.toString());
        int move = 1;
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();
                int index = cur.indexOf('0');
                for (int j = 0; j < dirs.length; j++) {
                    int m = index / cols + dirs[j][0];
                    int n = index % cols + dirs[j][1];
                    if (m >= 0 && m < rows && n >= 0 && n < cols) {
                        int nextIndex = m * cols + n;
                        String nextStr = swap(cur, index, nextIndex);
                        if (nextStr.equals(solved)) return move;
                        if (!visited.contains(nextStr)) {
                            q.offer(nextStr);
                            visited.add(nextStr);
                        }
                    }
                }
            } // end outer for
            move += 1;
        }
        return -1;

    }

    private String swap(String str, int i, int j) {
        char[] chs = str.toCharArray();
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
        return new String(chs);
    }

}
