package com.github.ryan.algorithm.leetcode.medium.medium289;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 17,2019
 */
public class Solution {

    public void gameOfLife(int[][] board) {
        // Neighbors array to find 8 neighboring cells
        // for a given cell
        int[] neighbors = {0, 1, -1};
        int rows = board.length;
        int cols = board[0].length;

        // create a copy of the original board
        int[][] cpBoard = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cpBoard[i][j] = board[i][j];
            }
        }

        // Iterate through board cell be cell
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // For each cell count the number of live neighbors
                int liveNeighbors = 0;
                // 9 - 1 = 8 neighbors
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (!(neighbors[i] == 0 && neighbors[j] == 0)) {
                            int r = row + neighbors[i];
                            int c = col + neighbors[j];
                            if (r < rows && r >= 0
                                    && c < cols && c >= 0
                                    && cpBoard[r][c] == 1) {
                                liveNeighbors += 1;
                            }
                        }
                    }
                }

                // Rule 1 or Rule 3
                if (cpBoard[row][col] == 1
                        && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[row][col] = 0;
                }
                // Rule 4
                if (cpBoard[row][col] == 0 && liveNeighbors == 3) {
                    board[row][col] = 1;
                }
            }
        }
    }
}
