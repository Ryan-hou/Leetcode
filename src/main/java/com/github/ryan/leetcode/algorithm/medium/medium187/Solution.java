package com.github.ryan.leetcode.algorithm.medium.medium187;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() <= 10) return res;

        Set<String> set = new HashSet<>();
        Set<String> dict = new HashSet<>();
        String str = s.substring(0, 10);
        dict.add(s.substring(0, 10));
        for (int i = 10; i < s.length(); i++) {
            str = str.substring(1) + s.charAt(i);
            if (dict.contains(str)) {
                set.add(str);
            } else {
                dict.add(str);
            }
        }

        return new ArrayList<>(set);
    }
}
