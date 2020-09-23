package com.github.ryan.algorithm.leetcode.medium.medium240;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 18,2019
 */
public class Solution {

    // time complexity: O(m + n)
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

        int m = matrix.length;
        int n = matrix[0].length;
        // start from top right, move to left down
        int row = 0, col = n - 1;
        while (row < m && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                row++;
            } else {
                // matrix[row][col] > target
                col--;
            }
        }
        return false;
    }

}
