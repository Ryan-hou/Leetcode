package com.github.ryan.algorithm.leetcode.easy.easy290;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        char[] charArray = pattern.toCharArray();

        if (pattern.length() != words.length) {
            return false;
        }

        Map<Character, String> patternMap = new HashMap<>();
        Set<String> valuesSet = new HashSet<>();

        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            String word = words[i];
            String mappedWord = patternMap.get(c);
            if (mappedWord == null) {
                patternMap.put(c, word);
                valuesSet.add(word);
            } else if (!mappedWord.equals(word)) {
                return false;
            }
        }
        return (patternMap.size() == valuesSet.size());
    }

}
