package com.github.ryan.data_structure.graph.base;

public interface Graph {

    /**
     * 图的顶点数
     * @return
     */
    int V();

    /**
     * 图的边数
     * @return
     */
    int E();

    /**
     * 顶点 v,w 是否存在边
     * @param v
     * @param w
     * @return
     */
    boolean hasEdge(int v, int w);

    /**
     * 删除顶点 v,w 的边
     * @param v
     * @param w
     */
    void removeEdge(int v, int w);

    /**
     * 顶点 v 的相邻顶点
     * @param v
     * @return
     */
    Iterable<Integer> adj(int v);

    /**
     * 顶点 v 的度
     * @param v
     * @return
     */
    int degree(int v);

    /**
     * 顶点 v 的入度
     * @param v
     * @return
     */
    int indegree(int v);

    /**
     * 顶点 v 的出度
     * @param v
     * @return
     */
    int outdegree(int v);

    /**
     * 是否有向
     * @return
     */
    boolean isDirected();

}
