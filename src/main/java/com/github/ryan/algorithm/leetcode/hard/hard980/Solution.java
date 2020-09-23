package com.github.ryan.algorithm.leetcode.hard.hard980;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 21,2019
 */
@Slf4j
public class Solution {

    public int uniquePathsIII(int[][] grid) {
        int[][] g = grid;
        int m = g.length;
        int n = g[0].length;

        // [s0, s1] is start
        int s0 = 0, s1 = 0;
        int obst = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 1) {
                    s0 = i;
                    s1 = j;
                } else if (g[i][j] == -1) {
                    obst++;
                }
            }
        }

        int[][] visited = new int[m][n];
        visited[s0][s1] = 1;
        int left = m * n - obst - 1; // left point need to visit
        return path(g, s0 + 1, s1, left, visited) + path(g, s0 -1, s1, left, visited)
                + path(g, s0, s1 + 1, left, visited) + path(g, s0, s1 - 1, left, visited);
    }

    private int path(int[][] g, int m, int n, int left, int[][] visited) {
        if (m < 0 || m >= g.length || n < 0 || n >= g[0].length
                || g[m][n] == -1 || visited[m][n] == 1) {
            return 0;
        }
        if (g[m][n] == 2) {
            return left == 1 ? 1 : 0;
        }
        // g[m][n] == 0
        visited[m][n] = 1;
        left--;
        int total = path(g, m - 1, n, left, visited) + path(g, m + 1, n, left, visited)
                + path(g, m, n - 1, left, visited)
                + path(g, m, n + 1, left, visited);
        // backtracking
        visited[m][n] = 0;
        left++;
        return total;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}};
        log.info("unique paths = {}", new Solution().uniquePathsIII(grid));
    }
}
