package com.leetcode.ryan.personal.learn.unionfind;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: UnionFind
 * @date February 11,2018
 */

/**
 * 并查集：可以高效的解决连接问题，相比路径(最短路径)问题，回答的问题更少，
 * 因而可以更加高效
 */
public interface UnionFind {

    // 查询p节点对应的值
    int find(int p);

    // 判断p,q节点是否连接
    boolean isConnected(int p, int q);

    // 连接p,q 节点
    void union(int p, int q);

}
