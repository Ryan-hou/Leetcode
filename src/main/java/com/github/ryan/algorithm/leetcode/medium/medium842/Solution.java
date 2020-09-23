package com.github.ryan.algorithm.leetcode.medium.medium842;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> res = new ArrayList<>();
        split(res, 0, S);
        return res;
    }

    // use backtracking
    // can s[pos, s.length()) split into Fibonacci-like sequence
    // return true -> yes
    private boolean split(List<Integer> res, int pos, String s) {
        if (pos >= s.length()) {
            return res.size() >= 3;
        }

        long num = 0;
        for (int i = pos; i < s.length(); i++) {
            num = num * 10 + (s.charAt(i) - '0');
            // quick return -> improve the speed vastly
            if (num > Integer.MAX_VALUE) return false;
            if (validNum(num, pos, i, res, s)) {
                res.add((int) num);
                if (split(res, i + 1, s)) {
                    return true;
                }
                // backtracking
                res.remove(res.size() - 1);
            }
        }
        return false;
    }

    private boolean validNum(long num, int start, int idx, List<Integer> res, String s) {
        if (start != idx && s.charAt(start) == '0') return false; // leading zero
        // if (num > Integer.MAX_VALUE) return false;
        if (res.size() <= 1) return true;
        int last = res.size() - 1;
        return res.get(last - 1) + res.get(last) == (int) num;
    }
}
