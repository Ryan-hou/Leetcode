package com.github.ryan.algorithm.leetcode.medium.medium91;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 05,2019
 */
public class Solution {

    public int numDecodings(String s) {
        // use dp: use two array to record decode ways
        // at ith-index that ith itself as a character or
        // ith and i-1th combine as a character
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }

        int len = s.length();
        int[] dpSingle = new int[len];
        int[] dpNotSingle = new int[len];
        dpSingle[0] = 1;
        dpNotSingle[0] = 0;
        for (int i = 1; i < len; i++) {
            int num = (s.charAt(i - 1) - '0') * 10 + (s.charAt(i) - '0');
            if (10 <= num && num <= 26) {
                // dpSingle[i - 1] -> i-1 th itself as a character
                // now i-1th and ith as a combinie
                dpNotSingle[i] = dpSingle[i - 1];
            }
            if (s.charAt(i) != '0') {
                dpSingle[i] = dpSingle[i - 1] + dpNotSingle[i - 1];
            }
        }
        return dpSingle[len - 1] + dpNotSingle[len - 1];
    }
}
