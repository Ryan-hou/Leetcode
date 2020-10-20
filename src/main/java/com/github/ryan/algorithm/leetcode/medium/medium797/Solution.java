package com.github.ryan.algorithm.leetcode.medium.medium797;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    private int V;
    private int[] pre;
    private List<List<Integer>> res;
    private int[][] graph;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        this.graph = graph;
        V = graph.length;
        pre = new int[V];
        res = new ArrayList<>();
        pre[0] = 0;
        dfs(0);

        return res;
    }

    private void dfs(int v) {
        if (v == V - 1) {
            res.add(getPath());
            return;
        }

        for (int w : graph[v]) {
            pre[w] = v;
            dfs(w);
        }
    }

    private List<Integer> getPath() {
        List<Integer> res = new ArrayList<>();
        int cur = V - 1;
        while (cur != 0) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(0);
        Collections.reverse(res);
        return res;
    }
}
