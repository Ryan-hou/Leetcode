package com.github.ryan.algorithm.leetcode.easy.easy171;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 09,2019
 */
public class Solution {

    public int titleToNumber(String s) {
        if (s == null || s.length() == 0) return 0;

        int[] map = new int[26];
        for (int i = 0; i < 26; i++) {
            map[i] = i + 1;
        }

        char[] ches = s.toCharArray();
        int res = 0;
        int radix = 26;
        int digitCnt = 0;
        for (int i = ches.length - 1; i >= 0; i--) {
            char ch = ches[i];
            if (digitCnt == 0) {
                res += map[ch - 'A'];
            } else {
                res += (map[ch - 'A'] * (int) Math.pow(radix, digitCnt));
            }
            digitCnt += 1;
        }
        return res;
    }
}
