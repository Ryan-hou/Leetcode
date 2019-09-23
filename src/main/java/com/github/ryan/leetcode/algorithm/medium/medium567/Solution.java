package com.github.ryan.leetcode.algorithm.medium.medium567;

public class Solution {

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        int[] map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            map[s1.charAt(i) - 'a']++;
            map[s2.charAt(i) - 'a']--;
        }

        if (allZero(map)) return true;
        int start = s1.length();
        for (int i = start; i < s2.length(); i++) {
            map[s2.charAt(i) - 'a']--;
            map[s2.charAt(i - start) - 'a']++;
            if (allZero(map)) return true;
        }
        return false;
    }

    private boolean allZero(int[] arr) {
        for (int n : arr) {
            if (n != 0) return false;
        }
        return true;
    }

}
