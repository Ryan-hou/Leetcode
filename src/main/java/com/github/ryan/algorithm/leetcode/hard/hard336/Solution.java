package com.github.ryan.algorithm.leetcode.hard.hard336;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if (words == null || words.length < 1) return res;
        // key -> word, value -> word's index
        Map<String, Integer> dict = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            dict.put(words[i], i);
        }
        // palindrome -> A + palindrome string + reverse A
        for (int idx = 0; idx < words.length; idx++) {
            String w = words[idx];
            for (int i = 0; i <= w.length(); i++) {
                String prefix = w.substring(0, i);
                String suffix = w.substring(i);
                if (isPalindrome(prefix)) {
                    String reverse = new StringBuilder(suffix).reverse().toString();
                    if (dict.containsKey(reverse) && dict.get(reverse) != idx) {
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(dict.get(reverse));
                        tmp.add(idx);
                        res.add(tmp);
                    }
                }

                if (isPalindrome(suffix) && suffix.length() > 0) {
                    String reverse = new StringBuilder(prefix).reverse().toString();
                    if (dict.containsKey(reverse) && dict.get(reverse) != idx) {
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(idx);
                        tmp.add(dict.get(reverse));
                        res.add(tmp);
                    }
                }
            }
        }
        return res;
    }

    private boolean isPalindrome(String str) {
        int start = 0;
        int end = str.length() - 1;
        while (start < end) {
            if (str.charAt(start) != str.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

}
