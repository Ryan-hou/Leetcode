package com.github.ryan.leetcode.algorithm.hard.hard30;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 08,2019
 */
public class Solution {

    // use slide window and two map
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (words == null || words.length == 0
                || s == null || s.length() == 0) {
            return res;
        }
        int wordLen = words[0].length();
        int wordLenSum = wordLen * words.length;
        if (s.length() < wordLenSum) {
            return res;
        }

        // key -> word in words, value -> word count in words
        Map<String, Integer> wordDict = new HashMap<>(words.length * 2);
        // key -> word seen in s, word seen count in s
        Map<String, Integer> wordSeen = new HashMap<>(words.length * 2);
        for (String word : words) {
            wordDict.put(word, wordDict.getOrDefault(word, 0) + 1);
        }

        // use slide window
        for (int winStart = 0; winStart <= s.length() - wordLenSum; winStart++) {
            int matched = 0;
            wordSeen.clear();
            for (int winEnd = winStart; winEnd + wordLen <= s.length(); winEnd += wordLen) {
                String curWord = s.substring(winEnd, winEnd + wordLen);
                wordSeen.put(curWord, wordSeen.getOrDefault(curWord, 0) + 1);
                if (!wordDict.containsKey(curWord) || wordSeen.get(curWord) > wordDict.get(curWord)) {
                    break;
                }
                matched++;
                if (matched == words.length) {
                    res.add(winStart);
                    break;
                }
            }
        }

        return res;
    }
}
