package com.github.ryan.algorithm.leetcode.hard.hard239;

import java.util.PriorityQueue;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 18,2019
 */
public class Solution {

    // use priority queue
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[0];

        int len = nums.length - (k - 1);
        int[] res = new int[len];
        // max heap
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (a1, a2) -> a2 - a1);
        for (int i = 0; i < k; i++) {
            pq.add(nums[i]);
        }

        int idx = 0;
        for (int i = 0; i < len; i++) {
            res[idx++] = pq.peek();
            if (i != len - 1) {
                pq.remove(nums[i]);
                pq.add(nums[i + k]);
            }
        }
        while (idx < len) {
            res[idx++] = pq.peek();
        }
        return res;
    }
}
