package com.github.ryan.algorithm.leetcode.medium.medium838;

public class Solution {

    public String pushDominoes(String dominoes) {
        if (dominoes == null || dominoes.length() < 2) {
            return dominoes;
        }

        char[] ches = dominoes.toCharArray();
        int left = 0, right = 0;
        while (right < ches.length) {
            if (ches[right] == 'L' || ches[right] == 'R'
                    || right == ches.length - 1) {
                pushDominoes(ches, left, right);
                left = right;
            }
            right++;
        }
        return new String(ches);
    }

    private void pushDominoes(char[] ches, int left, int right) {
        boolean pushRight = ches[left] == 'R';
        boolean pushLeft = ches[right] == 'L';
        if (pushRight && pushLeft) {
            for (int i = 0; i < (right - left + 1) / 2; i++) {
                ches[left + i] = 'R';
                ches[right - i] = 'L';
            }
        } else if (pushRight || pushLeft) {
            for (int i = left; i <= right; i++) {
                ches[i] = pushLeft ? 'L' : 'R';
            }
        } // else do nothing
    }

}
