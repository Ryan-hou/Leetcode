package com.github.ryan.data_structure.graph.search_bfs;


import java.util.ArrayDeque;
import java.util.Queue;

public class LC1091 {

    /**
     * 八连通
     */
    private int[][] dirs = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1},
            {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

    private int R, C;

    public int shortestPathBinaryMatrix(int[][] grid) {
        R = grid.length;
        C = R;
        if (grid[0][0] == 1) {
            return -1;
        }
        if (R == 1) {
            return 1;
        }

        boolean[][] visited = new boolean[R][C];
        int[][] dis = new int[R][C];
        // 二维坐标转为一维
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        visited[0][0] = true;
        dis[0][0] = 1;

        // BFS 求解最短路径
        while (!q.isEmpty()) {
            int cur = q.poll();
            int curx = cur / C, cury = cur % C;
            for (int d = 0; d < dirs.length; d++) {
                int nextx = curx + dirs[d][0];
                int nexty = cury + dirs[d][1];
                if (inArea(nextx, nexty)
                        && !visited[nextx][nexty]
                        && grid[nextx][nexty] == 0) {
                    q.offer(nextx * C + nexty);
                    visited[nextx][nexty] = true;
                    dis[nextx][nexty] = dis[curx][cury] + 1;
                    if (nextx == R - 1 && nexty == C - 1) {
                        return dis[nextx][nexty];
                    }
                }
            }
        } // end while
        return -1;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
