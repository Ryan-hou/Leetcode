package com.github.ryan.algorithm.leetcode.medium.medium74;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 23,2019
 */
public class Solution {

    public boolean searchMatrix(int[][] matrix, int target) {
        int[][] mt = matrix;
        if (mt == null || mt.length == 0 || mt[0].length == 0) return false;

        int m = mt.length;
        int n = mt[0].length;
        int l = 0, r = m * n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int row = mid / n;
            int col = mid % n;
            if (mt[row][col] == target) {
                return true;
            } else if (mt[row][col] < target) {
                l = mid + 1;
            } else {
                // mt[row][col] > target
                r = mid - 1;
            }
        }
        return false;
    }
}
