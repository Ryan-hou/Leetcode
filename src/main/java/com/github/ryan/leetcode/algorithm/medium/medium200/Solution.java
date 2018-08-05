package com.github.ryan.leetcode.algorithm.medium.medium200;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date February 02,2018
 */

/**
 * 使用dfs实现floodfill算法
 */

@Slf4j
public class Solution {

    private static final int[][] d = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0}
    };

    private static int m, n;
    private static boolean[][] visited;

    private static boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }


    public static int numIslands(char[][] grid) {
        m = grid.length;
        if (m == 0) {
            return 0;
        }
        n = grid[0].length;

        visited = new boolean[m][n];

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    res++;
                    dfs(grid, i, j);
                }
            }
        }

        return res;
    }

    // 从grid[x][y]的位置开始，进行floodfill
    private static void dfs(char[][] grid, int x, int y) {

        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int newx = x + d[i][0];
            int newy = y + d[i][1];
            // 保证(x,y)合法，且grid[x][y]是没有被访问的陆地，相当于隐含的递归出口
            if (inArea(newx, newy) && !visited[newx][newy]
                    && grid[newx][newy] == '1') {
                dfs(grid, newx, newy);
            }
        }
        return;
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'},
        };

        log.info("Number of islands is {}", numIslands(grid));
    }
}
