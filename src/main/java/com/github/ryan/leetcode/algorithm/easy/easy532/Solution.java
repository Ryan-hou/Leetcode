package com.github.ryan.leetcode.algorithm.easy.easy532;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date December 01,2018
 */
public class Solution {

    public int findPairs(int[] nums, int k) {
        if (k < 0 || nums == null || nums.length < 2) return 0;

        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        int res = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (k == 0) {
                if (entry.getValue() > 1) res++;
            } else {
                if (map.get(entry.getKey() + k) != null) res++;
                // if (map.get(entry.getKey() - k) != null) res++;
            }
        }
        return res;
    }

}
