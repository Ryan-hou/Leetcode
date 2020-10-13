package com.github.ryan.data_structure.graph.search_bfs;

import java.util.*;

/**
 * 自动求解类似于华容道的益智游戏的基于图搜索的 AI
 * 每一个可能的棋盘面为一种状态，每一个状态代表图中的一个顶点
 * 每一个状态可以用一个 6 位整数表示，也可以用字符串表示
 * 这里采用字符串表示，进一步熟悉使用字符串表示图中顶点的思路
 *
 * 基于 BFS 的时间复杂度 O(V+E) -> O(V+4V) (四连通) -> 顶点数为棋盘格构成的所有排列组合 n!
 */
public class LC773 {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int R;
    private int C;

    public int slidingPuzzle(int[][] board) {
        R = board.length;
        C = board[0].length;

        final String endState = "123450";
        String initStat = boardToStr(board);
        if (endState.equals(initStat)) {
            return 0;
        }

        Queue<String> q = new ArrayDeque<>();
        Map<String, Integer> visited = new HashMap<>();
        q.offer(initStat);
        visited.put(initStat, 0);

        // BFS
        while (!q.isEmpty()) {
            String cur = q.poll();
            List<String> nexts = getNext(cur);

            for (String next : nexts) {
                if (!visited.containsKey(next)) {
                    q.offer(next);
                    visited.put(next, visited.get(cur) + 1);
                    if (endState.equals(next)) {
                        return visited.get(next);
                    }
                }
            }
        } // end while
        return -1;
    }

    private List<String> getNext(String s) {
        int zero;
        for (zero = 0; zero < s.length(); zero++) {
            if (s.charAt(zero) == '0') {
                break;
            }
        }

        int[][] cur = strToBoard(s);
        List<String> res = new ArrayList<>();
        int zx = zero / C, zy = zero % C;
        for (int d = 0; d < dirs.length; d++) {
            int nextx = zx + dirs[d][0];
            int nexty = zy + dirs[d][1];
            if (inArea(nextx, nexty)) {
                swap(cur, zx, zy, nextx, nexty);
                res.add(boardToStr(cur));
                // 重置棋盘
                swap(cur, zx, zy, nextx, nexty);
            }
        }

        return res;
    }

    private void swap(int[][] b, int x1, int y1, int x2, int y2) {
        int tmp = b[x1][y1];
        b[x1][y1] = b[x2][y2];
        b[x2][y2] = tmp;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    private String boardToStr(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    private int[][] strToBoard(String board) {
        int[][] res = new int[R][C];
        for (int i = 0; i < board.length(); i++) {
            res[i / C][i % C] = board.charAt(i) - '0';
        }
        return res;
    }
}
