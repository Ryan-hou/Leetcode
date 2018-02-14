package com.leetcode.ryan.personal.learn.graph.genetree;

import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: DenseGraph
 * @date February 13,2018
 */

// 有权稠密图－邻接矩阵
public class DenseGraph<E extends Comparable<E>> implements Graph<E> {

    private int v;
    private int e;
    private boolean directed;
    private Edge[][] g;

    public DenseGraph(int n, boolean directed) {
        v = n;
        e = 0;
        this.directed = directed;
        g = new Edge[n][n];
    }

    @Override
    public int V() { return v; }
    @Override
    public int E() { return e; }

    @Override
    public void addEdge(int v, int w, E weight) {
        assert v >= 0 && v < this.v;
        assert w >= 0 && w < this.v;

        if (hasEdge(v, w)) {
            g[v][w] = null;
            if (!directed) {
                g[w][v] = null;
            }
            e--;
        }

        g[v][w] = new Edge(v, w, weight);
        if (!directed) {
            g[w][v] = new Edge(w, v, weight);
        }
        e++;
    }

    @Override
    public void show() {
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if (g[i][j] != null) {
                    System.out.print(StringUtils.rightPad(g[i][j].wt().toString(), 4, '0') + "\t");
                } else {
                    System.out.print("NULL\t");
                }
            }
            System.out.println();
        }
    }

    @Override
    public Iterator<Edge<E>> iterator(int v) {
        return new AdjIterator(v, this);
    }

    private class AdjIterator implements Iterator<Edge<E>> {

        int v;
        int index;
        DenseGraph graph;

        public AdjIterator(int v, DenseGraph denseGraph) {
            this.v = v;
            this.graph = denseGraph;
            index = -1;
        }

        @Override
        public boolean hasNext() {
            for (index += 1; index < graph.V(); index++) {
                if (graph.g[v][index] != null) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Edge next() {
            return graph.g[v][index];
        }
    }


    private boolean hasEdge(int v, int w) {
        assert v >= 0 && v < this.v;
        assert w >= 0 && w < this.v;
        return g[v][w] != null;
    }
}
