package com.github.ryan.leetcode.algorithm.hard.hard52;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 05,2019
 */
public class Solution {

    private boolean[] col;
    private boolean[] dia1;
    private boolean[] dia2;
    private int queenNum;

    public int totalNQueens(int n) {
        queenNum = 0;
        col = new boolean[n];
        dia1 = new boolean[2 * n - 1];
        dia2 = new boolean[2 * n - 1];
        putQueen(n, 0);
        return queenNum;
    }

    // 在一个n皇后问题中，尝试摆放第idx行的皇后
    private void putQueen(int n, int idx) {
        if (idx == n) {
            queenNum += 1;
            return;
        }
        // i 为 col index
        for (int i = 0; i < n; i++) {
            // 两条对角线下标值 -》row + col, row - col + n - 1
            if (!col[i] && !dia1[idx + i]
                    && !dia2[idx - i + n - 1]) {
                col[i] = true;
                dia1[idx + i] = true;
                dia2[idx - i + n - 1] = true;
                putQueen(n, idx + 1);
                // backtracking
                col[i] = false;
                dia1[idx + i] = false;
                dia2[idx - i + n - 1] = false;
            }
        }
        return;
    }
}
