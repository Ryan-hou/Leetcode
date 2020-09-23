package com.github.ryan.algorithm.leetcode.medium.medium54;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 23,2019
 */
public class Solution {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int[][] mt = matrix;
        if (mt == null ||mt.length ==0 || mt[0].length == 0) return res;

        int m = mt.length;
        int n = mt[0].length;
        int start = 0;
        // corner case: process only one row or one column
        while (m > start && n > start) {
            // left to right
            for (int i = start; i < n; i++) {
                res.add(mt[start][i]);
            }
            // top to bottom
            for (int i = start + 1; i < m; i++) {
                res.add(mt[i][n - 1]);
            }
            // right to left
            for (int i = n - 2; m - 1 != start && i > start; i--) {
                res.add(mt[m - 1][i]);
            }
            // bottom to top
            for (int i = m - 1; n - 1 != start && i > start; i--) {
                res.add(mt[i][start]);
            }

            start += 1;
            m -= 1;
            n -= 1;
        }
        return res;
    }
}
