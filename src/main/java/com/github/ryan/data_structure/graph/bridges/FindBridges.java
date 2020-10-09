package com.github.ryan.data_structure.graph.bridges;

import com.github.ryan.data_structure.graph.base.Edge;
import com.github.ryan.data_structure.graph.base.UnweightedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 寻找无向图中的桥
 * 基于 DFS 遍历图，同时记录并维护相关状态变量
 */
public class FindBridges {

    private UnweightedGraph g;
    private boolean[] visited;
    /**
     * ord[v] 表示 DFS 过程中顶点 v 被访问到的顺序
     * 该顺序用来记录顶点间的父子关系
     */
    private int[] ord;
    /**
     * 记录 DFS 过程中顶点被访问的顺序
     */
    private int cnt;
    /**
     * low[v] 表示 DFS 过程中，通过顶点 v 从另外一条路
     * 能到达的最小 ord 值
     */
    private int[] low;

    private List<Edge> bridges;

    public FindBridges(UnweightedGraph g) {
        this.g = g;
        visited = new boolean[g.V()];

        bridges = new ArrayList<>();
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
     * 从顶点 v 开始 DFS 寻找桥
     * @param v
     * @param parent
     */
    private void dfs(int v, int parent) {
        /**
         * 维护状态变量的语义
         */
        visited[v] = true;
        ord[v] = cnt;
        low[v] = ord[v];
        cnt++;

        for (int w : g.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);
                // low[w] <= ord[v] -> 通过顶点 w 存在另一条路到达 v 或者 v 的父亲节点
                if (low[w] > ord[v]) {
                    bridges.add(new Edge(v, w));
                }
            } else if (w != parent) {
                // visited[w] == true -> 存在环 -> v-w 边不是桥
                low[v] = Math.min(low[v], low[w]);
            }
        }
    }

    public List<Edge> listBridges() {
        return bridges;
    }

    public static void main(String[] args) {
        UnweightedGraph g = new UnweightedGraph("bridges.txt");
        FindBridges fb = new FindBridges(g);
        System.out.println("Bridges: " + fb.listBridges());
    }

}
