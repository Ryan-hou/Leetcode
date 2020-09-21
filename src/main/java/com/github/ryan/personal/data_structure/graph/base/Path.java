package com.github.ryan.personal.data_structure.graph.base;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Path
 * @date February 12,2018
 */
public class Path {

    private Graph graph;
    private int s; // source,出发点
    boolean[] visited;
    int[] from;

    public Path(int s, Graph graph) {
        assert s >= 0 && s < graph.V();

        visited = new boolean[graph.V()];
        from = new int[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            from[i] = -1;
        }
        this.s = s;
        this.graph = graph;

        // 寻路算法（不一定是最短路径）
        dfs(s);
    }

    private void dfs(int v) {
        visited[v] = true;

        Iterator<Integer> it = graph.iterator(v);
        while (it.hasNext()) {
            int i = it.next();
            if (!visited[i]) {
                from[i] = v;
                dfs(i);
            }
        }
    }

    public boolean hasPath(int w) {
        assert w >= 0 && w < graph.V();
        return visited[w];
    }

    public void path(int w, List<Integer> list) {
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
