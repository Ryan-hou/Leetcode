package com.github.ryan.data_structure.graph.floodfill;

/**
 * 采用 floodfill 遍历二维表格所表示的图，图的顶点和边的定义不变，但是不显式构建图
 */
public class Solution2 {

    private int[][] grid;
    private int R;
    private int C;
    // (x, y) 表示图中的顶点
    private boolean[][] visited;

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int maxAreaOfIsland(int[][] grid) {
        // grid is a non-empty 2D array
        this.grid = grid;
        R = grid.length;
        C = grid[0].length;

        visited = new boolean[R][C];

        int res = 0;
        // 从一点开始 floodfill，每次 floodfill 返回一个连通分量的大小
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    res = Math.max(res, dfs(i, j));
                }
            }
        }
        return res;
    }

    /**
     * 从顶点 (x, y) 开始 dfs，返回连通分量的顶点个数
     * @param x
     * @param y
     * @return
     */
    private int dfs(int x, int y) {
        visited[x][y] = true;
        int res = 1;
        for (int d = 0; d < dirs.length; d++) {
            int nextx = x + dirs[d][0];
            int nexty = y + dirs[d][1];
            if (inArea(nextx, nexty)
                    &&!visited[nextx][nexty]
                    && grid[nextx][nexty] == 1) {
                // (x, y) -> (nextx, nexty) 存在边
                res += dfs(nextx, nexty);
            }
        }
        return res;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
