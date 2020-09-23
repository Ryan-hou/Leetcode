package com.github.ryan.algorithm.leetcode.medium.medium787;

import java.util.*;

public class Solution {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        // key -> src city, value -> dst city list and src to dst's price
        Map<Integer, List<int[]>> map = new HashMap<>(n * 2);
        for (int[] f : flights) {
            map.putIfAbsent(f[0], new ArrayList<>());
            map.get(f[0]).add(new int[] { f[1], f[2]});
        }
        // int[0] city num, int[1] from src to int[0]'s min price
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { src, 0 });
        int res = Integer.MAX_VALUE;
        int stop = 0;
        // BFS
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                int[] cur = q.poll();
                if (cur[0] == dst) {
                    res = Math.min(res, cur[1]);
                }
                List<int[]> next = map.get(cur[0]);
                if (next != null) {
                    for (int[] node : next) {
                        if (cur[1] + node[1] > res) {
                            continue;
                        } else {
                            q.offer(new int[] { node[0], cur[1] + node[1] });
                        }
                    }
                }
            } // end for
            if (stop > K) break;
            stop += 1;
        }
        return res == Integer.MAX_VALUE ? -1 : res;

    }

}
