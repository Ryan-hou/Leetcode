package com.github.ryan.leetcode.algorithm.medium.medium5;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date February 18,2019
 */
public class Solution {

    // 时间复杂度O(n^2), use DP
    public String longestPalindrome(String s) {

        if (s == null || s.length() <= 1) return s;

        // use dp: P(i, j) = (P(i + 1, j - 1) and Si == Sj)
        // Base case: P(i,i) = true, p(i, i + 1) = (Si == Si+1)
        int len = s.length();
        int start = 0, end = 0;
        boolean[][] dp = new boolean[len][len];
        // dp[i][j]: s[i...j]是否是 palindrome
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)
                        && (j - i < 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    if (j - i > end - start) {
                        start = i;
                        end = j;
                    }
                }
            }
        }
        return s.substring(start, end + 1);
    }

    // 使用 palindrome 的性质，关于中心镜面对称
    // 时间复杂度 O(n^2),空间复杂度O(1)
    private int maxLen;
    private int lo;

    //  Expand Around Center
    //  We observe that a palindrome mirrors around its center.
    // Therefore, a palindrome can be expanded from its center, and there are only 2n - 1 such centers
    public String longestPalindrome2(String s) {

        if (s == null || s.length() <= 1) return s;

        int len = s.length();
        for (int i = 0; i < len - 1; i++) {
            extendPalindrome(s, i, i); // assume odd length, try to extend Palindrome as possible
            extendPalindrome(s, i, i + 1); // assume even length
        }
        return s.substring(lo, lo + maxLen);
    }

    private void extendPalindrome(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }

        if (maxLen < j - i - 1) {
            maxLen = j - i - 1;
            lo = i + 1;
        }
    }
}
