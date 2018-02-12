package com.leetcode.ryan.personal.learn.graph;

import java.util.Iterator;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Component
 * @date February 12,2018
 */

/**
 * 图的dfs复杂度：
 * 稀疏图(邻接表)：O(V+E)
 * 稠密图(邻接矩阵)：O(V^2)
 */
// 使用图的DFS获取图的联通分量个数(还可以使用DFS判断有向图是否存在环)
public class Component {

    private Graph graph;
    private boolean[] visited;
    private int ccount;
    // 判断图中两个节点是否相连(类似并查集的思路)
    private int[] id;

    public Component(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.V()];
        id = new int[graph.V()];
        ccount = 0;

        for (int i = 0; i < graph.V(); i++) {
            if (!visited[i]) {
                dfs(i);
                ccount++;
            }
        }
    }

    // 使用递归实现DFS，也可以使用栈模拟递归过程
    private void dfs(int v) {
        visited[v] = true;
        id[v] = ccount;
        Iterator<Integer> it = graph.iterator(v);
        while (it.hasNext()) {
            int i = it.next();
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

    public int count() {
        return ccount;
    }

    public boolean isConnected(int v, int w) {
        assert v >= 0 && v < graph.V();
        assert w >= 0 && w < graph.V();
        return id[v] == id[w];
    }

}
