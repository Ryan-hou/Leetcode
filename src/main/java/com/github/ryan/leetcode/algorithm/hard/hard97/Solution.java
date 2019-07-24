package com.github.ryan.leetcode.algorithm.hard.hard97;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 24,2019
 */
public class Solution {

    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        if (len1 + len2 != len3) return false;

        // dp[i][j] -> s1 的前i个字符和 s2 的前j的元素是否能构成 s3 的前 i + j 的元素
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                // 可以把各种if简化为只剩最后一个else里的语句，但是这样更清晰，可读性更好
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && (s2.charAt(j - 1) == s3.charAt(j - 1));
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && (s1.charAt(i - 1) == s3.charAt(i - 1));
                } else {
                    // i != 0 && j != 0
                    dp[i][j] = dp[i - 1][j] && (s1.charAt(i - 1) == s3.charAt(i + j - 1))
                            || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[len1][len2];
    }
}
