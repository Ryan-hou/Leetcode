package com.leetcode.ryan.personal.learn.graph;

import java.util.Iterator;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: DenseGraph
 * @date February 12,2018
 */

// 稠密图 － 邻接矩阵
public class DenseGraph implements Graph {

    private int v;
    private int e;
    private boolean directed;
    boolean[][] g;

    public DenseGraph(int n, boolean directed) {
        v = n;
        e = 0;
        this.directed = directed;
        g = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            boolean[] vArray = new boolean[n];
            g[i] = vArray;
        }
    }

    public int V() { return v; }
    public int E() { return e; }

    @Override
    public void addEdge(int v, int w) {
        assert v >= 0 && v < this.v;
        assert w >= 0 && w < this.v;
        if (hasEdge(v, v)) {
            return;
        }

        g[v][w] = true;
        if (!directed) {
            g[w][v] = true;
        }

        e++;
    }

    private boolean hasEdge(int v, int w) {
        assert v >= 0 && v < this.v;
        assert w >= 0 && w < this.v;
        return g[v][w];
    }

    @Override
    public Iterator<Integer> iterator(int v) {
        return new AdjIterator(v, this);
    }

    private class AdjIterator implements Iterator<Integer> {

        int v;
        int index;
        DenseGraph graph;

        public AdjIterator(int v, DenseGraph graph) {
            this.v = v;
            this.graph = graph;
            index = -1;
        }

        @Override
        public boolean hasNext() {
            for (index += 1; index < graph.V(); index++) {
                if (graph.g[v][index]) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Integer next() {
            return index;
        }
    }

}
