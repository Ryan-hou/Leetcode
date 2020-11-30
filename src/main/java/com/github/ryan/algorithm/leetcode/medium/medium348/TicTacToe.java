package com.github.ryan.algorithm.leetcode.medium.medium348;

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
public class TicTacToe {

    // Maintain count of each row, column, diagnol and antiDiagnol after every move.
    // if any of the count becomes equal to n return the player.
    private int num;
    private int diag;
    private int antiDiag;
    private int[] rowCount;
    private int[] colCount;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        num = n;
        diag = 0;
        antiDiag = 0;
        rowCount = new int[n];
        colCount = new int[n];
    }

    /** Player {player} makes a move at ({row}, {col}).
     @param row The row of the board.
     @param col The column of the board.
     @param player The player, can be either 1 or 2.
     @return The current winning condition, can be either:
     0: No one wins.
     1: Player 1 wins.
     2: Player 2 wins. */
    public int move(int row, int col, int player) {
        if (row == col) {
            diag += (player == 1 ? 1 : -1);
        }
        if (row + col == num - 1) {
            antiDiag += (player == 1 ? 1 : -1);
        }
        rowCount[row] += (player == 1 ? 1 : -1);
        colCount[col] += (player == 1 ? 1 : -1);

        if (Math.abs(diag) == num
                || Math.abs(antiDiag) == num
                || Math.abs(rowCount[row]) == num
                || Math.abs(colCount[col]) == num) {
            return player;
        }

        return 0;
    }
}
