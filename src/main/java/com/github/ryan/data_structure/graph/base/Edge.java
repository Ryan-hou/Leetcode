package com.github.ryan.data_structure.graph.base;

public class Edge {

    private int v, w;

    public Edge(int v, int w) {
        this.v = v;
        this.w = w;
    }

    public int V() {
        return v;
    }

    public int W() {
        return w;
    }

    @Override
    public String toString() {
        return String.format("%d-%d", v, w);
    }
}
