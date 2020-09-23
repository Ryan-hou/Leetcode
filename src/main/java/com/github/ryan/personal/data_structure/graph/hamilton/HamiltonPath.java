package com.github.ryan.personal.data_structure.graph.hamilton;

import com.github.ryan.personal.data_structure.graph.base.Graph;
import com.github.ryan.personal.data_structure.graph.base.UnweightedGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HamiltonPath {

    private Graph G;
    private int[] pre;
    private int end;
    // 汉密尔顿路径起始点
    private int s;

    public HamiltonPath(Graph g, int s) {
        G = g;
        this.s = s;
        // Initialize data
        end = -1;
        pre = new int[G.V()];
        // visited 使用状态压缩，把布尔值数组压缩为一个整型数字
        // 数字的每一个二进制位代表一个顶点, int 数值最多表示 31 个顶点
        // 考虑到汉密尔顿路径求解是一个指数级算法，31 个顶点基本已经够用
        int visited = 0;
        // 使用状态压缩后，可以采用记忆化搜索的方式避免重复计算
        // boolean memo[G.V()][2 ^ G.V()]

        dfs(visited, s, s, G.V());
    }

    // 从顶点 v 开始，在剩余 left 个节点中寻找是否存在汉密尔顿路径
    private boolean dfs(int visited, int v, int parent, int left) {
        visited = (visited | (1 << v));
        pre[v] = parent;
        left--;
        if (left == 0) {
            end = v;
            return true;
        }

        for (int w : G.adj(v)) {
            if ((visited & (1 << w)) == 0) {
                // 顶点 w 还没有被访问
                if (dfs(visited, w, v, left)) {
                    return true;
                }
            }
        }

        // visited 作为函数参数，在函数栈返回时会自动恢复调用前的值
        // visited = (visited | (0 << v));
        // left++
        return false;
    }

    public boolean hasHamiltonPath() {
        return end != -1;
    }

    public List<Integer> hamiltonPath() {
        List<Integer> res = new ArrayList<>();
        if (!hasHamiltonPath()) {
            return res;
        }

        int cur = end;
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        Graph g = new UnweightedGraph("hamilton.txt");
        HamiltonPath hp = new HamiltonPath(g, 1);
        System.out.println(hp.hasHamiltonPath());
        System.out.println("Hamilton path is: " + hp.hamiltonPath());
    }


}
