package com.github.ryan.algorithm.leetcode.medium.medium646;

import java.util.Arrays;

public class Solution {

    public int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length < 1) return 0;
        Arrays.sort(pairs, (p1, p2) -> p1[1] - p2[1]);
        int res = 1;
        int tail = pairs[0][1];
        for (int i = 1; i < pairs.length; i++) {
            if (pairs[i][0] > tail) {
                res++;
                tail = pairs[i][1];
            }
        }
        return res;
    }

}
