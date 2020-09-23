package com.github.ryan.algorithm.leetcode.medium.medium1048;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int longestStrChain(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        // key -> word, value -> chain's length to this word
        Map<String, Integer> map = new HashMap<>();
        int res = 0;
        for (String w : words) {
            map.put(w, 1);
            for (int i = 0; i < w.length(); i++) {
                String prev = new StringBuilder(w).deleteCharAt(i).toString();
                if (map.containsKey(prev) && map.get(prev) + 1 > map.get(w)) {
                    map.put(w, map.get(prev) + 1);
                }
            }
            res = Math.max(res, map.get(w));
        }
        return res;
    }

}
