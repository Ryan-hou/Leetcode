package com.github.ryan.data_structure.graph.mst;

import com.github.ryan.data_structure.graph.base.dfs.CC;
import com.github.ryan.data_structure.graph.base.WeightedEdge;
import com.github.ryan.data_structure.graph.base.WeightedGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 使用优先队列优化 Prim 算法
 * 时间复杂度 O(ElogE)
 * TODO: 借助索引堆进一步优化到 O(ElogV)
 */
public class Prim2 {

    private WeightedGraph G;
    private List<WeightedEdge> mst;

    public Prim2(WeightedGraph G) {
        this.G = G;
        mst = new ArrayList<>();

        CC cc = new CC(G);
        if (cc.count() > 1) {
            return;
        }

        // Prim
        boolean[] visited = new boolean[G.V()]; // 用于区分切分
        // min heap
        Queue<WeightedEdge> pq = new PriorityQueue<>();
        visited[0] = true;
        for (int w : G.adj(0)) {
            pq.offer(new WeightedEdge(0, w, G.getWeight(0, w)));
        }

        while (!pq.isEmpty()) {
            // 切分变化后，存在无效的横切边
            WeightedEdge edge = pq.poll();
            if (visited[edge.getV()] && visited[edge.getW()]) {
                continue;
            }

            mst.add(edge);
            int newv = visited[edge.getV()] ? edge.getW() : edge.getV();
            visited[newv] = true;
            // 新增横切边
            for (int w : G.adj(newv)) {
                if (!visited[w]) {
                    pq.offer(new WeightedEdge(newv, w, G.getWeight(newv, w)));
                }
            }
        }
    }

    public List<WeightedEdge> mst() {
        return mst;
    }

    public static void main(String[] args) {
        WeightedGraph wg = new WeightedGraph("mst.txt");
        Prim2 prim2 = new Prim2(wg);
        System.out.println("Minimal spanning tree is: " + prim2.mst());
    }
}
