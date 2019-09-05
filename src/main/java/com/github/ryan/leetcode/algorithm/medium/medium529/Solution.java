package com.github.ryan.leetcode.algorithm.medium.medium529;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

    private static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private final int[][] direct = { {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1} };

    // BFS
    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0], y = click[1];
        int rows = board.length;
        int cols = board[0].length;
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }

        Queue<Point> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[rows][cols];
        q.offer(new Point(x, y));
        visited[x][y] = true;
        while (!q.isEmpty()) {
            // 'E'
            Point cur = q.poll();
            int num = 0;
            for (int i = 0; i < 8; i++) {
                int nx = cur.x + direct[i][0];
                int ny = cur.y + direct[i][1];
                if (nx >= 0 && nx < rows && ny >= 0 && ny < cols
                        && board[nx][ny] == 'M') {
                    num++;
                }
            }

            if (num == 0) {
                board[cur.x][cur.y] = 'B';
                for (int i = 0; i < 8; i++) {
                    int nx = cur.x + direct[i][0];
                    int ny = cur.y + direct[i][1];
                    if (nx >= 0 && nx < rows && ny >= 0 && ny < cols
                            && !visited[nx][ny] && board[nx][ny] == 'E') {
                        q.offer(new Point(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            } else {
                board[cur.x][cur.y] = (char) (num + '0');
            }
        }
        return board;
    }

}
