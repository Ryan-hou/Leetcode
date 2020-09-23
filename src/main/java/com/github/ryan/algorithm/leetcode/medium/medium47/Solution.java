package com.github.ryan.algorithm.leetcode.medium.medium47;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 27,2019
 */
public class Solution {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 1) return res;

        Arrays.sort(nums);
        boolean[] seen = new boolean[nums.length];
        permute(nums, res, new LinkedList<>(), seen);
        return res;
    }

    private void permute(int[] nums, List<List<Integer>> res, LinkedList<Integer> cur, boolean[] seen) {
        if (cur.size() == nums.length) {
            res.add(new LinkedList(cur));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (seen[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1] && seen[i - 1]) continue;

            seen[i] = true;
            cur.add(nums[i]);
            permute(nums, res, cur, seen);
            cur.removeLast();
            seen[i] = false;
        }
        return;
    }


    // use backtracking
    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 1) return res;

        Set<String> set = new HashSet<>();
        List<Integer> nlist = IntStream.of(nums).boxed().collect(Collectors.toList());
        permute(nlist, res, new ArrayList<>(), set, nums.length);
        return res;
    }

    private void permute(List<Integer> nums, List<List<Integer>> res, List<Integer> perm, Set<String> set, int n) {
        if (perm.size() == n) {
            if (!set.contains(perm.toString())) {
                res.add(new ArrayList<>(perm));
                set.add(perm.toString());
            }
            return;
        }

        for (int i = 0; i < nums.size(); i++) {
            perm.add(nums.get(i));
            int val = nums.get(i);
            nums.remove(i);
            permute(nums, res, perm, set, n);
            nums.add(i, val);
            perm.remove(perm.size() - 1);
        }
        return;
    }


}
