package com.github.ryan.leetcode.algorithm.medium.medium1167;

import java.util.PriorityQueue;

public class Solution {

    public int connectSticks(int[] sticks) {
        // min heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : sticks) {
            pq.offer(s);
        }

        int res = 0;
        while (pq.size() > 1) {
            int a1 = pq.poll();
            int a2 = pq.poll();
            res += (a1 + a2);
            pq.offer(a1 + a2);
        }
        return res;
    }
}
