package com.github.ryan.data_structure.graph.base.dfs;

import com.github.ryan.data_structure.graph.base.GraphUtil;
import com.github.ryan.data_structure.graph.base.UnweightedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 单源路径求解:
 * 两点在同一个连通分量，意味着两点间有路径
 * 路径怎么求？ -> 遍历图的过程中记录额外信息&利用递归返回值
 */
public class SingleSourcePath {

    private UnweightedGraph g;
    /**
     * pre[v] == -1 表示顶点 v 还未被访问
     * 除此外，pre[v] 表示遍历过程中 v 的前一个顶点为 pre[v]
     */
    private int[] pre;

    /**
     * 单源路径的源顶点
     */
    private int s;

    public SingleSourcePath(UnweightedGraph g, int s) {
        this.g = g;
        GraphUtil.validateVertex(g, s);

        pre = new int[g.V()];
        Arrays.fill(pre, -1);

        dfs(s, s);
    }

    /**
     * 从顶点v开始dfs遍历相邻顶点，parent 为顶点v的父亲节点
     * @param v
     * @param parent
     */
    private void dfs(int v, int parent) {
        pre[v] = parent;
        for (int w : g.adj(v)) {
            if (pre[w] == -1) {
                dfs(w, v);
            }
        }
    }

    public boolean isConnectedTo(int t) {
        GraphUtil.validateVertex(g, t);
        return pre[t] != -1;
    }

    /**
     * 求解源点 s 到顶点 t 的路径
     * @param t
     * @return
     */
    public Iterable<Integer> pathTo(int t) {
        List<Integer> res = new ArrayList<>();
        if (!isConnectedTo(t)) {
            return res;
        }

        int cur = t;
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        UnweightedGraph g = new UnweightedGraph("path.txt");
        SingleSourcePath ssp = new SingleSourcePath(g, 0);
        System.out.println("0 -> 6: " + ssp.pathTo(6));
        System.out.println("0 is connected to 5? " + ssp.isConnectedTo(5));
    }
}
