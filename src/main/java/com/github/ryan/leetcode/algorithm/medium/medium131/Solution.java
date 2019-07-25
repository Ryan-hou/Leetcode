package com.github.ryan.leetcode.algorithm.medium.medium131;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 25,2019
 */
public class Solution {

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() < 1) return res;

        helper(s, new ArrayList<>(), res, s.length(), 0);
        return res;
    }

    private void helper(String s, List<String> list, List<List<String>> res, int len, int curLen) {
        if ("".equals(s)) {
            if (len == curLen) res.add(new ArrayList<>(list));
            return;
        }

        for (int i = 1; i <= s.length(); i++) {
            String str = s.substring(0, i);
            if (!isPalin(str)) continue;
            curLen += str.length();
            list.add(str);
            helper(s.substring(i), list, res, len, curLen);
            // backtracking
            curLen -= str.length();
            list.remove(list.size() - 1);
        }
    }

    private boolean isPalin(String str) {
        char[] ch = str.toCharArray();
        int l = 0, r = ch.length - 1;
        while (l < r) {
            if (ch[l] != ch[r]) return false;
            l++;
            r--;
        }
        return true;
    }
}
