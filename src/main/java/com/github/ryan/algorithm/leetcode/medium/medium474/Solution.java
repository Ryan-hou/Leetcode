package com.github.ryan.algorithm.leetcode.medium.medium474;

public class Solution {

    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length < 1) return 0;
        // dp[i][j] -> max form from i len of 0 and j len of 1
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int count0 = str.replace("1", "").length();
            int count1 = str.length() - count0;
            for (int i = m; i >= count0; i--) {
                for (int j = n; j >= count1; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - count0][j - count1] + 1);
                }
            }
        }
        return dp[m][n];
    }

}
