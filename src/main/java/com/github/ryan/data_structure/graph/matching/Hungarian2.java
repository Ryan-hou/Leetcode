package com.github.ryan.data_structure.graph.matching;

import com.github.ryan.data_structure.graph.base.UnweightedGraph;
import com.github.ryan.data_structure.graph.base.dfs.BipartitionDetection;

import java.util.Arrays;

/**
 * 匈牙利算法求解二分图最大匹配问题 DFS
 */
public class Hungarian2 {

    private UnweightedGraph g;
    private int maxMatching;
    private int[] matching;
    private boolean[] visited;

    public Hungarian2(UnweightedGraph g) {
        BipartitionDetection bd = new BipartitionDetection(g);
        if (!bd.isBipartite()) {
            throw new IllegalArgumentException("Hungarian only works for bipartite graph.");
        }
        this.g = g;
        maxMatching = 0;
        matching = new int[g.V()];
        Arrays.fill(matching, -1);
        visited = new boolean[g.V()];
        int[] colors = bd.colors();

        for (int v = 0; v < g.V(); v++) {
            if (colors[v] == 0 && matching[v] == -1) {
                Arrays.fill(visited, false);
                if (dfs(v)) {
                    maxMatching++;
                }
            }
        }
    }

    /**
     * 从顶点 v 开始 dfs 寻找是否存在增广路径，存在增广路径则返回 true
     * @param v
     * @return
     */
    private boolean dfs(int v) {
        visited[v] = true;

        for (int w : g.adj(v)) {
            if (!visited[w]) {
                visited[w] = true;
                if (matching[w] == -1 || dfs(matching[w])) {
                    matching[w] = v;
                    matching[v] = w;
                    return true;
                }
            }
        }
        return false;
    }

    public int maxMatching() {
        return maxMatching;
    }

    public static void main(String[] args) {
        UnweightedGraph g = new UnweightedGraph("matching.txt");
        Hungarian2 hungarian2 = new Hungarian2(g);
        System.out.println("Max matching is: " + hungarian2.maxMatching());
    }
}
