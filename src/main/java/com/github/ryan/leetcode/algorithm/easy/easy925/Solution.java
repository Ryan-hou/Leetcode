package com.github.ryan.leetcode.algorithm.easy.easy925;

public class Solution {

    public boolean isLongPressedName(String name, String typed) {
        int idx = 0;
        for (char c : name.toCharArray()) {
            if (idx >= typed.length()) return false;

            // if mismatch
            if (typed.charAt(idx) != c) {
                // if it's the first char of the block, return false
                if (idx == 0 || typed.charAt(idx - 1) != typed.charAt(idx)) {
                    return false;
                }

                // discard all similar chars
                char cur = typed.charAt(idx);
                while (idx < typed.length() && typed.charAt(idx) == cur) {
                    idx++;
                }

                // if next it's a match, return false
                if (idx >= typed.length() || typed.charAt(idx) != c) {
                    return false;
                }
            }

            idx++;
        } // end for
        return true;
    }

}
