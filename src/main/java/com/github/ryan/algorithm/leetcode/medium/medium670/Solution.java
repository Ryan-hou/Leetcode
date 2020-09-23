package com.github.ryan.algorithm.leetcode.medium.medium670;

public class Solution {

    public int maximumSwap(int num) {
        char[] chs = Integer.toString(num).toCharArray();
        // idx -> digit, dict[idx] -> last index of this digit in chs
        int[] dict = new int[10]; // digit 0 ~ 9
        for (int i = 0; i < chs.length; i++) {
            dict[chs[i] - '0'] = i;
        }

        for (int i = 0; i < chs.length; i++) {
            for (int d = 9; d > chs[i] - '0'; d--) {
                if (dict[d] > i) {
                    char tmp = chs[i];
                    chs[i] = chs[dict[d]];
                    chs[dict[d]] = tmp;
                    return Integer.valueOf(new String(chs));
                }
            }
        }
        return  num;
    }

}
