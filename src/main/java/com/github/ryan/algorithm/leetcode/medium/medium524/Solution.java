package com.github.ryan.algorithm.leetcode.medium.medium524;

import java.util.List;

public class Solution {

    public String findLongestWord(String s, List<String> d) {
        String ans = new String();
        for (String word : d) {
            if (word.length() < ans.length()
                    || word.length() > s.length()
                    || (word.length() == ans.length() && ans.compareTo(word) <= 0)) {
                continue;
            }
            if (isMatched(word, s) && isBetterAns(word, ans)) {
                ans = word;
            }
        }
        return ans;
    }

    private boolean isMatched(String word, String s) {
        int startPos = 0;
        for (int i = 0; i < word.length(); i++) {
            int idx = s.indexOf((int) word.charAt(i), startPos);
            if (idx == -1) {
                return false;
            }
            startPos = idx + 1;
        }
        return true;
    }

    private boolean isBetterAns(String word, String ans) {
        if (word.length() > ans.length()
                || (word.length() == ans.length() && word.compareTo(ans)  < 0)) {
            return true;
        } else {
            return false;
        }
    }

}
