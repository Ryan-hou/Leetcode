package com.github.ryan.data_structure.graph.directed_graph;

import com.github.ryan.data_structure.graph.base.UnweightedGraph;

/**
 * 有向图环检测
 * 相比无向图环检测存在以下问题:
 * 已经遍历过不不代表形成环
 * 解决 -> 添加一个标记，在当前路径上
 */
public class DirectedCycleDetection {

    private UnweightedGraph g;
    private boolean[] visited;
    /**
     * 顶点v是否在当前路径上
     */
    private boolean[] onPath;

    private boolean hasCycle = false;

    public DirectedCycleDetection(UnweightedGraph g) {
        if (!g.isDirected()) {
            throw new IllegalArgumentException("DirectedCycleDetection only works in directed graph.");
        }

        this.g = g;
        visited = new boolean[g.V()];
        onPath = new boolean[g.V()];

        for (int v = 0; v < g.V(); v++) {
            if (dfs(v)) {
                hasCycle = true;
                break;
            }
        }
    }

    /**
     * 从顶点v开始dfs，看是否能够找到环
     * @param v
     * @return
     */
    private boolean dfs(int v) {
        visited[v] = true;
        onPath[v] = true;

        for (int w : g.adj(v)) {
            if (!visited[w]) {
                if (dfs(w)) {
                    return true;
                }
            } else if (onPath[w]) {
                return true;
            }
        }

        onPath[v] = false; // backtracking
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        UnweightedGraph ug = new UnweightedGraph("directed_cycle_detection.txt", true);
        DirectedCycleDetection dcd = new DirectedCycleDetection(ug);
        System.out.println("Directed graph has cycle? " + dcd.hasCycle());
    }

}
