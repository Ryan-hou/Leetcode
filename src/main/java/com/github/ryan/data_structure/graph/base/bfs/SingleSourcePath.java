package com.github.ryan.data_structure.graph.base.bfs;

import com.github.ryan.data_structure.graph.base.GraphUtil;
import com.github.ryan.data_structure.graph.base.UnweightedGraph;

import java.util.*;

/**
 * 单源路径求解
 */
public class SingleSourcePath {

    private UnweightedGraph g;
    private boolean[] visited;
    private int[] pre;
    private int s;

    public SingleSourcePath(UnweightedGraph g, int s) {
        this.g = g;
        GraphUtil.validateVertex(g, s);
        this.s = s;

        visited = new boolean[g.V()];
        pre = new int[g.V()];
        Arrays.fill(pre, -1);

        bfs(s);
    }

    /**
     *  从顶点 s 开始 bfs，并记录路径数据
     * @param s
     */
    private void bfs(int s) {
        visited[s] = true;
        pre[s] = s;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(s);
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int w : g.adj(v)) {
                if (!visited[w]) {
                    visited[w] = true;
                    pre[w] = v;
                    q.offer(w);
                }
            }
        }
    }

    public boolean isConnectedTo(int t) {
        GraphUtil.validateVertex(g, t);
        return visited[t];
    }

    public List<Integer> pathTo(int t) {
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
