package com.github.ryan.leetcode.algorithm.medium.medium516;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date March 22,2019
 */
public class Solution {

    // 递推
    // bottom-up: use dp
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        // dp[i][j]: the longest palindromic subsequence's length of substring [i,j]
        // so: return dp[0][len - 1]
        // State transition：
        // if char[i] == char[j]: dp[i][j] = dp[i + 1][j - 1] + 2
        // else dp[i][j] = max(dp[i + 1][j], dp[i][j - 1])
        // Initialization: dp[i][i] = 1
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][len - 1];
    }

    // top-down: recursion + memorization
    private int[][] memo; // 记忆化搜索
    public int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        memo = new int[len][len];
        return helper(s, 0, len - 1);
    }

    // return s[i,j]'s longest palindromic subsequence
    private int helper(String s, int i, int j) {
        // 递归出口
        if (i > j) return 0;
        if (i == j) return 1;

        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = helper(s, i + 1, j - 1) + 2;
        } else {
            memo[i][j] = Math.max(helper(s, i + 1, j), helper(s, i, j - 1));
        }
        return memo[i][j];
    }
}
