package com.github.ryan.leetcode.algorithm.medium.medium249;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    // The basic idea is to compute a hash value for every string
    // by calculating the relative offset of every char from the starting character
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        if (strings == null || strings.length < 1) return res;

        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            map.computeIfAbsent(hash(str), x -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }

    private String hash(String str) {
        StringBuilder b = new StringBuilder();
        int offset = str.charAt(0) - 'a';
        for (int i = 0; i < str.length(); i++) {
            int pos = str.charAt(i) - 'a' - offset;
            char ch = pos < 0 ? (char) (pos + 26) : (char) pos;
            b.append(ch);
        }
        return b.toString();
    }

}
