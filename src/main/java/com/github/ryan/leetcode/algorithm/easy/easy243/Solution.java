package com.github.ryan.leetcode.algorithm.easy.easy243;

public class Solution {

    public int shortestDistance(String[] words, String word1, String word2) {
        // idx1 and idx2 store most recent locations of word1 and word2
        int idx1 = -1, idx2 = -1;
        int minDis = words.length;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                idx1 = i;
            } else if (words[i].equals(word2)) {
                idx2 = i;
            }

            if (idx1 != -1 && idx2 != -1) {
                minDis = Math.min(minDis, Math.abs(idx1 - idx2));
            }
        }
        return minDis;
    }

}
