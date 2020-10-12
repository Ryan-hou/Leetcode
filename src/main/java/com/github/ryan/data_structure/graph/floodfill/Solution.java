package com.github.ryan.data_structure.graph.floodfill;

import java.util.HashSet;

/**
 * 图论建模:
 * 二维网格中的每一个点 -> 图中每一个顶点(二维转一维)
 * 二维网格中每一对相邻的陆地 -> 图中的边
 * 求解最大的岛屿面积 -> 图中最大的连通分量包含的顶点数
 */
public class Solution {

    /**
     * 四连通
     */
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private int[][] grid;
    // row
    private int R;
    // column
    private int C;

    // 图的邻接表表示
    private HashSet<Integer>[] g;
    private boolean[] visited;

    public int maxAreaOfIsland(int[][] grid) {
        // grid is a non-empty 2D array
        this.grid = grid;
        R = grid.length;
        C = grid[0].length;
        g = constructGraph();
        visited = new boolean[g.length];

        int res = 0;
        for (int v = 0; v < g.length; v++) {
            int x = v / C, y = v % C;
            if (grid[x][y] == 1 && !visited[v]) {
                // dfs 求解连通分量的大小
                res = Math.max(res, dfs(v));
            }
        }
        return res;
    }

    /**
     * 从顶点 v 开始 dfs 求解连通分量的大小
     * @param v
     * @return
     */
    private int dfs(int v) {
        visited[v] = true;
        int res = 1;
        for (int w : g[v]) {
            if (!visited[w]) {
                res += dfs(w);
            }
        }
        return res;
    }

    private HashSet<Integer>[] constructGraph() {
        g = new HashSet[R * C];
        for (int v = 0; v < g.length; v++) {
            g[v] = new HashSet<>();
        }

        for (int v = 0; v < g.length; v++) {
            int x = v / C, y = v % C;
            if (grid[x][y] == 1) {
                for (int d = 0; d < dirs.length; d++) {
                    int nextx = x + dirs[d][0];
                    int nexty = y + dirs[d][1];
                    if (inArea(nextx, nexty) && grid[nextx][nexty] == 1) {
                        // 构建边
                        int next = nextx * C + nexty;
                        g[v].add(next);
                        g[next].add(v);
                    }
                }
            }
        }

        return g;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
