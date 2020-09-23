package com.github.ryan.algorithm.leetcode.hard.hard44;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 07,2019
 */
public class Solution {

    // use dp
    public boolean isMatch(String s, String p) {
        int slen = s.length();
        int plen = p.length();
        // dp[i][j] -> j length of p can match i length of s
        boolean[][] dp = new boolean[slen + 1][plen + 1];
        // empty can match empty;
        dp[0][0] = true;
        for (int i = 1; i <= slen; i++) {
            // empty p can't match not empty s
            dp[i][0] = false;
        }
        for (int i = 1; i <= plen; i++) {
            dp[0][i] = (p.charAt(i - 1) == '*' && dp[0][i - 1]);
        }

        for (int i = 1; i <= slen; i++) {
            for (int j = 1; j <= plen; j++) {
                if (p.charAt(j - 1) == '*') {
                    // '*' can match empty / current char / current char and previous chars
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j - 1] || dp[i - 1][j];
                } else {
                    dp[i][j] = (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1))
                            && dp[i - 1][j - 1];
                }
            }
        }
        return dp[slen][plen];
    }
}
