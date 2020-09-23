package com.github.ryan.algorithm.leetcode.hard.hard727;

import java.util.Arrays;

public class Solution {

    private int[][] memo;
    private String res;

    public String minWindow(String S, String T) {
        if (S.length() < T.length()) return "";

        // memo[i][j] -> last index of S when S and T start from i and j
        memo = new int[S.length()][T.length()];
        res = "";
        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }
        helper(S, T, 0, 0);
        return res;
    }

    private int helper(String s, String t, int sIdx, int tIdx) {
        if (tIdx == t.length()) {
            return sIdx;
        }
        if (sIdx == s.length()) {
            return sIdx + 1;
        }
        if (memo[sIdx][tIdx] != -1) {
            return memo[sIdx][tIdx];
        }


        int lastIdx = s.length() + 1;
        if (s.charAt(sIdx) == t.charAt(tIdx)) {
            lastIdx = helper(s, t, sIdx + 1, tIdx + 1);
        }
        lastIdx = Math.min(lastIdx, helper(s, t, sIdx + 1, tIdx));
        memo[sIdx][tIdx] = lastIdx;
        // process res
        if (tIdx == 0 && lastIdx < s.length() + 1) {
            // use >= cause we need to return the left-most starting index
            if (res.length() == 0 || res.length() >= lastIdx - sIdx) {
                res = s.substring(sIdx, lastIdx);
            }
        }

        return lastIdx;
    }

}
