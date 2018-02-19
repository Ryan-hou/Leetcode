package com.leetcode.ryan.personal.learn.graph.genetree;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Edge
 * @date February 13,2018
 */
public class Edge<E extends Comparable<E>> implements Comparable<Edge> {

    private int a;
    private int b;
    private E weight;

    public Edge() {
    }

    public Edge(int a, int b, E weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }

    public int v() {
        return a;
    }

    public int w() {
        return b;
    }

    public E wt() {
        return weight;
    }

    public int other(int x) {
        assert x == a || x == b;
        return x == a ? b : a;
    }

    @Override
    public String toString() {
        return a + "-" + b + ": " + weight;
    }

    // 用于构造最小堆
    @Override
    public int compareTo(Edge o) {
        return this.weight.compareTo((E) o.weight);
    }
}
