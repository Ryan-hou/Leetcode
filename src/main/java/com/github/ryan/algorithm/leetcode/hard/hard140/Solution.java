package com.github.ryan.algorithm.leetcode.hard.hard140;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 26,2019
 */
public class Solution {

    public List<String> wordBreak(String s, List<String> wordDict) {
        if (wordDict == null || wordDict.size() < 1) return new ArrayList<>();

        Set<String> dict = new HashSet<>(wordDict);
        // key -> sentence, value -> words that construct this sentence
        Map<String, List<String>> sentenceByWords = new HashMap<>();
        return helper(s, dict, sentenceByWords);
    }

    private List<String> helper(String s, Set<String> dict, Map<String, List<String>> sentenceByWords) {
        if (sentenceByWords.containsKey(s)) {
            return sentenceByWords.get(s);
        }

        List<String> res = new ArrayList<>();
        if (dict.contains(s)) {
            res.add(s);
        }

        for (int i = 0; i < s.length(); i++) {
            String prev = s.substring(0, i);
            String next = s.substring(i);
            if (dict.contains(prev)) {
                List<String> words = helper(next, dict, sentenceByWords);
                sentenceByWords.put(next, words);
                constructRes(prev, words, res);
            }
        }
        return res;
    }

    private void constructRes(String prev, List<String> nextWords, List<String> res) {
        if (nextWords == null || nextWords.size() < 1) return;

        for (String str : nextWords) {
            StringBuilder b = new StringBuilder();
            b.append(prev + " " + str);
            res.add(b.toString());
        }
    }
}
