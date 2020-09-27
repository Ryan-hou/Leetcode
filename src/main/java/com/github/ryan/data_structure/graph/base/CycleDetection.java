package com.github.ryan.data_structure.graph.base;

/**
 * 无向图环检测
 */
public class CycleDetection {

    private UnweightedGraph g;
    private boolean[] visited;
    private boolean hasCycle = false;

    public CycleDetection(UnweightedGraph g) {
        if (g.isDirected()) {
            throw new IllegalArgumentException("CycleDetection only works in undirected graph.");
        }

        this.g = g;
        visited = new boolean[g.V()];
        for (int v = 0; v < g.V(); v++) {
            if (!visited[v]) {
                if (dfs(v, v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    // 从顶点v开始dfs，寻找是否存在环
    private boolean dfs(int v, int parent) {
        visited[v] = true;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v)) {
                    return true;
                }
            } else if (w != parent) {
                return true;
            }
        }
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
