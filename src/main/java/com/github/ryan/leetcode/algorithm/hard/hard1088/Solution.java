package com.github.ryan.leetcode.algorithm.hard.hard1088;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    private Map<Integer, Integer> dict;
    private int limit;

    public int confusingNumberII(int N) {
        dict = new HashMap<Integer, Integer>() {
            {
                put(0, 0);
                put(1, 1);
                put(6, 9);
                put(8, 8);
                put(9, 6);
            }
        };
        limit = N;
        return dfs(0, 0, 1);
    }

    private int dfs(long cur, long reverse, long base) {
        if (cur > limit) return 0;
        int res = (cur == reverse ? 0 : 1);
        for (int n : dict.keySet()) {
            long num = cur * 10 + n;
            if (num == 0) continue;
            res += dfs(num, reverse + dict.get(n) * base, base * 10);
        }
        return res;
    }

}
