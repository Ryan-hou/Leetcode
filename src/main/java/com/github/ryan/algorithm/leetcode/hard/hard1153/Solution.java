package com.github.ryan.algorithm.leetcode.hard.hard1153;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Solution {

    // If at least one character not exists in str1 and
    // str1 and str2 have same pattern then str1 can convert to str2
    public boolean canConvert(String str1, String str2) {
        if (str1.equals(str2)) return true;

        // key -> char in str1, value -> char in str2
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < str1.length(); i++) {
            if (map.containsKey(str1.charAt(i))) {
                if (map.get(str1.charAt(i)) != str2.charAt(i)) {
                    return false;
                }
            } else {
                map.put(str1.charAt(i), str2.charAt(i));
            }
        }

        if (new HashSet<>(map.values()).size() == 26) {
            return false;
        } else {
            return true;
        }
    }

}
