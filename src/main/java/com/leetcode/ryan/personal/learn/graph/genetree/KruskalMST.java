package com.leetcode.ryan.personal.learn.graph.genetree;

import com.leetcode.ryan.personal.learn.unionfind.UnionFind5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: KruskalMST
 * @date February 14,2018
 */

// 时间复杂度: O(ElogE)
public class KruskalMST<E extends Comparable<E>> {

    private List<Edge<E>> mst;
    private E mstWeight;

    public KruskalMST(Graph graph) {
        PriorityQueue<Edge<E>> pq = new PriorityQueue<>(graph.E());
        mst = new ArrayList<>();

        for (int i = 0 ; i < graph.V(); i++) {
            Iterator<Edge<E>> it = graph.iterator(i);
            while (it.hasNext()) {
                Edge<E> edge = it.next();
                if (edge.v() < edge.w()) {
                   // 去除多余的边
                    pq.add(edge);
                }
            }
        }

        UnionFind5 uf = new UnionFind5(graph.V());
        while (!pq.isEmpty() && mst.size() < graph.V() - 1) {
            Edge<E> e = pq.poll();
            if (uf.isConnected(e.v(), e.w())) {
                continue;
            }

            mst.add(e);
            uf.union(e.v(), e.w());
        }

        // fixme
        Double weight = 0.0;
        for (int i = 0; i < mst.size(); i++) {
            weight += (Double) mst.get(i).wt();
        }
        mstWeight = (E) weight;
    }


    public E result() {
        return mstWeight;
    }

    public List<Edge<E>> mstEdges() {
        return mst;
    }
}
