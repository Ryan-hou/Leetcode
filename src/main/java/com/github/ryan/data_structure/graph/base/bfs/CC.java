package com.github.ryan.data_structure.graph.base.bfs;

import com.github.ryan.data_structure.graph.base.GraphUtil;
import com.github.ryan.data_structure.graph.base.UnweightedGraph;

import java.util.*;

public class CC {

    private UnweightedGraph g;
    // -1 表示节点未被访问；其余的值为连通分量的id值
    private int[] visited;
    /**
     * 连通分量个数
     */
    private int cccount = 0;

    public CC(UnweightedGraph g) {
        if (g.isDirected()) {
            throw new IllegalArgumentException("CC only works in undirected graph.");
        }
        this.g = g;

        visited = new int[g.V()];
        Arrays.fill(visited, -1);
        for (int v = 0; v < g.V(); v++) {
            if (visited[v] == -1) {
                bfs(v, cccount);
                cccount++;
            }
        }
    }

    private void bfs(int s, int ccid) {
        visited[s] = ccid;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(s);
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int w : g.adj(v)) {
                if (visited[w] == -1) {
                    q.offer(w);
                    visited[w] = cccount;
                }
            }
        }
    }

    public int count() {
        return cccount;
    }

    public boolean isConnected(int v, int w) {
        GraphUtil.validateVertex(g, v);
        GraphUtil.validateVertex(g, w);
        return visited[v] == visited[w];
    }

    public List<Integer>[] components() {
        List<Integer>[] res = new List[cccount];
        for (int i = 0; i < res.length; i++) {
            res[i] = new ArrayList<>();
        }

        for (int v = 0; v < g.V(); v++) {
            res[visited[v]].add(v);
        }
        return res;
    }

    public static void main(String[] args) {
        UnweightedGraph g = new UnweightedGraph("g.txt");
        CC cc = new CC(g);
        System.out.println(cc.cccount);
        List<Integer>[] components = cc.components();
        for (int i = 0; i < components.length; i++) {
            System.out.println(components[i]);
        }
    }

}
