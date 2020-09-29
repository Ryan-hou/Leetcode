package com.github.ryan.data_structure.graph.base.bfs;

import com.github.ryan.data_structure.graph.base.UnweightedGraph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BipartitionDetection {

    private UnweightedGraph g;
    private boolean[] visited;
    private int[] colors;
    private boolean isBipartite = true;

    public BipartitionDetection(UnweightedGraph g) {
        if (g.isDirected()) {
            throw new IllegalArgumentException("Bipartition Detection only works in undirected graph.");
        }
        this.g = g;
        visited = new boolean[g.V()];
        colors = new int[g.V()];
        Arrays.fill(colors, -1);

        for (int v = 0; v < g.V(); v++) {
            if (!visited[v]) {
                if (!bfs(v)) {
                    isBipartite = false;
                    break;
                }
            }
        }
    }

    /**
     * 从顶点 s 开始尝试染色，看是否可以成功
     * @param s
     * @return true 表示可以染色成功
     */
    private boolean bfs(int s) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(s);
        visited[s] = true;
        colors[s] = 0;

        while (!q.isEmpty()) {
            int v = q.poll();
            for (int w : g.adj(v)) {
                if (!visited[w]) {
                    visited[w] = true;
                    colors[w] = 1 - colors[v];
                    q.offer(w);
                } else if (colors[v] == colors[w]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isBipartite() {
        return isBipartite;
    }

    public static void main(String[] args) {
        UnweightedGraph g = new UnweightedGraph("bipartition_detection.txt");
        BipartitionDetection bd = new BipartitionDetection(g);
        System.out.println("Is bipartite? " + bd.isBipartite());
    }
}
