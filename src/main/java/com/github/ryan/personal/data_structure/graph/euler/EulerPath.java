package com.github.ryan.personal.data_structure.graph.euler;

import com.github.ryan.personal.data_structure.graph.base.CC;
import com.github.ryan.personal.data_structure.graph.base.UnweightedGraph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class EulerPath {

    private UnweightedGraph g;

    /**
     * 起始顶点
     */
    private int startV = 0;
    /**
     * 顶点的度为奇数的个数
     */
    private int oddCount;

    public EulerPath(UnweightedGraph g) {
        this.g = g;
    }

    /**
     * 对于无向连通图，至多存在两个节点(起始和终止节点)的度为奇数是该图存在欧拉路径的充要条件
     * @return
     */
    public boolean hasEulerPath() {
        // reset oddCount
        oddCount = 0;
        CC cc = new CC(g);
        if (cc.count() > 1) {
            return false;
        }

        for (int v = 0; v < g.V(); v++) {
            if ((g.degree(v) & 1) == 1) {
                oddCount++;
                startV = v;
            }
        }
        return oddCount == 0 || oddCount == 2;
    }

    // Hierhozer 算法
    public List<Integer> eulerPath() {
        List<Integer> res = new ArrayList<>();
        if (!hasEulerPath()) {
            return res;
        }

        UnweightedGraph cloned = (UnweightedGraph) g.clone();
        Deque<Integer> stack = new ArrayDeque<>();
        int curv = startV;
        stack.push(curv);
        while (!stack.isEmpty()) {
            if (cloned.degree(curv) != 0) {
                stack.push(curv);
                int w = cloned.adj(curv).iterator().next();
                cloned.removeEdge(curv, w);
                curv = w;
            } else {
                res.add(curv);
                curv = stack.pop();
            }
        }

        if (oddCount == 0) {
            // 存在欧拉回路
            res.remove(res.size() - 1);
        }
        return res;
    }

    public static void main(String[] args) {
        // 测试数据为在 euler_loop.txt 的基础上删除顶点 0,1 之间的边构成的图
        UnweightedGraph ug = new UnweightedGraph("euler_path.txt");
        EulerPath ep = new EulerPath(ug);
        System.out.println(ep.hasEulerPath());
        System.out.println("Euler Path: " + ep.eulerPath());
    }
}
