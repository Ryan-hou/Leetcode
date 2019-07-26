package com.github.ryan.leetcode.algorithm.hard.hard115;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 26,2019
 */
public class Solution {

    // dp: time complexity O(mn)
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        if (m < n) return 0;

        // dp[i] -> subsequences of s for  i lenth of t
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = n; j >= 1; j--) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return dp[n];
    }

}
