package com.github.ryan.algorithm.leetcode.medium.medium743;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    // Dijkstra's Algorithm
    public int networkDelayTime(int[][] times, int N, int K) {
        // key -> source node, value -> list of destination nodes with delay time
        Map<Integer, List<int[]>> g = new HashMap<>();
        for (int[] t : times) {
            g.computeIfAbsent(t[0], x -> new ArrayList<>()).add(new int[] { t[1], t[2] });
        }
        // key -> node, value -> distence from K node to this node
        Map<Integer, Integer> dist = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            dist.put(i, Integer.MAX_VALUE);
        }
        dist.put(K, 0);
        boolean[] seen = new boolean[N + 1];

        while (true) {
            int curNode = -1;
            int curDist = Integer.MAX_VALUE;
            for (int i = 1; i <= N; i++) {
                if (!seen[i] && dist.get(i) < curDist) {
                    curNode = i;
                    curDist = dist.get(i);
                }
            }

            if (curNode == -1) break;

            seen[curNode] = true;
            if (g.containsKey(curNode)) {
                for (int[] d : g.get(curNode)) {
                    dist.put(d[0], Math.min(dist.get(d[0]), dist.get(curNode) + d[1]));
                }
            }
        } // end while

        int res = -1;
        for (int d : dist.values()) {
            if (d == Integer.MAX_VALUE) return -1;
            res = Math.max(res, d);
        }
        return res;
    }

}
