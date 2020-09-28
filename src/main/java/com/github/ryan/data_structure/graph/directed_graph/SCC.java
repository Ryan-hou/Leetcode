package com.github.ryan.data_structure.graph.directed_graph;

import com.github.ryan.data_structure.graph.base.dfs.GraphDFS;
import com.github.ryan.data_structure.graph.base.GraphUtil;
import com.github.ryan.data_structure.graph.base.UnweightedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Strongly Connected Component
 * 求解有向图的强连通分量 -> Kosaraju 算法
 */
public class SCC {

    private UnweightedGraph g;
    /**
     * visited[i] 的值为 -1 表示顶点i未被访问，其余值则为该顶点所属连通分量的id值
     */
    private int[] visited;
    /**
     * 强连通分量的个数: strong connected component count
     */
    private int scccount;

    public SCC(UnweightedGraph g) {
        if (!g.isDirected()) {
            throw new IllegalArgumentException("SCC only works in directed graph");
        }

        this.g = g;
        visited = new int[g.V()];
        Arrays.fill(visited, -1);

        scccount = 0;
        GraphDFS dfs = new GraphDFS(g.reverse());
        List<Integer> post = dfs.post();
        List<Integer> order = new ArrayList<>();
        // 反图后序遍历的逆 -> 强连通分量构成的图的拓扑排序的逆
        for (int i = post.size() - 1; i >= 0; i--) {
            order.add(post.get(i));
        }

        for (int v : order) {
            if (visited[v] == -1) {
                dfs(v, scccount);
                scccount++;
            }
        }
    }

    private void dfs(int v, int sccid) {
        visited[v] = sccid;
        for (int w : g.adj(v)) {
            if (visited[w] == -1) {
                dfs(w, sccid);
            }
        }
    }

    public int count() {
        return scccount;
    }

    public boolean isStronglyConnected(int v, int w) {
        GraphUtil.validateVertex(g, v);
        GraphUtil.validateVertex(g, w);
        return visited[v] == visited[w];
    }

    public List<Integer>[] components() {
        List<Integer>[] res = new ArrayList[scccount];
        for (int i = 0; i < res.length; i++) {
            res[i] = new ArrayList<>();
        }

        for (int v = 0; v < g.V(); v++) {
            res[visited[v]].add(v);
        }
        return res;
    }

    public static void main(String[] args) {
        UnweightedGraph g = new UnweightedGraph("scc.txt", true);
        SCC scc = new SCC(g);
        System.out.println("Strongly Connected Component number is: " + scc.count());

        System.out.println("Components ars: ");
        for (int i = 0; i < scc.scccount; i++) {
            System.out.println(scc.components()[i]);
        }
    }
}
