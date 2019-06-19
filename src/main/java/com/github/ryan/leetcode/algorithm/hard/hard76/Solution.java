package com.github.ryan.leetcode.algorithm.hard.hard76;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 19,2019
 */
public class Solution {

    // two pointer
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";

        int[] map = new int[128];
        for (char ch : t.toCharArray()) {
            map[ch]++;
        }

        int l = 0, r = 0;
        int i = 0, j = 0; // return s[i,j]
        int n = t.length();
        char[] sArr = s.toCharArray();
        while (r < s.length()) {
            // move right pointer until contain all the characters
            if (map[sArr[r]] > 0) {
                n--;
            }
            map[sArr[r]]--;
            r++;

            // move left pointer
            while (n == 0) {
                if (j == 0 || j - i > r - l) {
                    j = r;
                    i = l;
                }

                map[sArr[l]]++;
                if (map[sArr[l]] > 0) {
                    n++;
                }
                l++;
            }
        }
        return s.substring(i, j);
    }

}
