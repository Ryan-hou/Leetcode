package com.github.ryan.leetcode.algorithm.medium.medium809;

public class Solution {

    public int expressiveWords(String S, String[] words) {
        int count = 0;
        for (String w : words) {
            if (isValid(S, w)) {
                count++;
            }
        }
        return count;
    }

    private boolean isValid(String s, String w) {
        int i = 0, j = 0;
        int m = s.length(), n = w.length();
        while (i < m && j < n) {
            if (s.charAt(i) != w.charAt(j)) return false;
            int sEnd = getEnd(s, i);
            int wEnd = getEnd(w, j);
            if (sEnd - i < 3 && sEnd - i != wEnd - j) return false;
            // sEnd - i >= 3
            if (wEnd > sEnd) return false;
            i = sEnd;
            j = wEnd;
        }
        return i == m && j == n;
    }

    // end of char s.charAt(i) in s, exclusive
    private int getEnd(String s, int i) {
        char ch = s.charAt(i);
        while (i < s.length() && s.charAt(i) == ch) {
            i++;
        }
        return i;
    }

}
