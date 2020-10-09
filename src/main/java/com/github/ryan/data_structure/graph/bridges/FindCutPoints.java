package com.github.ryan.data_structure.graph.bridges;

import com.github.ryan.data_structure.graph.base.UnweightedGraph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 寻找无向图的割点
 * 思路类似于求解无向图中的桥，基于 DFS 遍历图，同时记录并维护相关状态遍历
 */
public class FindCutPoints {

    private UnweightedGraph g;
    private boolean[] visited;

    /**
     * ord[v] 表示 DFS 过程中，顶点 v 被访问到的顺序
     */
    private int[] ord;

    /**
     * low[v] 表示 DFS 过程中，通过顶点 v 从另外一条路能到达的
     * 顶点的最小 ord 值
     */
    private int[] low;

    /**
     * 记录 DFS 过程中，顶点被访问的顺序
     */
    private int cnt;

    private Set<Integer> cutPoints;

    public FindCutPoints(UnweightedGraph g) {
        // initialize data
        this.g = g;
        cutPoints = new HashSet<>();
        visited = new boolean[g.V()];
        ord = new int[g.V()];
        low = new int[g.V()];
        Arrays.fill(ord, -1);
        Arrays.fill(low, -1);
        cnt = 0;

        for (int v = 0; v < g.V(); v++) {
            if (!visited[v]) {
                dfs(v, v);
            }
        }
    }

    /**
     * 从顶点 v 开始 DFS 遍历图，寻找图中的割点
     * @param v
     * @param parent
     */
    private void dfs(int v, int parent) {
        visited[v] = true;
        ord[v] = cnt;
        low[v] = ord[v];

        cnt++;
        int child = 0;
        for (int w : g.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);

                // 根节点单独考虑
                if (v != parent && low[w] >= ord[v]) {
                    cutPoints.add(v);
                }
                child++;
                if (v == parent && child > 1) {
                    cutPoints.add(v);
                }

            } else if (w != parent) {
                // visited[w] == true -> 存在环 -> v 不是割点
                low[v] = Math.min(low[v], low[w]);
            }
        }
    }

    public Iterable<Integer> listCutPoints() {
        return cutPoints;
    }

    public static void main(String[] args) {
        UnweightedGraph g = new UnweightedGraph("bridges.txt");

        FindCutPoints fc = new FindCutPoints(g);
        System.out.println("Cut Points: " + fc.listCutPoints());
    }

}
