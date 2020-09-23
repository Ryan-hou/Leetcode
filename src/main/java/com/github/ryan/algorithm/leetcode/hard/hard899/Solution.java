package com.github.ryan.algorithm.leetcode.hard.hard899;

import java.util.Arrays;

public class Solution {

    public String orderlyQueue(String S, int K) {
        if (K > 1) {
            char[] ches = S.toCharArray();
            Arrays.sort(ches);
            return new String(ches);
        }

        String res = S;
        for (int i = 1; i < S.length(); i++) {
            String tmp = S.substring(i) + S.substring(0, i);
            if (tmp.compareTo(res) < 0) {
                res = tmp;
            }
        }
        return res;
    }
}
