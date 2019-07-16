package com.github.ryan.leetcode.algorithm.medium.medium73;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 16,2019
 */
public class Solution {

    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;

        int m = matrix.length;
        int n = matrix[0].length;
        Set<Integer> rowSet = new HashSet<>();
        Set<Integer> colSet = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rowSet.add(i);
                    colSet.add(j);
                }
            }
        }

        for (int row : rowSet) {
            setRowZero(row, n, matrix);
        }
        for (int col : colSet) {
            setColZero(col, m, matrix);
        }
    }

    private void setColZero(int col, int m, int[][] matrix) {
        for (int i = 0; i < m; i++) {
            matrix[i][col] = 0;
        }
    }

    private void setRowZero(int row, int n, int[][] matrix) {
        for (int i = 0; i < n; i++) {
            matrix[row][i] = 0;
        }
    }
}
