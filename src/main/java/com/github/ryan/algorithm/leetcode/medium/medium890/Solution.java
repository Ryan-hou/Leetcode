package com.github.ryan.algorithm.leetcode.medium.medium890;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> res = new ArrayList<>();
        char[] p = pattern.toCharArray();
        for (String str : words) {
            if (match(str.toCharArray(), p)) {
                res.add(str);
            }
        }
        return res;
    }

    private boolean match(char[] src, char[] p) {

        int[] map = new int[26];
        Arrays.fill(map, -1);
        boolean[] used = new boolean[26];

        for (int i = 0; i < src.length; i++) {
            int srcIdx = src[i] - 'a';
            int pIdx = p[i] - 'a';
            if (map[srcIdx] == -1) {
                // char[srcIdx] -> p[pIdx]
                map[srcIdx] = pIdx;
            } else if (map[srcIdx] != pIdx) {
                return false;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (map[i] == -1) continue;
            if (used[map[i]]) {
                return false;
            } else {
                used[map[i]] = true;
            }
        }
        return true;
    }
}
