package com.github.ryan.personal.data_structure.graph.euler;

import com.github.ryan.personal.data_structure.graph.base.CC;
import com.github.ryan.personal.data_structure.graph.base.UnweightedGraph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class EulerLoop {

    private UnweightedGraph G;

    public EulerLoop(UnweightedGraph g) {
        this.G = g;
    }

    /**
     * 使用连通图存在欧拉路径的充要条件进行判定: 每个顶点的度为偶数
     * @return
     */
    public boolean hasEulerLoop() {
        CC cc = new CC(G);
        if (cc.count() > 1) {
            return false;
        }

        for (int v = 0; v < G.V(); v++) {
            if ((G.degree(v) & 1) == 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * 求解欧拉回路: 使用 Hierhozer 算法，时间复杂度 O(2E) -》O(E), 复杂度只和图的边有关
     * 算法实现思路与证明连通图的顶点的度都为偶数则存在欧拉回路的构造性证明类似
     * @return
     */
    public List<Integer> eulerLoop() {
        List<Integer> res = new ArrayList<>();
        if (!hasEulerLoop()) {
            return res;
        }

        UnweightedGraph g = (UnweightedGraph) G.clone();
        Deque<Integer> stack = new ArrayDeque<>();
        int curv = 0;
        stack.push(curv);
        while (!stack.isEmpty()) {
            if (g.degree(curv) != 0) {
                stack.push(curv);
                int w = g.adj(curv).iterator().next();
                g.removeEdge(curv, w);
                curv = w;
            } else {
                res.add(curv);
                curv = stack.pop();
            }
        }

        return res;
    }

    public static void main(String[] args) {
        UnweightedGraph g = new UnweightedGraph("euler.txt");
        EulerLoop el = new EulerLoop(g);
        System.out.println(el.hasEulerLoop());
        System.out.println("Euler loop: " + el.eulerLoop());
    }
}
