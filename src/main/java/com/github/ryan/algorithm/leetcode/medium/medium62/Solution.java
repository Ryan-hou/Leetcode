package com.github.ryan.algorithm.leetcode.medium.medium62;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 11,2019
 */
public class Solution {


    // use dp: time complecity O(m*n)
    public int uniquePaths2(int m, int n) {
        // dp[i][j] -> dp[0][0] 到 dp[i][j] 的 unique paths
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int i = 0; i < n; i++) dp[0][i] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    // BFS: 时间复杂度指数级 -> 超时
    public int uniquePaths(int m, int n) {
        int res = 0;
        Queue<Pair> q = new ArrayDeque<>();
        q.offer(Pair.makePair(1, 1)); // initialize start (1,1)
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int col = pair.col;
            int row = pair.row;

            if (col == m && row == n) {
                res++;
            } else {
                if (col + 1 <= m) {
                    q.offer(Pair.makePair(col + 1, row));
                }
                if (row + 1 <= n) {
                    q.offer(Pair.makePair(col, row + 1));
                }
            }
        }
        return res;
    }

    private static class Pair {
        public int col;
        public int row;

        private Pair(int col, int row) {
            this.col = col;
            this.row = row;
        }

        public static Pair makePair(int col, int row) {
            return new Pair(col, row);
        }
    }
}
