package com.github.ryan.algorithm.leetcode.medium.medium294;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    /**
     * 时间复杂度 O(n!!)
     * let's say the length of the input string s is n, there are at most n - 1
     * ways to replace "++" to "--" (imagine s is all "+++..."), once we replace one "++",
     * there are at most (n-2) - 1 ways to do the replacement,
     * it's a little bit like solving the N-Queens problem,
     * the time complexity is (n - 1) x (n - 3) x (n - 5) x ...,
     * so it's O(n!!), double factorial.
     * 双阶乘是一个数学概念，用n!!表示
     * 正整数的双阶乘表示不超过这个正整数且与它有相同奇偶性的所有正整数乘积
     *
     * @param s
     * @return
     */
    public boolean canWin(String s) {
        if (s == null) {
            return false;
        }
        return canWinHelper(s.toCharArray());
    }

    private boolean canWinHelper(char[] chs) {

        for (int i = 0; i < chs.length - 1; i++) {
            if (chs[i] == '+' && chs[i + 1] == '+') {
                chs[i] = '-';
                chs[i + 1] = '-';

                boolean win = !canWinHelper(chs);

                // backtracking, before return
                chs[i] = '+';
                chs[i + 1] = '+';

                if (win) {
                    return true;
                }
            }
        }
        return false;
    }


    // ======== 回溯算法优化 -> 记忆化搜索 =========
    // todo: 时间复杂度？ O(n^2)
    public boolean canWin2(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }

        // key -> current str, value -> can win with this str or not
        Map<String, Boolean> dict = new HashMap<>();
        return canWinHelper(s, dict);
    }

    private boolean canWinHelper(String s, Map<String, Boolean> dict) {
        if (dict.containsKey(s)) {
            return dict.get(s);
        }

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                String opponent = s.substring(0, i) + "--" + s.substring(i + 2);
                boolean canWin = canWinHelper(opponent, dict);
                if (!canWin) {
                    dict.put(s, true);
                    return true;
                }
            }
        }
        dict.put(s, false);
        return false;
    }

}
