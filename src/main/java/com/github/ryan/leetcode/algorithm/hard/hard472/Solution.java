package com.github.ryan.leetcode.algorithm.hard.hard472;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        if (words == null || words.length < 3) return new ArrayList<>();

        Set<String> dict = new HashSet<>(words.length * 2);
        for (String w : words) {
            dict.add(w);
        }
        List<String> res = new ArrayList<>();
        for (String w : words) {
            if (isContain(w, dict)) {
                res.add(w);
            }
        }
        return res;
    }

    // DFS
    private boolean isContain(String word, Set<String> dict) {
        for (int i = 1; i < word.length(); i++) {
            String prefix = word.substring(0, i);
            String suffix = word.substring(i);
            if (dict.contains(prefix)
                    && (dict.contains(suffix) || isContain(suffix, dict))) {
                return true;
            }
        }
        return false;
    }

}
