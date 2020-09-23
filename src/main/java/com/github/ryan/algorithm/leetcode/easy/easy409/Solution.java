package com.github.ryan.algorithm.leetcode.easy.easy409;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int longestPalindrome(String s) {
        if (s == null || s.length() < 1) return 0;
        // key -> character, value -> character's count
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int res = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            res += (entry.getValue() / 2);
        }
        return res * 2 + (res * 2 < s.length() ? 1 : 0);
    }

}
