package com.github.ryan.algorithm.leetcode.medium.medium1291;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> res = new ArrayList<>();

        for (int i = 1; i <= 8; i++) {
            dfs(i, low, high, res);
        }
        Collections.sort(res);
        return res;
    }

    private void dfs(long cur, int low, int high, List<Integer> res) {
        if (low <= cur && cur <= high) {
            res.add((int) cur);
        }

        if (cur > high) {
            return;
        }

        int last = (int) cur % 10;
        if (last == 9) {
            return;
        }

        dfs(cur * 10 + last + 1, low, high, res);
    }
}
