package com.github.ryan.algorithm.leetcode.hard.hard411;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    // result int: convert string to bits, 1 means that we keep the
    // character in target string, 0 means that we abbreviate it
    private int res;

    // min length of the result
    private int minL;

    // diff-number dict
    private Set<Integer> dict;


    public String minAbbreviation(String target, String[] dictionary) {
        dict = new HashSet<>();
        for (String s : dictionary) {
            if (target.length() == s.length()) {
                int mask = getBitMask(target, s);
                if (mask == 0) {
                    return null;
                }
                dict.add(mask);
            }
        }

        if (dict.size() == 0) {
            return String.valueOf(target.length());
        }

        minL = target.length() + 1;
        res = -1;
        // generate all possible abbreviations
        dfs(0, target.length(), 0, 0);

        // construct result string using result int
        StringBuilder sb = new StringBuilder();
        int num = 0;
        for (int i = 0; i < target.length(); i++) {
            if (((res >> (target.length() - i - 1)) & 1) == 1) {
                sb.append(num > 0 ? num : "").append(target.charAt(i));
                num = 0;
            } else {
                num++;
            }
        }

        return sb.append(num > 0 ? num : "").toString();
    }

    // use backtracking to generate all possible abbreviation for the target
    // with 1 means that we will keep the char in result, 0 means that we will
    // abbreviate it.
    // curL is to keep track of the length of current abbreviation
    private void dfs(int cur, int len, int res, int curL) {
        if (cur == len) {
            if (curL < minL) {
                for (int mask : dict) {
                    if ((res & mask) == 0) {
                        // if res & mask != 0, exists at least one
                        // character in res is not same as diff-numer,
                        // thus this is a valid abbreviation
                        return;
                    }
                } // end for
                minL = curL;
                this.res = res;
            }
            return;
        }

        if ((res & 1) == 1 || cur == 0) {
            // start abbreviating
            dfs(cur + 1, len, res << 1, curL + 1);
        } else {
            dfs(cur + 1, len, res << 1, curL);
        }

        // backtracking
        // without abbreviating at cur position
        dfs(cur + 1, len, (res << 1) + 1, curL + 1);
    }


    /**
     * Since only strings with the same length as target would possible have the
     * same abbreviation, we crate a diff-number for those strings in the dictionary,
     * with 0 represents same character, 1 represents different character.
     * For example: abc and bbc's diff-numer is 100
     */
    private int getBitMask(String s, String t) {
        int mask = 0;
        for (int i = 0; i < s.length(); i++) {
            mask <<= 1;
            if (s.charAt(i) != t.charAt(i)) {
                mask += 1;
            }
        }
        return mask;
    }

}
