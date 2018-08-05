package com.github.ryan.personal.algorithm.learn.graph.path;

import com.github.ryan.personal.algorithm.learn.graph.genetree.Edge;
import com.github.ryan.personal.algorithm.learn.graph.genetree.Graph;
import com.github.ryan.personal.algorithm.learn.heap.IndexMinHeap;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Dijkstra
 * @date February 14,2018
 */

// 图中不能存在负权边，使用最小索引堆，时间复杂度为O(ElogV)
public class Dijkstra<E extends Comparable<E>> {

    private Graph g;
    private int s;
    Object[] distTo;
    boolean[] marked;
    Edge[] from;

    public Dijkstra(int s, Graph g) {
        this.s = s;
        this.g = g;
        distTo = new Object[g.V()];
        marked = new boolean[g.V()];
        from = new Edge[g.V()];

        IndexMinHeap<E> ipq = new IndexMinHeap<>(g.V());

        // start dijkstra
        distTo[s] = 0; // fixme
        ipq.insert(s, (E) distTo[s]);
        marked[s] = true;
        while (!ipq.isEmpty()) {
            int v = ipq.extractMinIndex();

            // distTo[v] 就是s到v的最短距离
            marked[v] = true;
            // 松弛操作
            Iterator<Edge> it = g.iterator(v);
            while (it.hasNext()) {
                Edge e = it.next();
                int w = e.other(v);
                if (!marked[w]) {
                    //fixme
                    if (from[w] == null || ((Integer)distTo[v] + (Integer) e.wt()) < (Integer) distTo[w]) {
                        distTo[w] = (Integer)distTo[v] + (Integer) e.wt();
                        from[w] = e;
                        if (ipq.contain(w)) {
                            ipq.change(w, (E)distTo[w]);
                        } else {
                            ipq.insert(w, (E) distTo[w]);
                        }
                    }
                }
            }
        }


    }


    public E shortestPathTo(int w) {
        assert w >= 0 && w < g.V();
        return (E) distTo[w];
    }

    public boolean hasPathTo(int w) {
        assert w >= 0 && w < g.V();
        return marked[w];
    }

    public void shortestPath(int w, List<Edge<E>> edges) {
        assert w >= 0 && w < g.V();

        Deque<Edge<E>> s = new ArrayDeque<>();
        Edge edge = from[w];
        while (edge.v() != this.s) {
            s.push(edge);
            edge = from[edge.v()];
        }
        s.push(edge);

        while (!s.isEmpty()) {
            edges.add(s.pop());
        }
    }

    public void showPath(int w) {
        assert w >= 0 && w < g.V();

        List<Edge<E>> edges = new ArrayList<>();
        shortestPath(w, edges);

        for (int i = 0; i < edges.size(); i++) {
            System.out.print(edges.get(i).v() + " -> ");
            if (i == edges.size() - 1) {
                System.out.println(edges.get(i).w());
            }
        }
    }

}
