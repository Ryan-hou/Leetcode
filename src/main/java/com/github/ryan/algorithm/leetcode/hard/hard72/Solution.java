package com.github.ryan.algorithm.leetcode.hard.hard72;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 19,2019
 */
public class Solution {

    // use dp: time complexity O(mn)
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        // dp[i][j] -> min distance from i len of word1 and j len of word2
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j; // remove from word2
                } else if (j == 0) {
                    dp[i][j] = i; // remove from word1
                } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // word1.charAt(i - 1) != word2.charAt(j - 1)
                    dp[i][j] = 1 + min(dp[i - 1][j - 1], // replace
                            dp[i - 1][j], // remove cur char
                            dp[i][j - 1]); // insert
                }
            }
        }
        return dp[m][n];
    }

    private int min(int x, int y, int z) {
        return Math.min(x, Math.min(y, z));
    }
}
