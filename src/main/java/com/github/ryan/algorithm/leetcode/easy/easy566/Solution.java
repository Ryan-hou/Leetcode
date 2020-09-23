package com.github.ryan.algorithm.leetcode.easy.easy566;

public class Solution {

    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums == null || nums.length == 0
                ||  nums[0].length == 0
                || nums.length * nums[0].length != r * c) {
            return nums;
        }

        int[][] res = new int[r][c];
        int rows = 0, cols = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                res[rows][cols] = nums[i][j];
                cols++;
                if (cols == c) {
                    cols = 0;
                    rows++;
                }
            }
        }
        return res;
    }

}
