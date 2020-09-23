package com.github.ryan.algorithm.leetcode.medium.medium1079;

public class Solution {

    private int count = 0;

    public int numTilePossibilities(String tiles) {
        int[] dict = new int[26];
        for (char ch : tiles.toCharArray()) {
            dict[ch - 'A']++;
        }
        calc(dict);
        return count;
    }

    private void calc(int[] dict) {
        for (int i = 0; i < dict.length; i++) {
            if (dict[i] != 0) {
                count++;
                dict[i]--;
                calc(dict);
                dict[i]++;
            }
        }
        // 递归出口
    }
}
