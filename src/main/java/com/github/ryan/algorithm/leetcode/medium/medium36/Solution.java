package com.github.ryan.algorithm.leetcode.medium.medium36;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 27,2019
 */
@Slf4j
public class Solution {

    public boolean isValidSudoku(char[][] board) {
        char[][] b = board;
        int[] rowMap = new int[10];
        int[][] colMap = new int[9][10];
        int[][] subMap = new int[3][10];
        // i -> row, j -> col
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char ch = b[i][j];
                if (ch == '.') continue;

                // check row repetition
                if (rowMap[ch - '0'] != 0) {
                    return false;
                }
                rowMap[ch - '0'] = ch - '0';
                // check column repetition
                if (colMap[j][ch - '0'] != 0) {
                    return false;
                }
                colMap[j][ch - '0'] = ch - '0';
                // check sub-box repetition
                if (subMap[j / 3][ch - '0'] != 0) {
                    return false;
                }
                subMap[j / 3][ch - '0'] = ch - '0';
            }
            Arrays.fill(rowMap, 0);
            if (i == 2|| i == 5) {
                Arrays.fill(subMap[0], 0);
                Arrays.fill(subMap[1], 0);
                Arrays.fill(subMap[2], 0);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };

        log.info("res = {}", new Solution().isValidSudoku(board));
    }
}
