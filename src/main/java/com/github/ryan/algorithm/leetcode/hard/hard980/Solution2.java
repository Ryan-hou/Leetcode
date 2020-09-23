package com.github.ryan.algorithm.leetcode.hard.hard980;

/**
 * 实现思路: 求解 Hamilton 路径
 */
public class Solution2 {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int R;
    private int C;
    private int[][] grid;
    private int start, end;

    public int uniquePathsIII(int[][] grid) {
        R = grid.length;
        C = grid[0].length;
        this.grid = grid;

        int left = R * C;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] == 1) {
                    start = i * C + j;
                    grid[i][j] = 0;
                } else if (grid[i][j] == 2) {
                    end = i * C + j;
                    grid[i][j] = 0;
                } else if (grid[i][j] == -1) {
                    left--;
                }
            }
        }

        // State compression
        int visited = 0;
        return dfs(visited, start, left);
    }

    // 从顶点 start 出发且当前已经访问的顶点为 visited
    // 走完剩余的 left 个顶点直到 end 结束，总共的路径数
    private int dfs(int visited, int start, int left) {

        left--;
        visited = (visited | (1 << start));
        if (left == 0 && start == end) {
            return 1;
        }

        int res = 0;
        for (int i = 0; i < dirs.length; i++) {
            int nextx = dirs[i][0] + (start / C);
            int nexty = dirs[i][1] + (start % C);
            int next = nextx * C + nexty;
            if (inArea(nextx, nexty)
                    && grid[nextx][nexty] == 0
                    && (visited & (1 << next)) == 0) {
                res += dfs(visited, next, left);
            }
        }

        return res;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }

}
