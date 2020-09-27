package com.github.ryan.data_structure.graph.euler;

import com.github.ryan.data_structure.graph.base.UnweightedGraph;

import java.util.*;

public class DirectedEulerLoop {

    private UnweightedGraph g;

    public DirectedEulerLoop(UnweightedGraph g) {
        if (!g.isDirected()) {
            throw new RuntimeException("DirectedEulerLoop only works in directed graph.");
        }
        this.g = g;
    }

    public boolean hasEulerLoop() {
        // todo: 有向图连通性判断

        for (int v = 0; v < g.V(); v++) {
            if (g.indegree(v) != g.outdegree(v)) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> eulerLoop() {
        List<Integer> res = new ArrayList<>();
        if (!hasEulerLoop()) {
            return res;
        }

        UnweightedGraph ug = (UnweightedGraph) g.clone();

        Deque<Integer> stack = new ArrayDeque<>();
        int curv = 0;
        stack.push(curv);
        while (!stack.isEmpty()) {
            if (ug.outdegree(curv) != 0) {
                stack.push(curv);
                int w = ug.adj(curv).iterator().next();
                ug.removeEdge(curv, w);
                curv = w;
            } else {
                res.add(curv);
                curv = stack.pop();
            }
        }

        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        UnweightedGraph ug = new UnweightedGraph("directed_euler_loop.txt", true);
        DirectedEulerLoop del = new DirectedEulerLoop(ug);
        System.out.println("Directed euler loop: " + del.eulerLoop());
    }
}
