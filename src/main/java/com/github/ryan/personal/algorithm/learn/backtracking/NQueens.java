package com.github.ryan.personal.algorithm.learn.backtracking;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className NQueens
 * 回溯法求解N皇后问题的所有解，并打印输出
 * 对比 LeetCode hard51
 *
 * @date December 25,2018
 */
@Slf4j
public class NQueens {

    private int[] result; // 记录N皇后问题的一个解,下标表示行, 值表示 queen 存储在哪一列

    public void solveNQueens(int n) {
        result = new int[n];
        _solveNQueens(n, 0);
    }

    // 设置第row行的皇后位置
    private void _solveNQueens(int n, int row) {

        // 递归出口
        if (row == n) {
            printResult(result);
            System.out.println();
            return;
        }

        for (int col = 0; col < n; col++) {
            if (valid(row, col, n)) {
                result[row] = col;
                _solveNQueens(n, row + 1);
            }
        }
    }

    // row行的皇后是否可以放置在col列
    private boolean valid(int row, int col, int n) {

        int leftup = col - 1, rightup = col + 1;
        for (int i = row - 1; i >= 0; i--) {
            // 判断第i行col是否有Queen
            if (result[i] == col) return false;
            // 判断左上对角线是否有Queen
            if (leftup >= 0) {
                if (result[i] == leftup) return false;
            }
            // 判断右上对角线是否有Queen
            if (rightup < n) {
                if (result[i] == rightup) return false;
            }
            leftup--;
            rightup++;
        }
        return true;
    }

    private void printResult(int[] result) {
        int n = result.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (result[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        nQueens.solveNQueens(6);
    }
}
