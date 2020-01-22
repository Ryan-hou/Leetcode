package com.github.ryan.leetcode.algorithm.hard.hard632;

import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

    public int[] smallestRange(List<List<Integer>> nums) {
        // int[0] -> index of list in lists
        // int[1] -> current min value of list at index int[0]
        PriorityQueue<int[]> pq = new PriorityQueue<>((n1, n2) -> n1[1] - n2[1]);
        int left = 0, right = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        int n = nums.size();
        // key -> index of lists, value -> list's iterator at key index
        Iterator<Integer>[] map = new Iterator[n];
        for (int i = 0; i < n; i++) {
            map[i] = nums.get(i).iterator();
            int val = map[i].next();
            pq.offer(new int[] { i, val });
            maxVal = Math.max(maxVal, val);
        }

        while (pq.size() == n) {
            int[] min = pq.poll();
            if (maxVal - min[1] < right - left) {
                left = min[1];
                right = maxVal;
            }

            int idx = min[0];
            if (map[idx].hasNext()) {
                int val = map[idx].next();
                maxVal = Math.max(maxVal, val);
                pq.offer(new int[] { idx, val });
            }
        }

        return new int[] { left, right };
    }

}
