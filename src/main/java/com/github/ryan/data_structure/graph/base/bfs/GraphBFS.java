package com.github.ryan.data_structure.graph.base.bfs;

import com.github.ryan.data_structure.graph.base.UnweightedGraph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class GraphBFS {

    private UnweightedGraph g;
    private boolean[] visited;
    private List<Integer> order;

    public GraphBFS(UnweightedGraph g) {
        this.g = g;
        visited = new boolean[g.V()];
        order = new ArrayList<>();

        for (int v = 0; v < g.V(); v++) {
            if (!visited[v]) {
                bfs(v);
            }
        }
    }

    // 从顶点 s 开始 bfs 遍历图
    private void bfs(int s) {
        visited[s] = true;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(s);
        while (!q.isEmpty()) {
            int v = q.poll();
            order.add(v);
            for (int w : g.adj(v)) {
                if (!visited[w]) {
                    visited[w] = true;
                    q.offer(w);
                }
            }
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public static void main(String[] args) {
        UnweightedGraph ug = new UnweightedGraph("g.txt");
        GraphBFS bfs = new GraphBFS(ug);
        System.out.println("BFS order: " + bfs.order());
    }
}
