package com.github.ryan.leetcode.algorithm.medium.medium399;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 14,2019
 */
public class Solution {


    // use graph -> dfs
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        double[] res = new double[queries.size()];
        // build graph: key -> vertex, value: all adjacent vertexs and values
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);

            // a -> b
            graph.putIfAbsent(a, new HashMap<>());
            graph.get(a).put(b, values[i]);
            // b -> a
            graph.putIfAbsent(b, new HashMap<>());
            graph.get(b).put(a, 1 / values[i]);
        }

        int i = 0;
        for (List<String> q : queries) {
            String s = q.get(0);
            String e = q.get(1);
            Set<String> visited = new HashSet<>();
            res[i++] = dfs(graph, s, e, visited);
        }
        return res;
    }

    private double dfs(Map<String, Map<String, Double>> graph, String start, String end, Set<String> visited) {
        if (!graph.containsKey(start)) return -1.0;
        if (graph.get(start).containsKey(end)) return graph.get(start).get(end);

        for (Map.Entry<String, Double> entry : graph.get(start).entrySet()) {
            if (!visited.contains(entry.getKey())) {
                visited.add(entry.getKey());
                double res = dfs(graph, entry.getKey(), end, visited);
                if (res != -1.0) {
                    return res * entry.getValue();
                }
            }
        }
        return -1.0;
    }
}
