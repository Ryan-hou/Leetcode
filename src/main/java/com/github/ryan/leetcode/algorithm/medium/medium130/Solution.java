package com.github.ryan.leetcode.algorithm.medium.medium130;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 31,2019
 */
public class Solution {

    // use dfs
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;

        int rows = board.length;
        int cols = board[0].length;
        // process left and right border
        for (int i = 0; i < rows; i++) {
            dfs(board, i, 0);
            dfs(board, i, cols - 1);
        }

        // process top and bottom border
        for (int i = 0; i < cols; i++) {
            dfs(board, 0, i);
            dfs(board, rows - 1, i);
        }

        // flip char
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == '#') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }


    private void dfs(char[][] board, int row, int col) {
        if (row < 0 || row > board.length - 1
                || col < 0 || col > board[0].length - 1)
            return;
        if (board[row][col] != 'O') return;

        board[row][col] = '#';
        // prcocess top/down/left/right directions
        dfs(board, row - 1, col);
        dfs(board, row + 1, col);
        dfs(board, row, col - 1);
        dfs(board, row, col + 1);
    }
}
