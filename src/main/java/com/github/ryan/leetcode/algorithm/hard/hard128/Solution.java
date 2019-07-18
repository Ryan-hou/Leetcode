package com.github.ryan.leetcode.algorithm.hard.hard128;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 18,2019
 */
public class Solution {

    // union find
    public int longestConsecutive(int[] nums) {
        int sz = 0;
        if (nums == null || nums.length == 0) return sz;

        // build graph pair
        Map<Integer, Integer> g = new HashMap<>();
        for (int n : nums) {
            if (g.get(n - 1) != null) {
                g.put(n - 1, n);
            }
            if (g.get(n + 1) != null) {
                g.put(n, n + 1);
            } else {
                g.put(n, n);
            }
        }

        for (Integer key : g.keySet()) {
            int prev = key;
            Integer next = g.get(prev);
            while (next != null && next != prev) {
                g.put(prev, prev); // delete used pair
                g.put(key, next);
                prev = next;
                next = g.get(next);
            }
            sz = Math.max(sz, prev - key + 1);
        }
        return sz;
    }
}
