package com.github.ryan.algorithm.leetcode.medium.medium395;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 15,2019
 */
public class Solution {

    public int longestSubstring(String s, int k) {
        if (s == null || s.length() < k) return 0;

        int[] map = new int[26];
        for (char ch : s.toCharArray()) {
            map[ch - 'a']++;
        }

        int max = 0;
        int start = -1;
        for (int i = 0; i < s.length(); i++) {
            int n = map[s.charAt(i) - 'a'];
            // set start index
            if (n >= k && start < 0) {
                start = i;
            }
            // set end index
            if (n < k && start >= 0) {
                max = Math.max(max, longestSubstring(s.substring(start, i), k));
                start = -1;
            }
        }

        if (start == 0) {
            return s.length();
        } else if (start > 0) {
            return Math.max(max, longestSubstring(s.substring(start), k));
        } else {
            return max;
        }
    }
}
