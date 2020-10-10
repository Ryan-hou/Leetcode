package com.github.ryan.data_structure.graph.matching;

import com.github.ryan.data_structure.graph.base.UnweightedGraph;
import com.github.ryan.data_structure.graph.base.WeightedGraph;
import com.github.ryan.data_structure.graph.base.dfs.BipartitionDetection;
import com.github.ryan.data_structure.graph.network_flows.MaxFlow;

/**
 * 二分图匹配求解，采用最大流算法实现
 */
public class BipartiteMatching {

    private UnweightedGraph g;

    /**
     * 二分图的最大匹配数
     */
    private int maxMatching;

    public BipartiteMatching(UnweightedGraph g) {
        BipartitionDetection bd = new BipartitionDetection(g);
        if (!bd.isBipartite()) {
            throw new IllegalArgumentException("BipartiteMatching only works for bipartite graph.");
        }

        this.g = g;
        maxMatching = 0;
        int[] colors = bd.colors();

        // 通过无向无权图构造有向有权图
        // 源点为 g.V(), 汇点为 g.V() + 1
        // 源点与 color 值为 0 的顶点建立边，color 值为 1的顶点与汇点建立边
        // color 值为 0 的顶点建立指向 color 值为 1的顶点的有向边
        WeightedGraph network = new WeightedGraph(g.V() + 2, true);
        for (int v = 0; v < g.V(); v++) {
            if (colors[v] == 0) {
                network.addEdge(g.V(), v, 1);
            } else {
                network.addEdge(v, g.V() + 1, 1);
            }

            for (int w : g.adj(v)) {
                if (v < w) {
                    // 避免重复添加边
                    if (colors[v] == 0) {
                        network.addEdge(v, w, 1);
                    } else {
                        network.addEdge(w, v, 1);
                    }
                }
            }
        } // end outer for

        MaxFlow maxFlow = new MaxFlow(network, g.V(), g.V() + 1);
        maxMatching = maxFlow.getMaxFlow();
    }

    public int maxMatching() {
        return maxMatching;
    }

    /**
     * 二分图是否存在完全匹配
     * @return
     */
    public boolean isPerfectMatching() {
        return maxMatching * 2 == g.V();
    }

    public static void main(String[] args) {
        UnweightedGraph g = new UnweightedGraph("matching.txt");
        BipartiteMatching bm = new BipartiteMatching(g);
        System.out.println("Max matching is: " + bm.maxMatching());
        System.out.println("Is perfect match? " + bm.isPerfectMatching());
    }

}
