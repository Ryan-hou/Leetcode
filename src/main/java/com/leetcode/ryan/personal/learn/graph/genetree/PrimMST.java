package com.leetcode.ryan.personal.learn.graph.genetree;

import com.leetcode.ryan.personal.learn.heap.IndexMinHeap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: PrimMST
 * @date February 13,2018
 */

// 时间复杂度：O(ElogV)
public class PrimMST<E extends Comparable<E>> {

    private Graph graph;
    private List<Edge<E>> mst;

    private boolean[] marked;
    private IndexMinHeap<E> ipq;
    Edge[] edgeTo;
    private E mstWeight;

    // assume graph is connected
    public PrimMST(Graph graph) {
        assert graph.E() >= 1;

        this.graph = graph;
        marked = new boolean[graph.V()];
        edgeTo = new Edge[graph.V()];
        mst = new ArrayList<>();
        ipq = new IndexMinHeap<>(graph.V());
        mst.clear();

        visit(0);
        while (!ipq.isEmpty()) {
            int v = ipq.extractMinIndex();
            assert edgeTo[v] != null;
            mst.add(edgeTo[v]);
            visit(v);
        }

        // fixme
        Double weight = 0.0;
        for (int i = 0; i < mst.size(); i++) {
            weight += (Double) mst.get(i).wt();
        }
        mstWeight = (E) weight;
    }

    private void visit(int v) {
        assert (!marked[v]);
        marked[v] = true;

        Iterator<Edge> it = graph.iterator(v);
        while (it.hasNext()) {
            Edge e = it.next();
            int w = e.other(v);
            if (!marked[w]) {
                if (edgeTo[w] == null) {
                    edgeTo[w] = e;
                    ipq.insert(w, (E) e.wt());
                } else if ((e.wt()).compareTo(edgeTo[w].wt()) < 0) {
                    edgeTo[w] = e;
                    ipq.change(w, (E) e.wt());
                }
            }

        }
    }

    public List<Edge<E>> mstEdges() {
        return mst;
    }

    public E result() {
        return mstWeight;
    }

}
