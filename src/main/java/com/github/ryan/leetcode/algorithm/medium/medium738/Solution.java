package com.github.ryan.leetcode.algorithm.medium.medium738;

public class Solution {

    public int monotoneIncreasingDigits(int N) {
        char[] ches = String.valueOf(N).toCharArray();
        // find the cliff: the first index that ches[i - 1] > ches[i]
        int i = 1;
        while (i < ches.length && ches[i - 1] <= ches[i]) {
            i++;
        }
        // move backward for ensuring that ches[i - 1] <= ches[i]
        while (i > 0 && i < ches.length
                && ches[i - 1] > ches[i]) {
            ches[--i]--;
        }
        for (int j = i + 1; j < ches.length; j++) {
            ches[j] = '9';
        }
        return Integer.parseInt(String.valueOf(ches));
    }
}
