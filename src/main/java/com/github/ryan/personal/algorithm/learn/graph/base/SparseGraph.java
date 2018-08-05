package com.github.ryan.personal.algorithm.learn.graph.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: SparseGraph
 * @date February 12,2018
 */

// 稀疏图－邻接表
public class SparseGraph implements Graph {

    private int v;
    private int e;
    private boolean directed;
    // 每个顶点与其所有相连的顶点构成图
    private List<List<Integer>> g;

    public SparseGraph(int n, boolean directed) {
        v = n;
        e = 0;
        this.directed = directed;
        g = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
    }

    @Override
    public int V() { return v; }
    public int E() { return e; }

    // 在两个顶点之间增加一条边
    // 不处理平行边
    @Override
    public void addEdge(int v, int w) {
        assert v >= 0 && v < this.v;
        assert w >= 0 && w < this.v;

        g.get(v).add(w);
        if (v != w && !directed) {
            g.get(w).add(v);
        }

        e++;
    }

    private boolean hasEdge(int v, int w) {
        assert v >= 0 && v < this.v;
        assert w >= 0 && w < this.v;

        for (int i = 0; i < g.get(v).size(); i++) {
            if (g.get(v).get(i) == w) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void show() {
        for (int i = 0; i < v; i++) {
            System.out.print("Vertex " + i + ":" + "\t");
            for (int j = 0; j < g.get(i).size(); j++) {
                System.out.print(g.get(i).get(j) + "\t");
            }
            System.out.println();
        }
    }

    @Override
    public Iterator<Integer> iterator(int v) {
        return new AdjIterator(v, this);
    }

    // 邻边迭代器
    private class AdjIterator implements Iterator<Integer> {

        int v;
        int index;
        SparseGraph graph;

        public AdjIterator(int v, SparseGraph graph) {
            this.v = v;
            this.graph = graph;
            this.index = -1;
        }

        @Override
        public boolean hasNext() {
            index++;
            if (index < graph.g.get(v).size()) {
                return true;
            }
            return false;
        }

        @Override
        public Integer next() {
            return graph.g.get(v).get(index);
        }
    }
}
