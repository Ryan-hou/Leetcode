package com.github.ryan.algorithm.leetcode.medium.medium424;

public class Solution {

    public int characterReplacement(String s, int k) {
        int res = 0;
        char[] ches = s.toCharArray();
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            int start = 0, end = 0; // two pointer
            int curLen = 0, replaceNum = 0, maxLen = 0;
            while (end < ches.length) {
                if (ches[end] == ch) {
                    curLen++;
                } else {
                    if (replaceNum < k) {
                        curLen++;
                        replaceNum++;
                    } else {
                        while (ches[start] == ch) {
                            start++; // shrink the window
                            curLen--;
                        }
                        start++;
                    }
                }
                maxLen = Math.max(maxLen, curLen);
                end++; // expand the window
            } // end outer while
            res = Math.max(res, maxLen);
        } // end for
        return res;
    }

}
