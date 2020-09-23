package com.github.ryan.algorithm.leetcode.easy.easy345;

public class Solution {

    public String reverseVowels(String s) {
        if (s == null) return null;

        int l = 0, r = s.length() - 1;
        char[] ches = s.toCharArray();
        while (l < r) {
            if (isVowel(ches[l]) && isVowel(ches[r])) {
                char tmp = ches[l];
                ches[l] = ches[r];
                ches[r] = tmp;
                r--;
                l++;
            } else if (isVowel(ches[l])) {
                r--;
            } else if (isVowel(ches[r])) {
                l++;
            } else {
                // ches[l] and ches[r] are all not vowels
                r--;
                l++;
            }
        }
        return new String(ches);
    }

    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'
                || ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U';
    }

}
