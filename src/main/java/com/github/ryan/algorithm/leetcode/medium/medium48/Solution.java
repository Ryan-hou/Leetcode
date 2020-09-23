package com.github.ryan.algorithm.leetcode.medium.medium48;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 14,2019
 */
public class Solution {

    // 从外向内一层一层的旋转90度，每一层上每个节点走边长的距离
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length < 2) return;

        int n = matrix.length;
        int start = 0;
        for (int i = n; i >= 2; i -= 2) {
            // n -> 边长
            // start node -> [start, start]
            for (int j = 0; j < i - 1; j++) {
                // 下标计算容易出错，写几个测试用例，对着测试数据组织下标
                int temp = matrix[start][start + j];
                int num = n - 1 - start - j;
                matrix[start][start + j] = matrix[num][start];
                matrix[num][start] = matrix[n - 1 - start][num];
                matrix[n - 1 -start][num] = matrix[n - 1 - num][n - 1 - start];
                matrix[n - 1 - num][n - 1 - start] = temp;
            }
            start++;
        }
    }
}
