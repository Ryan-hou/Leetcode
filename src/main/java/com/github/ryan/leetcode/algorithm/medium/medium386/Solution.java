package com.github.ryan.leetcode.algorithm.medium.medium386;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    // DFS
    public List<Integer> lexicalOrder(int n) {
        if (n < 1) return new ArrayList<>();
        List<Integer> res = new ArrayList<>(n);
        for (int i = 1; i <= Math.min(9, n); i++) {
            res.add(i);
            lexicalOrderDfs(res, i * 10, n);
        }
        return res;
    }

    private void lexicalOrderDfs(List<Integer> res, int cur, int n) {
        for (int i = cur; i < cur + 10 && i <= n; i++) {
            res.add(i);
            lexicalOrderDfs(res, i * 10, n);
        }
    }
}
