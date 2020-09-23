package com.github.ryan.algorithm.leetcode.medium.medium763;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        if (S == null || S.length() < 1) return res;

        int[] map = new int[26];
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            map[ch - 'a'] = i;
        }

        int start = 0;
        int end = 0;
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            end = Math.max(end, map[ch - 'a']);
            if (end <= i) {
                res.add(end - start + 1);
                start = i + 1;
            }
        }
        return res;
    }
}
