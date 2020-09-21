package com.github.ryan.personal.data_structure.graph.path;

import com.github.ryan.personal.data_structure.graph.genetree.Edge;
import com.github.ryan.personal.data_structure.graph.genetree.Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: BellmanFord
 * @date February 19,2018
 */

// 可以处理负权边，不允许负权环(算法可以判断是否存在负权环)
// 时间复杂度O(EV)

public class BellmanFord<E extends Comparable<E>> {

    /**
     * 如果一个图没有负权环，从一点到另外一点的最短路径，最多经过
     * 所有的V个顶点，有V-1条边；否则，存在顶点经过了两次，既存在
     * 负权环
     *
     * 对一个点的松弛操作，就是找到经过这个点的另外一条路径，多一条
     * 边，权值更小
     *
     * 对所有的点进行V-1次松弛操作
     */

    private Graph g;
    private int s;
    private Object[] distTo;
    private Edge[] from;
    private boolean hasNegativeCycle;

    private boolean detectNegativeCycle() {
        for (int i = 0; i < g.V(); i++) {
            Iterator<Edge> it = g.iterator(i);
            while (it.hasNext()) {
                Edge e = it.next();
                if (from[e.w()] == null
                        //fixme
                        || (Integer)distTo[e.v()] + (Integer)e.wt() < (Integer)distTo[e.w()]) {
                    return true;
                }
            }
        }
        return false;
    }

    public BellmanFord(int s, Graph g) {
        this.s = s;
        this.g = g;

        distTo = new Object[g.V()];
        from = new Edge[g.V()];

        // Bellman-Ford
        distTo[s] = 0; //fixme

        for (int pass = 1; pass < g.V(); pass++) {

            // Relaxation
            for (int i = 0; i < g.V(); i++) {
                Iterator<Edge> it = g.iterator(i);
                while (it.hasNext()) {
                    Edge e = it.next();
                    if (from[e.w()] == null
                            || (Integer) distTo[e.v()] + (Integer) e.wt() < (Integer) distTo[e.w()]) {
                        distTo[e.w()] = (Integer)distTo[e.v()] + (Integer)e.wt();
                        from[e.w()] = e;
                    }
                }
            }
        }

        hasNegativeCycle = detectNegativeCycle();
    }

    public boolean hasNegativeCycle() {
        return hasNegativeCycle;
    }

    public boolean hasPathTo(int w) {
        assert w >= 0 && w < g.V();
        return from[w] != null;
    }

    public E shortestPathTo(int w) {
        assert w >= 0 && w < g.V();
        assert (!hasNegativeCycle);
        return (E) distTo[w];
    }

    public void shortestPath(int w, List<Edge> edgs) {
        assert w >= 0 && w < g.V();
        assert !hasNegativeCycle;

        Deque<Edge> s = new ArrayDeque<>();
        Edge e = from[w];
        while (e.v() != this.s) {
            s.push(e);
            e = from[e.v()];
        }
        s.push(e);

        while (!s.isEmpty()) {
            edgs.add(s.pop());
        }
    }

    public void showPath(int w) {
        assert w >= 0 && w < g.V();
        assert !hasNegativeCycle;

        List<Edge> edges = new ArrayList<>();
        shortestPath(w, edges);

        for (int i = 0; i < edges.size(); i++) {
            System.out.print(edges.get(i).v() + " -> ");
            if (i == edges.size() - 1) {
                System.out.println(edges.get(i).w());
            }
        }
    }
}
