package com.github.ryan.leetcode.algorithm.medium.medium373;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

    // Use priority queue
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums1 == null || nums1.length < 1
                || nums2 == null || nums2.length < 1
                || k < 1) {
            return res;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((t1, t2) -> t1[0] + t1[1] - t2[0] - t2[1]);
        boolean[][] visited = new boolean[nums1.length][nums2.length];

        int[] min = new int[] { nums1[0], nums2[0], 0, 0 };
        pq.add(min);
        visited[0][0] = true;
        while (!pq.isEmpty() && res.size() != k) {
            int[] e = pq.poll();
            List<Integer> pair = new ArrayList<>();
            pair.add(e[0]);
            pair.add(e[1]);
            res.add(pair);
            // num1 index add 1
            if (e[2] + 1 < nums1.length && !visited[e[2] + 1][e[3]]) {
                visited[e[2] + 1][e[3]] = true;
                pq.add(new int[] { nums1[e[2] + 1], nums2[e[3]], e[2] + 1, e[3] });
            }
            // num2 index add 1
            if (e[3] + 1 < nums2.length && !visited[e[2]][e[3] + 1]) {
                visited[e[2]][e[3] + 1] = true;
                pq.add(new int[] { nums1[e[2]], nums2[e[3] + 1], e[2], e[3] + 1 });
            }
        }
        return res;
    }

}
