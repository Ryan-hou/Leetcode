package com.github.ryan.data_structure.graph.base.bfs;

import com.github.ryan.data_structure.graph.base.UnweightedGraph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class CycleDetection {

    private UnweightedGraph g;
    private boolean[] visited;
    private int[] pre;
    private boolean hasCycle = false;

    public CycleDetection(UnweightedGraph g) {
        if (g.isDirected()) {
            throw new IllegalArgumentException("CycleDetection only works in undirected graph");
        }
        this.g = g;
        visited = new boolean[g.V()];
        pre = new int[g.V()];
        Arrays.fill(pre, -1);

        for (int v = 0; v < g.V(); v++) {
            if (!visited[v]) {
                if (bfs(v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    // 从顶点 s 开始判断无向图中是否存在环
    private boolean bfs(int s) {
        visited[s] = true;
        pre[s] = s;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(s);
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int w : g.adj(v)) {
                if (!visited[w]) {
                    visited[w] = true;
                    pre[w] = v;
                } else if (pre[w] != v) {
                    return true;
                }
            }
        } // end while
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        UnweightedGraph ug = new UnweightedGraph("cycle_detection.txt");
        CycleDetection detection = new CycleDetection(ug);
        System.out.println("Undirected graph has cycle? " + detection.hasCycle());
    }
}
