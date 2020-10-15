package com.github.ryan.algorithm.leetcode.medium.medium1020;

public class Solution {

    private int[][] g;
    private int R;
    private int C;
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private boolean[][] visited;
    // 是否是飞地
    private boolean isEnclave;

    public int numEnclaves(int[][] A) {
        g = A;
        R = A.length;
        C = A[0].length;
        visited = new boolean[R][C];

        int res = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j] && g[i][j] == 1) {
                    isEnclave = true;
                    int num = dfs(i, j);
                    if (isEnclave) {
                        res += num;
                    }
                }
            }
        }
        return res;
    }

    // 从顶点(x,y) 开始 dfs，返回连通分量的大小，并标记是否属于飞地
    // 除了返回连通分量的大小，该方法存在副作用即标记该连通分量是否属于飞地
    private int dfs(int x, int y) {
        visited[x][y] = true;
        if (inBoundary(x, y)) {
            isEnclave = false;
        }

        int res = 1;
        for (int d = 0; d < dirs.length; d++) {
            int nextx = x + dirs[d][0];
            int nexty = y + dirs[d][1];
            if (inArea(nextx, nexty)
                    && !visited[nextx][nexty]
                    && g[nextx][nexty] == 1) {
                res += dfs(nextx, nexty);
            }
        }
        return res;
    }


    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    private boolean inBoundary(int x, int y) {
        return x == 0 || x == R - 1 || y == 0 || y == C - 1;
    }

}
