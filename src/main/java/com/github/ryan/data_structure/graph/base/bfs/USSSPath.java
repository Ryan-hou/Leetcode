package com.github.ryan.data_structure.graph.base.bfs;

import com.github.ryan.data_structure.graph.base.GraphUtil;
import com.github.ryan.data_structure.graph.base.UnweightedGraph;

import java.util.*;

/**
 * 无权图单源最短路径求解 -> BFS
 * Unweighted Single Source Shortest Path
 */
public class USSSPath {

    private UnweightedGraph g;
    private int s;

    private boolean[] visited;
    private int[] pre;
    private int[] dis;

    public USSSPath(UnweightedGraph g, int s) {
        GraphUtil.validateVertex(g, s);
        this.g = g;
        this.s = s;

        visited = new boolean[g.V()];
        pre = new int[g.V()];
        dis = new int[g.V()];
        Arrays.fill(pre, -1);
        Arrays.fill(dis, -1);

        bfs(s);
    }

    /**
     * 从顶点 v 开始 bfs
     * @param s
     */
    private void bfs(int s) {
        visited[s] = true;
        pre[s] = s;
        dis[s] = 0;

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(s);
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int w : g.adj(v)) {
                if (!visited[w]) {
                    visited[w] = true;
                    pre[w] = v;
                    dis[w] = dis[v] + 1;
                    q.offer(w);
                }
            }
        }
    }

    public boolean isConnectedTo(int t) {
        GraphUtil.validateVertex(g, t);
        return visited[t];
    }

    public int disTo(int t) {
        GraphUtil.validateVertex(g, t);
        return dis[t];
    }

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
        UnweightedGraph g = new UnweightedGraph("ussspath.txt");
        USSSPath usssPath = new USSSPath(g, 0);
        for (int v = 0; v < g.V(); v++) {
            System.out.printf(usssPath.disTo(v) + " ");
        }
        System.out.println();
        System.out.println("0 -> 5's shortest distance is: " + usssPath.disTo(5));
        System.out.println("0 -> 5's path is: " + usssPath.pathTo(5));
    }

}
