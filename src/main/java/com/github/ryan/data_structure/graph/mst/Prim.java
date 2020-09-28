package com.github.ryan.data_structure.graph.mst;

import com.github.ryan.data_structure.graph.base.dfs.CC;
import com.github.ryan.data_structure.graph.base.WeightedEdge;
import com.github.ryan.data_structure.graph.base.WeightedGraph;

import java.util.ArrayList;
import java.util.List;

public class Prim {

    private WeightedGraph G;
    private List<WeightedEdge> mst;

    public Prim(WeightedGraph g) {
        G = g;
        mst = new ArrayList<>();

        CC cc = new CC(g);
        if (cc.count() > 1) {
            return;
        }

        // Prim
        // 正向使用切分定理，每次寻找最小的横切边
        boolean[] visited = new boolean[G.V()];
        visited[0] = true;
        // 时间复杂度 O((v - 1) * (V + E)) -> O(VE)
        // 获取 V - 1 条横切边构成 mst
        for (int i = 1; i < G.V(); i++) {

            WeightedEdge minEdge = new WeightedEdge(-1, - 1, Integer.MAX_VALUE);
            // 遍历所有横切边，选取最小值
            for (int v = 0; v < G.V(); v++) {
                if (visited[v]) {
                    for (int w : G.adj(v)) {
                        if (!visited[w] && G.getWeight(v, w) < minEdge.getWeight()) {
                            minEdge = new WeightedEdge(v, w, G.getWeight(v, w));
                        }
                    }
                }
            }

            mst.add(minEdge);
            visited[minEdge.getV()] = true;
            visited[minEdge.getW()] = true;
        }

    }

    public List<WeightedEdge> mst() {
        return mst;
    }

    public static void main(String[] args) {
        WeightedGraph wg = new WeightedGraph("mst.txt");
        Prim prim = new Prim(wg);
        System.out.println("Minimal spanning is: " + prim.mst);
    }
}
