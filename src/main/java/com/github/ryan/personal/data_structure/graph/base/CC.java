package com.github.ryan.personal.data_structure.graph.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Connected Component: 连通分量
 */
public class CC {

    private Graph G;
    // -1 表示节点未被访问；其余的值为连通分量的id值
    private int[] visited;
    /**
     * 连通分量个数
     */
    private int cccount = 0;

    public CC(Graph g) {
        this.G = g;
        visited = new int[G.V()];
        Arrays.fill(visited, -1);

        for (int v = 0; v < G.V(); v++) {
            if (visited[v] == -1) {
                dfs(v, cccount);
                cccount++;
            }
        }
    }

    // 从顶点 v 开始进行 dfs，遍历到的顶点标记上连通分量id 即 ccid
    private void dfs(int v, int ccid) {
        visited[v] = ccid;
        for (int w : G.adj(v)) {
            if (visited[w] == -1) {
                dfs(w, ccid);
            }
        }
    }

    public int count() {
        return cccount;
    }

    public boolean isConnected(int v, int w) {
        GraphUtil.validateVertex(G, v);
        GraphUtil.validateVertex(G, w);
        return visited[v] == visited[w];
    }

    public List<Integer>[] components() {
        List<Integer>[] res = new List[cccount];
        for (int i = 0; i < cccount; i++) {
            res[i] = new ArrayList<>();
        }

        for (int v = 0; v < G.V(); v++) {
            res[visited[v]].add(v);
        }
        return res;
    }

    public static void main(String[] args) {
        Graph g = new UnweightedGraph("g.txt");
        CC cc = new CC(g);
        System.out.println(cc.cccount);
        List<Integer>[] components = cc.components();
        for (int i = 0; i < components.length; i++) {
            System.out.println(components[i]);
        }
    }

}
