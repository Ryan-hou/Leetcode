package com.github.ryan.leetcode.algorithm.medium.medium785;

import java.util.Arrays;

public class Solution {

    private boolean[] visited;
    private int[] colors;
    private int[][] graph;

    public boolean isBipartite(int[][] graph) {
        this.graph = graph;
        int V = graph.length;
        this.visited = new boolean[V];
        this.colors = new int[V];
        // colors[i]: -1 -> no color, 0 -> red, 1 -> black
        Arrays.fill(colors, -1);

        for (int v = 0; v < V; v++) {
            if (!visited[v]) {
                if (!dfs(v, 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    // 从顶点 v 遍历染色，看是否可以二分
    private boolean dfs(int v, int color) {
        visited[v] = true;
        colors[v] = color;
        for (int w : graph[v]) {
            if (!visited[w]) {
                if (!dfs(w, 1 - color)) {
                    return false;
                }
            } else if (colors[w] == colors[v]) {
                return false;
            }
        } // end for
        return true;
    }

}
