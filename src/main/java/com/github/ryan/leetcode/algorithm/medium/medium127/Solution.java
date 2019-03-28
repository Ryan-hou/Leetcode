package com.github.ryan.leetcode.algorithm.medium.medium127;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date March 28,2019
 */
public class Solution {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // BSF 类似查找最短路径
        Set<String> reached = new HashSet<>(wordList.size());
        reached.add(beginWord);
        Set<String> wordDict = new HashSet<>(wordList);

        int distance = 1;
        while (!reached.contains(endWord)) {

            Set<String> toAdd = new HashSet<>();
            for (String str : reached) {
                for (int i = 0; i < str.length(); i++) {
                    char[] chars = str.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chars[i] = ch;
                        String newStr = new String(chars);
                        if (wordDict.contains(newStr)) {
                            toAdd.add(newStr);
                            wordDict.remove(newStr);
                        }
                    }
                }
            }

            if (toAdd.isEmpty()) { return 0; }
            reached = toAdd;
            distance++;
        }
        return distance;
    }
}
