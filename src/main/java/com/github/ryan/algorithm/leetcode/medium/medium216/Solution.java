package com.github.ryan.algorithm.leetcode.medium.medium216;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n > 45) return res; // sum(1...9) -> 45
        helper(res, new ArrayList<Integer>(), k, n, 1);
        return res;
    }

    // backtracking
    private void helper(List<List<Integer>> res, List<Integer> one
            , int k, int target, int start) {
        if (one.size() == k && target == 0) {
            res.add(new ArrayList<>(one));
            return;
        }

        for (int i = start; i <= 9; i++) {
            if (target < i) return;
            one.add(i);
            helper(res, one, k, target - i, i + 1);
            one.remove(one.size() - 1);
        }
    }

}
