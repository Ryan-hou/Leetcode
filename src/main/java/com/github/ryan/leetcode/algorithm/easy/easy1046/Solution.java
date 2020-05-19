package com.github.ryan.leetcode.algorithm.easy.easy1046;

import java.util.PriorityQueue;

public class Solution {

    public int lastStoneWeight(int[] stones) {
        // max heap
        PriorityQueue<Integer> pq = new PriorityQueue<>((a1, a2) -> a2 - a1);
        for (int s : stones) {
            pq.offer(s);
        }
        while (pq.size() >= 2) {
            int n1 = pq.poll();
            int n2 = pq.poll();
            if (n1 != n2) {
                pq.offer(n1 - n2);
            }
        }
        return pq.size() == 0 ? 0 : pq.poll();
    }

}
