package com.github.ryan.personal.algorithm.learn.graph.base;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: ShortestPath
 * @date February 12,2018
 */

/**
 * BFS遍历图时间复杂度：
 * 稀疏图(邻接表)：O(V+E)
 * 稠密图（邻接矩阵）：O(V^2)
 */
// 使用BFS求最短路径
public class ShortestPath {

    private Graph graph;
    private int source;
    private boolean[] visited;
    private int[] from;
    private int[] ord;

    public ShortestPath(int s, Graph graph) {
        assert s >= 0 && s < graph.V();

        visited = new boolean[graph.V()];
        from = new int[graph.V()];
        ord = new int[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            from[i] = -1;
            ord[i] = -1;
        }
        this.source = s;
        this.graph = graph;

        Deque<Integer> q = new ArrayDeque<>();
        // 无权图最短路径求法
        q.add(s);
        visited[s] = true;
        ord[s] = 0;
        while (!q.isEmpty()) {
            int v = q.poll();
            Iterator<Integer> it = graph.iterator(v);
            while (it.hasNext()) {
                int i = it.next();
                if (!visited[i]) {
                    q.add(i);
                    visited[i] = true;
                    from[i] = v;
                    ord[i] = ord[v] + 1;
                }
            }
        }
    }

    public int length(int w) {
        assert w >= 0 && w < graph.V();
        return ord[w];
    }

    public boolean hasPath(int w) {
        assert w >= 0 && w < graph.V();
        return visited[w];
    }

    public void path(int w, List<Integer> list) {
        assert w >= 0 && w < graph.V();

        Deque<Integer> s = new ArrayDeque<>();
        int p = w;
        while (p != -1) {
            s.push(p);
            p = from[p];
        }

        list.clear();
        while (!s.isEmpty()) {
            list.add(s.pop());
        }
    }

    public void showPath(int w) {
        assert w >= 0 && w < graph.V();

        List<Integer> path = new ArrayList<>();
        path(w, path);
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i == path.size() - 1) {
                System.out.println();
            } else {
                System.out.print(" -> ");
            }
        }
    }

}
