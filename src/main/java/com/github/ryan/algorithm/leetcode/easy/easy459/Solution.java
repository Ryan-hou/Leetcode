package com.github.ryan.algorithm.leetcode.easy.easy459;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date January 02,2019
 */
public class Solution {

    // brute force: O(n^2)
    public boolean repeatedSubstringPattern(String s) {
        // corner case
        if (s == null || s.length() == 0) {
            return true;
        }

        int n = s.length();
        for (int len = 1; len <= n/2; len++) {
            // s must repeat at least twice
            if (n % len != 0) continue;

            String pattern = s.substring(0, len);
            int i = len; // start index of 2nd pattern
            int j = i + len; // end index of 2nd pattern + 1
            while (j <= s.length()) {
                String substr = s.substring(i, j);
                if (!pattern.equals(substr)) break;
                i += len;
                j += len;
            }
            if (j == s.length() + len) return true;
        }
        return false;
    }

    // Too cleaver !! don't know how to prove!
    public boolean repeatedSubstringPattern2(String s) {
        return (s + s).substring(1, s.length() * 2 - 1).contains(s);
    }

}
