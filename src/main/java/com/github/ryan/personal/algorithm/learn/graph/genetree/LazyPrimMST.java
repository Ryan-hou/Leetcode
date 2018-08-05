package com.github.ryan.personal.algorithm.learn.graph.genetree;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: LazyPrimMST
 * @date February 13,2018
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 最小生成树: Minimum Span Tree
 * 使用切分定理（Cut property: 反证法可以证明定理的正确性）：
 * 把图中的节点分成两部分，成为一个切分
 * 如果一个边的两个端点属于切分(cut)不同的两边，这个边成为横切边(Crossing Edge)
 * 切分定理：
 * 给定任意切分，横切边中权值最小的边必然属于最小生成树
 *
 */
public class LazyPrimMST<E extends Comparable<E>> {

    private Graph g;
    PriorityQueue<Edge<E>> pq; // 使用最小堆性质
    boolean[] marked;
    E mstWeight;
    List<Edge<E>> mst;

    private void visit(int v) {
        assert (!marked[v]);

        marked[v] = true;
        Iterator<Edge<E>> it = g.iterator(v);
        while (it.hasNext()) {
            Edge<E> edge = it.next();
            if (!marked[edge.other(v)]) {
                pq.add(edge);
            }
        }
    }

    public LazyPrimMST(Graph g) {
        this.g = g;
        marked = new boolean[g.V()];
        pq = new PriorityQueue<>(g.E());
        mst = new ArrayList<>();
        mst.clear();

        // Lazy prim: 时间复杂度O(ElogE)
        visit(0);
        while (!pq.isEmpty()) {
           // 最小堆：取权值最小的边
           Edge<E> e = pq.poll();
            if (marked[e.v()] == marked[e.w()]) {
                continue;
            }

            mst.add(e);
            if (!marked[e.v()]) {
                visit(e.v());
            } else {
                visit(e.w());
            }
        }

        //fixme
        Double weight = 0.0;
        for (int i = 0; i < mst.size(); i++) {
            weight += (Double)mst.get(i).wt();
        }
        mstWeight = (E) weight;
    }

    public List<Edge<E>> mstEdges() {
        return mst;
    }

    public E result() {
        return mstWeight;
    }
}
