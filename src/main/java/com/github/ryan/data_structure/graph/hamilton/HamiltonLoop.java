package com.github.ryan.data_structure.graph.hamilton;

import com.github.ryan.data_structure.graph.base.Graph;
import com.github.ryan.data_structure.graph.base.UnweightedGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HamiltonLoop {

    private Graph G;
    private boolean[] visited;
    // 用于求解具体的汉密尔顿回路
    private int[] pre;
    private int end;

    public HamiltonLoop(Graph g) {
        this.G = g;
        end = -1;
        visited = new boolean[G.V()];
        pre = new int[G.V()];

        int left = G.V();
        // 存在汉密尔顿回路则从任意顶点开始都能找到该回路，这里从顶点 0 开始寻找
        dfs(0, 0, left);
    }

    // 从顶点 V 开始，在剩余没有访问的 left 个顶点中是否存在汉密尔顿回路
    private boolean dfs(int v, int parent, int left) {
        visited[v] = true;
        pre[v] = parent;
        left--;
        if (left == 0 && G.hasEdge(v, 0)) {
            end = v;
            return true;
        }

        for (int w : G.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v, left)) {
                    pre[w] = v;
                    return true;
                }
            }
        }

        // backtracking
        visited[v] = false;
        // left++; // 函数栈在返回时，上一层的函数参数 left 值会自动恢复
        return false;
    }

    public boolean hasHamiltonLoop() {
        return end != -1;
    }

    public List<Integer> hamiltonLoop() {
        List<Integer> res = new ArrayList<>();
        if (!hasHamiltonLoop()) {
            return res;
        }

        res.add(0);
        int cur = end;
        while (cur != 0) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(0);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph g = new UnweightedGraph("hamilton.txt");
        HamiltonLoop hl = new HamiltonLoop(g);
        System.out.println(hl.hasHamiltonLoop());
        System.out.println("Hamilton loop is: " + hl.hamiltonLoop());
    }
}
