package com.github.ryan.algorithm.leetcode.medium.medium267;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public List<String> generatePalindromes(String s) {
        if (s == null || s.length() < 1) {
            return new ArrayList<>();
        }

        Map<Character, Integer> dict = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            dict.put(ch, dict.getOrDefault(ch, 0) + 1);
        }
        if (!hasPalindrome(dict)) {
            return new ArrayList<>();
        }


        Character ch = null;
        for (Map.Entry<Character, Integer> entry : dict.entrySet()) {
            if (entry.getValue() % 2 == 1) {
                ch = entry.getKey();
            }
            dict.put(entry.getKey(), entry.getValue() / 2);
        }

        List<String> res = new ArrayList<>();
        // process half of s
        permutation(0, s.length() / 2, new StringBuilder(), dict, res, ch);
        return res;
    }

    private void permutation(int s, int len
            , StringBuilder b
            , Map<Character, Integer> dict
            , List<String> res
            , Character ch) {
        if (s == len) {
            String str = b.toString();
            res.add(b.toString() + (ch == null ? "" : ch) + new StringBuilder(str).reverse());
            return;
        }

        for (Map.Entry<Character, Integer> entry : dict.entrySet()) {
            if (entry.getValue() > 0) {
                b.append(entry.getKey());
                dict.put(entry.getKey(), entry.getValue() - 1);
                permutation(s + 1, len, b, dict, res, ch);
                // backtracking
                b.deleteCharAt(b.toString().length() - 1);
                dict.put(entry.getKey(), entry.getValue() + 1);
            }
        }
    }


    private boolean hasPalindrome(Map<Character, Integer> dict) {
        int oddNum = 0;
        for (Integer count : dict.values()) {
            if (count % 2 != 0) {
                oddNum++;
                if (oddNum > 1) {
                    return false;
                }
            }
        }
        return true;
    }

}
