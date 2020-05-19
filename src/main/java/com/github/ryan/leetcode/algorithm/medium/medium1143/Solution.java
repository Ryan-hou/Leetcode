package com.github.ryan.leetcode.algorithm.medium.medium1143;

public class Solution {

    // LCS -> DP
    public int longestCommonSubsequence(String text1, String text2) {
        char[] ch1 = text1.toCharArray();
        char[] ch2 = text2.toCharArray();
        // dp[i][j] -> lcs from text1[i...text1.length - 1] and text2[j...text2.length - 1]
        int[][] dp = new int[ch1.length + 1][ch2.length + 1];
        for (int i = ch1.length - 1; i >= 0; i--) {
            for (int j = ch2.length - 1; j >= 0; j--) {
                if (ch1[i] == ch2[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][0];
    }

}
