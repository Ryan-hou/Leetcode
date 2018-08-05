package com.github.ryan.personal.algorithm.learn.graph.base;

import java.util.Iterator;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Graph
 * @date February 12,2018
 */
public interface Graph {

    // 新增顶点 v 到 顶点 w 的边
    void addEdge(int v, int w);

    // 获取每个顶点对应的邻边迭代器
    Iterator<Integer> iterator(int v);

    void show();

    int V();

}
