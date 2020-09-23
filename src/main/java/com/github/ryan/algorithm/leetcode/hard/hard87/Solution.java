package com.github.ryan.algorithm.leetcode.hard.hard87;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 05,2019
 */
public class Solution {

    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) return true;
        if (s1.length() != s2.length()) return false;

        int[] s1Dict = new int[26];
        int[] s2Dict = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1Dict[s1.charAt(i) - 'a']++;
            s2Dict[s2.charAt(i) - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (s1Dict[i] != s2Dict[i]) {
                return false;
            }
        }

        for (int i = 1; i < s1.length(); i++) {
            if ((isScramble(s1.substring(0, i), s2.substring(0, i))
                    && isScramble(s1.substring(i), s2.substring(i)))
                    || (isScramble(s1.substring(0, i), s2.substring(s2.length() - i))
                    && isScramble(s1.substring(i), s2.substring(0, s2.length() - i)))) {
                return true;
            }
        }
        return false;
    }
}
