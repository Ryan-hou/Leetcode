package com.github.ryan.personal.algorithm.learn.graph.genetree;

import java.util.Iterator;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Graph
 * @date February 13,2018
 */
public interface Graph<E extends Comparable<E>> {

    // 新增顶点 v 到 顶点 w 的边
    void addEdge(int v, int w, E weight);

    // 获取每个顶点对应的邻边迭代器
    Iterator<Edge<E>> iterator(int v);

    void show();

    int V();

    int E();
}
