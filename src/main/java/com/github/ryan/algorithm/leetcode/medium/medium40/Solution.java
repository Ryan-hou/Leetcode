package com.github.ryan.algorithm.leetcode.medium.medium40;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 06,2019
 */
public class Solution {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length < 1) return res;
        Arrays.sort(candidates);
        combination(res, new ArrayList<>(), candidates, 0, 0, target);
        return res;
    }

    private void combination(List<List<Integer>> res, List<Integer> combination,
                             int[] candidates, int start, int sum, int target) {
        if (sum == target) {
            res.add(new ArrayList<>(combination));
        } else if (sum < target) {
            for (int i = start; i < candidates.length; i++) {
                if (i > start && candidates[i - 1] == candidates[i]) {
                    continue; // avoid duplicate
                }
                combination.add(candidates[i]);
                combination(res, combination, candidates, i + 1, sum + candidates[i], target);
                // backtracking
                combination.remove(combination.size() - 1);
            }
        }
        // sum > target -> return
    }
}
