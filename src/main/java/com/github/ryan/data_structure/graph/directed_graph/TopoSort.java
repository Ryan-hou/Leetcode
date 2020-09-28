package com.github.ryan.data_structure.graph.directed_graph;

import com.github.ryan.data_structure.graph.base.UnweightedGraph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 拓扑排序
 * DAG(Directed Acyclic Graph) 才存在拓扑排序
 * 每次更新入度值，把⼊度为 0 的顶点放⼊⼀个队列，每次从队列中取出下⼀个点
 * 拓扑排序结果不唯一
 */
public class TopoSort {

    private UnweightedGraph g;
    private boolean hasCycle = false;
    private List<Integer> res;

    public TopoSort(UnweightedGraph g) {
        if (!g.isDirected()) {
            throw new IllegalArgumentException("Topological sort only works in directed graph.");
        }
        this.g = g;
        res = new ArrayList<>();
        Queue<Integer> q = new ArrayDeque<>();

        int[] indegree = new int[g.V()];
        for (int v = 0; v < g.V(); v++) {
            indegree[v] = g.indegree(v);
            if (indegree[v] == 0) {
                q.offer(v);
            }
        }

        while (!q.isEmpty()) {
            int v = q.poll();
            res.add(v);
            for (int next : g.adj(v)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        } // end while

        if (res.size() != g.V()) {
            hasCycle = true;
            res.clear();
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public List<Integer> result() {
        return res;
    }

    public static void main(String[] args) {
        UnweightedGraph g = new UnweightedGraph("directed_cycle_detection.txt", true);
        TopoSort topoSort = new TopoSort(g);
        System.out.println("Topological sort: " + topoSort.result());
    }
}
