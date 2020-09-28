package com.github.ryan.data_structure.graph.mst;

import com.github.ryan.data_structure.graph.base.dfs.CC;
import com.github.ryan.data_structure.graph.base.WeightedEdge;
import com.github.ryan.data_structure.graph.base.WeightedGraph;
import com.github.ryan.data_structure.tree.union_find.UnionFind3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kruskal {

    private WeightedGraph G;
    private List<WeightedEdge> mst;

    /**
     * 时间复杂度 O(ElogE), 最耗时的操作为边的排序操作
     * @param G
     */
    public Kruskal(WeightedGraph G) {
        this.G = G;
        mst = new ArrayList<>();

        CC cc = new CC(G);
        if (cc.count() > 1) {
            return;
        }

        List<WeightedEdge> edges = new ArrayList<>();
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (v < w) {
                    edges.add(new WeightedEdge(v, w, G.getWeight(v, w)));
                }
            }
        }
        // 排序后每次贪心取短边
        Collections.sort(edges);

        // 取短边后，需要判断该边与其他边是否存在环 -> 判断顶点连通性
        UnionFind3 uf = new UnionFind3(G.V());
        // 并查集操作的时间复杂度近似为 O(1) 远小于 O(logn)
        for (WeightedEdge edge : edges) {
            int v = edge.getV();
            int w = edge.getW();
            if (!uf.isConnected(v, w)) {
                mst.add(edge);
                uf.union(v, w);
            }
        }
    }

    public List<WeightedEdge> mst() {
        return mst;
    }

    public static void main(String[] args) {
        WeightedGraph wg = new WeightedGraph("mst.txt");
        Kruskal kruskal = new Kruskal(wg);
        System.out.println("Minimal Spanning Tree is: "  + kruskal.mst());
    }
}
