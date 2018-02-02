package com.leetcode.ryan.algorithm.medium.medium79;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date February 02,2018
 */

/**
 * 二维平面上的回溯法：
 * 用到了二维数组的一些技巧：
 * 1）二维数组和坐标轴的映射要搞清楚
 * 2）二维数组如何在四个方向移动
 * 3）用数组保存是否被访问过
 * 4）二位数组边界判断
 */
@Slf4j
public class Solution {

    private static int m,n; // 二维数组的行与列

    // 二维数组表示的坐标轴，行维度表示x轴，列维度表示y轴
    // d为在坐标轴中向上，右，下，左四个方向移动的坐标表示
    private static final int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static boolean[][] visited;

    private static boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }


    public static boolean exist(char[][] board, String word) {
        m = board.length;
        assert (m > 0);
        n = board[0].length;

        visited = new boolean[m][n];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (searchWord(board, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 从board[startx][starty]开始，寻找word[index...word.size())
    private static boolean searchWord(char[][] board, String word, int index
            , int startx, int starty) {

        // 递归出口
        if (index == word.length() - 1) {
            return board[startx][starty] == word.charAt(index);
        }

        if (board[startx][starty] == word.charAt(index)) {
            visited[startx][starty] = true;
            // 从startx，starty出发，向四个方向寻找
            for (int i = 0; i < 4; i++) {
                int newx = startx + d[i][0];
                int newy = starty + d[i][1];

                if (inArea(newx, newy) && !visited[newx][newy]
                        && searchWord(board, word, index + 1, newx, newy)) {
                    return true;
                }
            }

            visited[startx][starty] = false;
        }

        return false;
    }


    public static void main(String[] args) {
        char[][] broad = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        log.info("{} is exist? {}", "ABCCED", exist(broad, "ABCCED"));
        log.info("{} is exist? {}", "SEE", exist(broad, "SEE"));
    }

}
