package com.github.ryan.leetcode.algorithm.medium.medium851;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {

    private ArrayList<Integer>[] graph; // DAG
    private int[] answer;
    private int[] quiet;

    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        graph = new ArrayList[n];
        answer = new int[n];
        this.quiet = quiet;

        for (int node = 0; node < n; node++) {
            graph[node] = new ArrayList<>();
        }
        for (int[] edge : richer) {
            // node A money < node B money -> Edge: A->B
            graph[edge[1]].add(edge[0]);
        }

        Arrays.fill(answer, -1);
        for (int node = 0; node < n; node++) {
            dfs(node);
        }
        return answer;
    }

    // return node that is quiest among all nodes(including itself)
    // that has equal or more money than this node
    private int dfs(int node) {
        if (answer[node] == -1) {
            answer[node] = node;
            for (int child : graph[node]) {
                int candidate = dfs(child);
                if (quiet[candidate] < quiet[answer[node]]) {
                    answer[node] = candidate;
                }
            }
        }
        return answer[node];
    }
}
