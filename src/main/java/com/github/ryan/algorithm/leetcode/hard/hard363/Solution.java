package com.github.ryan.algorithm.leetcode.hard.hard363;

import java.util.TreeSet;

public class Solution {

    public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < col; i++) {
            int[] dp = new int[row];
            for (int j = i; j < col; j++) {
                for (int r = 0; r < row; r++) {
                    dp[r] += matrix[r][j];
                }
                int curMax = maxInSubArray(dp, k);
                max = Math.max(max, curMax);
                if (max == k) {
                    return k;
                }
            } // end j loop
        }
        return max;
    }

    // a little tricy
    private int maxInSubArray(int[] dp, int k) {
        int max = Integer.MIN_VALUE;
        int curSum = 0;
        TreeSet<Integer> set = new TreeSet<>();
        // when curSum < k and i = 0, get ceil not null
        set.add(0);
        for (int i = 0; i < dp.length; i++) {
            curSum += dp[i];
            Integer ceil = set.ceiling(curSum - k);
            if (ceil != null) {
                max = Math.max(max, curSum - ceil);
            }
            set.add(curSum);
        }
        return max;
    }

}
