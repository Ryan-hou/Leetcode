package com.github.ryan.algorithm.leetcode.easy.easy205;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null) return s == t;
        if (s.length() == 0 || t.length() == 0) return s.length() == t.length();
        if (s.length() != t.length()) return false;
        // key -> char in t, value -> isomorphic char of t in s
        Map<Character, Character> map = new HashMap<>();
        // used char in s
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char tchar = t.charAt(i);
            char schar = s.charAt(i);
            if (!map.containsKey(tchar)) {
                if (set.contains(schar)) {
                    return false;
                } else {
                    map.put(tchar, schar);
                    set.add(schar);
                }
            } else if (map.get(tchar) != schar) {
                return false;
            }
        }
        return true;
    }

}
