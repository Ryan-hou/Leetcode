package com.github.ryan.leetcode.algorithm.easy.easy661;

public class Solution {

    public int[][] imageSmoother(int[][] M) {
        int rows = M.length;
        int cols = M[0].length;
        int[][] res = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                res[i][j] = smooth(M, rows, cols, i, j);
            }
        }
        return res;
    }

    private int smooth(int[][] M, int rows, int cols, int x, int y) {
        int sum = 0;
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (x + i < 0 || x + i >= rows || y + j < 0 || y + j >= cols) {
                    continue;
                }
                count++;
                sum += M[x + i][y + j];
            }
        }
        return sum / count;
    }
}
