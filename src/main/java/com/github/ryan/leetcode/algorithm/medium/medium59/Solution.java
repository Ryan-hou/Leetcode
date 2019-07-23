package com.github.ryan.leetcode.algorithm.medium.medium59;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 23,2019
 */
public class Solution {

    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int start = 0;
        int val = 1;
        while (start < n) {
            // left to right
            for (int i = start; i < n; i++) {
                res[start][i] = val++;
            }
            // top to bottom
            for (int i = start + 1; i < n; i++) {
                res[i][n - 1] = val++;
            }
            // right to left
            for (int i = n - 2; n - 1 != start && i > start; i--) {
                res[n - 1][i] = val++;
            }
            // bottom to top
            for (int i = n - 1; n - 1 != start && i > start; i--) {
                res[i][start] = val++;
            }

            start += 1;
            n -= 1;
        }
        return res;
    }

}
