package com.github.ryan.leetcode.algorithm.easy.easy242;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date December 02,2018
 */
public class Solution {

    public boolean isAnagram(String s, String t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        // s != null && t != null
        if (s.length() != t.length()) return false;

        int[] charFreq = new int[26];
        char[] sArr = s.toCharArray();
        for (char c : sArr) {
            charFreq[c - 'a']++;
        }
        char[] tArr = t.toCharArray();
        for (char c : tArr) {
            charFreq[c - 'a']--;
        }

        for (int n : charFreq) {
            if (n != 0) return false;
        }
        return true;
    }
}
