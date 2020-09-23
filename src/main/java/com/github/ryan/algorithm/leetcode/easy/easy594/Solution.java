package com.github.ryan.algorithm.leetcode.easy.easy594;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int findLHS(int[] nums) {
        if (nums == null || nums.length < 1) return 0;
        // key -> num in nums, value -> num's count
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        int res = 0;
        for (int n : map.keySet()) {
            if (map.containsKey(n + 1)) {
                res = Math.max(res, map.get(n) + map.get(n + 1));
            }
        }
        return res;
    }

}
