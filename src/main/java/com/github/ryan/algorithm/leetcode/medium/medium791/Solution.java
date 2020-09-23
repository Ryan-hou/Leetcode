package com.github.ryan.algorithm.leetcode.medium.medium791;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public String customSortString(String S, String T) {
        if (S == null || S.length() < 1) return T;
        if (T == null || T.length() < 1) return T;
        // key: character in T, value: number of this character
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : T.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        StringBuilder b = new StringBuilder();
        for (char ch : S.toCharArray()) {
            if (map.containsKey(ch)) {
                appendNChar(b, map.get(ch), ch);
                map.remove(ch);
            }
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            appendNChar(b, entry.getValue(), entry.getKey());
        }
        return b.toString();
    }

    private void appendNChar(StringBuilder b, int n, char ch) {
        for (int i = 0; i < n; i++) {
            b.append(ch);
        }
    }

}
