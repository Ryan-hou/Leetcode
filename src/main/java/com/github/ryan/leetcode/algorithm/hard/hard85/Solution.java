package com.github.ryan.leetcode.algorithm.hard.hard85;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 20,2019
 */
public class Solution {

    public int maximalRectangle(char[][] matrix) {
        char[][] mt = matrix;
        if (mt == null || mt.length == 0 || mt[0].length == 0) return 0;

        int rows = mt.length;
        int cols = mt[0].length;
        int[] his = new int[cols];
        int max = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mt[i][j] == '1') {
                    his[j] += 1;
                } else {
                    his[j] = 0;
                }
            }
            // process one row
            max = Math.max(max, getMaxArea(his));
        }
        return max;
    }

    // refer to question "Largest Rectangle in Histogram"
    private int getMaxArea(int[] his) {
        int len = his.length;
        // l_idx[i] -> 数组 his 中第一个小于 his[i] 的下标值
        int[] l_idx = new int[len];
        // r_idx[i] -> 数组 his 中第一个大于 his[i] 的下标值
        int[] r_idx = new int[len];
        for (int i = 0; i < len; i++) {
            int p = i - 1;
            while (p >= 0 && his[p] >= his[i]) {
                p = l_idx[p];
            }
            l_idx[i] = p;

            int j = len - i - 1;
            p = j + 1;
            while (p < len && his[p] >= his[j]) {
                p = r_idx[p];
            }
            r_idx[j] = p;
        }

        int max = 0;
        for (int i = 0; i < len; i++) {
            max = Math.max(max, his[i] * (r_idx[i] - l_idx[i] - 1));
        }
        return max;
    }
}
