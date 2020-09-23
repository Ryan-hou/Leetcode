package com.github.ryan.algorithm.leetcode.hard.hard828;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public int uniqueLetterString(String s) {
        if (s == null || s.length() < 1) return 0;

        // key -> character(A-Z), value -> character's all indexes in String s
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.computeIfAbsent(ch, x -> new ArrayList<>()).add(i);
        }
        // be careful about edge cases
        long res = 0;
        for (List<Integer> indexes : map.values()) {
            for (int i = 0; i < indexes.size(); i++) {
                long prev = i > 0 ? indexes.get(i - 1) : -1;
                long next = i < indexes.size() - 1 ? indexes.get(i + 1) : s.length();
                int curIdx = indexes.get(i);
                res += (curIdx - prev) * (next - curIdx);
            }
        }
        return (int) (res % 1_000_000_007);
    }

}
