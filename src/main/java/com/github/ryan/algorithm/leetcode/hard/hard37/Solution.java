package com.github.ryan.algorithm.leetcode.hard.hard37;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 07,2019
 */
public class Solution {

    private static final char EMPTY_CHAR = '.';

    public void solveSudoku(char[][] board) {
        solve(0, 0, board);
    }

    // [row, col] -> start point
    // process row by row
    private boolean solve(int row, int col, char[][] board) {
        int sz = board.length;
        if (col == sz) {
            row += 1;
            col = 0;
            if (row == sz) {
                return true;
            }
        }

        // already has number
        if (board[row][col] != EMPTY_CHAR) {
            return solve(row, col + 1, board);
        }

        // try 1 ~ 9 at board[row][col]
        for (int value = 1; value <= 9; value++) {
            char ch = (char) (value + '0');
            if (canPlaceValue(board, row, col, ch)) {
                board[row][col] = ch;
                if (solve(row, col + 1, board)) { // recurse with VALID placement
                    return true;
                }
            }
        }

        // backtracking
        board[row][col] = EMPTY_CHAR;
        return false;
    }

    private boolean canPlaceValue(char[][] board, int row, int col, char char2Place) {
        int sz = board.length;
        // valid all rows
        for (int i = 0; i < sz; i++) {
            if (board[i][col] == char2Place) {
                return false;
            }
        }
        // valid all cols
        for (int i = 0; i < sz; i++) {
            if (board[row][i] == char2Place) {
                return false;
            }
        }
        // valid all sub-boxes
        int subBoxSz = 3;
        int rowIdx = row / subBoxSz;
        int colIdx = col / subBoxSz;
        // calculate top-left index
        int top = subBoxSz * colIdx;
        int left = subBoxSz * rowIdx;
        for (int i = 0; i < subBoxSz; i++) {
            for (int j = 0; j < subBoxSz; j++) {
                if (board[left + i][top + j] == char2Place) {
                    return false;
                }
            }
        }

        return true;
    }
}
