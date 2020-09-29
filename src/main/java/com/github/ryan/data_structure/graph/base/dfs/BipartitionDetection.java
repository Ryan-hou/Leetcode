package com.github.ryan.data_structure.graph.base.dfs;

import com.github.ryan.data_structure.graph.base.UnweightedGraph;

import java.util.Arrays;

/**
 * 二分图检测
 */
public class BipartitionDetection {

    private UnweightedGraph g;
    private boolean[] visited;
    /**
     * 给图中顶点染色, 值为 -1 时表示未被访问
     * (可以用 colors 未 -1 时表示 visited, 为了语义更加明确，仍然单独定义 visited 数组)
     * 值为 0,1 分别表示两种不同的颜色
     */
    private int[] colors;

    private boolean isBipartite = true;

    public BipartitionDetection(UnweightedGraph g) {
        if (g.isDirected()) {
            throw new IllegalArgumentException("Bipartition detection only works in undirected graph.");
        }
        this.g = g;
        visited = new boolean[g.V()];
        colors = new int[g.V()];
        Arrays.fill(colors, -1);

        int color = 0;
        for (int v = 0; v < g.V(); v++) {
            if (!visited[v]) {
                if (!dfs(v, color)) {
                    isBipartite = false;
                    break;
                }
            }
        }
    }

    /**
     * 从顶点 v 开始尝试染色为 color，看是否能成功
     * @param v
     * @param color
     * @return true 可以染色成功
     */
    private boolean dfs(int v, int color) {
        visited[v] = true;
        colors[v] = color;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                if (!dfs(w, 1 - color)) {
                    return false;
                }
            } else if (colors[v] == colors[w]) {
                return false;
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
