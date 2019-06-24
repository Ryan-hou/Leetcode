package com.github.ryan.leetcode.algorithm.hard.hard10;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 24,2019
 */
public class Solution {

    public boolean isMatch(String s, String p) {
        int slen = s.length();
        int plen = p.length();
        // dp[i][j] -> p[0,j - 1] 是否能匹配 s[0, i - 1]
        boolean[][] dp = new boolean[slen + 1][plen + 1];
        dp[0][0] = true;
        for (int i = 1; i <= plen; i++) {
            if (p.charAt(i - 1) != '*') {
                dp[0][i] = false;
            } else {
                //  * is not the first character in p
                // i - 2 is ok
                dp[0][i] = dp[0][i - 2];
            }
        }

        for (int i = 1; i <= slen; i++) {
            char ch = s.charAt(i - 1);
            char[] parr = p.toCharArray();
            for (int j = 1; j <= plen; j++) {
                if (parr[j - 1] != '*') {
                    if (parr[j - 1] == ch || parr[j - 1] == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = false;
                    }
                } else {
                    // parr[j - 1] == '*'
                    if (parr[j - 2] == ch || parr[j - 2] == '.') {
                        dp[i][j] = dp[i - 1][j];
                    }
                    dp[i][j] |= dp[i][j - 2];
                }
            }
        }
        return dp[slen][plen];
    }

}
