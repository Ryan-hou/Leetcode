package com.leetcode.ryan.personal.learn.graph.genetree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: SparseGraph
 * @date February 13,2018
 */

// 有权稀疏图－邻接表
public class SparseGraph<E extends Comparable<E>> implements Graph<E> {

    private int v;
    private int e;
    private boolean directed;
    List<List<Edge<E>>> g;

    public SparseGraph(int n, boolean directed) {
        v = n;
        e = 0;
        this.directed = directed;
        g = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            g.add(new ArrayList<>());
        }
    }


    @Override
    public void addEdge(int v, int w, E weight) {
        assert v >= 0 && v < this.v;
        assert w >= 0 && w < this.v;

        // 不处理平行边
        g.get(v).add(new Edge<>(v, w, weight));
        if (v != w && !directed) {
            g.get(w).add(new Edge<>(w, v, weight));
        }
        e++;
    }


    @Override
    public Iterator<Edge<E>> iterator(int v) {
        return new AdjIterator(v);
    }

    // 邻边迭代器
    private class AdjIterator implements Iterator<Edge<E>> {

        int v;
        int index;

        public AdjIterator(int v) {
            this.v = v;
            index = -1;
        }

        @Override
        public boolean hasNext() {
            index++;
            if (index < g.get(v).size()) {
                return true;
            }
            return false;
        }

        @Override
        public Edge<E> next() {
            return g.get(v).get(index);
        }
    }

    @Override
    public void show() {
        for (int i = 0; i < v; i++) {
            System.out.print("Vertex " + i + ": \t");
            for (int j = 0; j < g.get(i).size(); j++) {
                System.out.print("( to: " + g.get(i).get(j).w() + ",wt:"
                        + g.get(i).get(j).wt() + " )\t");
            }
            System.out.println();
        }
    }

    public boolean hasEdge(int v, int w) {
        assert v >= 0 && v < this.v;
        assert w >= 0 && w < this.v;
        for (int i = 0; i < g.get(v).size(); i++) {
            if (g.get(v).get(i).other(v) == w) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int V() {
        return v;
    }

    @Override
    public int E() {
        return e;
    }
}
