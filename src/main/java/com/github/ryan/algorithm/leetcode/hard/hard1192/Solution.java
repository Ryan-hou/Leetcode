package com.github.ryan.algorithm.leetcode.hard.hard1192;

import java.util.*;

public class Solution {

    private List<List<Integer>> res;

    // Tarjan algorithm
    // https://www.youtube.com/watch?v=mKUsbABiwBI
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // build graph
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (List<Integer> edge : connections) {
            int node1 = edge.get(0);
            int node2 = edge.get(1);
            graph.putIfAbsent(node1, new ArrayList<>());
            graph.putIfAbsent(node2, new ArrayList<>());
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }

        res = new ArrayList<>();
        // populate timestamps array using tarjan's algorithm
        // timestamps[i] -> minimal timestamp from node i
        int[] timestamps = new int[n];
        dfs(graph, 0, 0, 1, timestamps);
        return res;
    }

    private int dfs(Map<Integer, List<Integer>> graph, int cur, int parent, int ts, int[] timestamps) {
        timestamps[cur] = ts;
        for (Integer nextNode : graph.get(cur)) {
            if (nextNode == parent) continue;

            if (timestamps[nextNode] != 0) {
                timestamps[cur] = Math.min(timestamps[cur], timestamps[nextNode]);
            } else {
                // DFS
                timestamps[cur] = Math.min(timestamps[cur], dfs(graph, nextNode, cur, ts + 1, timestamps));
            }

            // As defined by Tarjan's algorithm, if the timestamp of the current node is already
            // smaller than that of it's next node (child), then the edge connecting the
            // current and next nodes make up a critical connection.
            if (ts < timestamps[nextNode]) {
                res.add(Arrays.asList(cur, nextNode));
            }
        }
        return timestamps[cur];
    }

}
