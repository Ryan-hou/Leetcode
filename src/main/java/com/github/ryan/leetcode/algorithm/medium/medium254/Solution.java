package com.github.ryan.leetcode.algorithm.medium.medium254;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    // DFS
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        dfs(n, 2, cur, res);
        return res;
    }

    private void dfs(int n, int start, List<Integer> cur, List<List<Integer>> res) {
        if (cur.size() > 0) {
            List<Integer> tmp = new ArrayList<>(cur);
            tmp.add(n);
            res.add(tmp);
        }

        for (int i = start; i * i <= n; i++) {
            if (n % i == 0) {
                cur.add(i);
                dfs(n / i, i, cur, res);
                cur.remove(cur.size() - 1);
            }
        }
    }

}
