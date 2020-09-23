package com.github.ryan.algorithm.leetcode.medium.medium973;

import java.util.PriorityQueue;

public class Solution {

    // Heap
    public int[][] kClosest(int[][] points, int K) {
        // max heap
        PriorityQueue<int[]> pq =
                new PriorityQueue<>((a1, a2)
                        -> (a2[0] * a2[0] + a2[1] *a2[1]) - (a1[0] * a1[0] + a1[1] * a1[1]));

        for (int i = 0; i < points.length; i++) {
            pq.offer(points[i]);
            if (pq.size() > K) {
                pq.poll();
            }
        }

        int[][] res = new int[K][2];
        for (int i = K - 1; i >= 0; i--) {
            int[] ele = pq.poll();
            res[i][0] = ele[0];
            res[i][1] = ele[1];
        }
        return res;
    }
}
