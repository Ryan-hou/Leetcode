package com.github.ryan.leetcode.algorithm.medium.medium90;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 16,2019
 */
public class Solution {

    // use dfs
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 0) return res;

        Arrays.sort(nums);
        helper(new ArrayList<>(), 0, nums, res);
        return res;
    }

    private void helper(List<Integer> subSet, int start, int[] nums, List<List<Integer>> res) {
        res.add(new ArrayList<>(subSet));
        for (int i = start; i < nums.length; i++) {
            if (i != start && nums[i] == nums[i - 1]) continue;

            subSet.add(nums[i]);
            helper(subSet, i + 1, nums, res);
            subSet.remove(subSet.size() - 1);
        }
    }

}
