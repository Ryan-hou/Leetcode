package com.github.ryan.algorithm.leetcode.medium.medium647;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date April 08,2019
 */
public class Solution {


    // brute force
    public int countSubstrings(String s) {
        if (s == null || s.length() <= 0) return 0;

        int count = 0;
        int len = s.length();
        char[] chs = s.toCharArray();
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (isPalindrome(i, j, chs)) count++;
            }
        }
        return count;
    }

    private boolean isPalindrome(int begin, int end, char[] chs) {
        while (begin < end && begin < chs.length && end >= 0) {
            if (chs[begin] != chs[end]) return false;
            begin++;
            end--;
        }
        return true;
    }

    // Use dp
    // This solution is almost same as the DP solution for longest palindromic substring,
    // instead of storing the longest, just get the count of palindromic substrings.
    public int countSubstrings2(String s) {
        if (s == null) return 0;
        int n = s.length();
        int count = 0;
        boolean[][] dp = new boolean[n][n]; // boolean[i][j] -> [i...j] is palindrome or not
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
                if (dp[i][j]) count++;
            }
        }
        return count;
    }

    // Idea is start from each index and try to extend palindrome
    // for both odd and even length.
    private int count;
    public int countSubstrings3(String s) {
        if (s == null || s.length() == 0) return 0;

        for (int i = 0; i < s.length(); i++) { // i is mid post
            extendPalindrome(s, i, i); // odd length
            extendPalindrome(s, i, i + 1); // even length
        }
        return count;
    }

    private void extendPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
            count++;
        }
    }
}
