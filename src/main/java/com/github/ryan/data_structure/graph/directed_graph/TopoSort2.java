package com.github.ryan.data_structure.graph.directed_graph;

import com.github.ryan.data_structure.graph.base.GraphDFS;
import com.github.ryan.data_structure.graph.base.UnweightedGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * 利用 dfs 的后序遍历求解拓扑排序:
 * 对于⼀个节点，遍历完其所有相邻接点之后，再遍历它自身
 * dfs 后序遍历的逆序 -> 拓扑排序的结果
 * 缺点: 不能进行有向图的环检测
 */
public class TopoSort2 {

    private UnweightedGraph g;
    private boolean hasCycle;
    private List<Integer> res;

    public TopoSort2(UnweightedGraph g) {
        if (!g.isDirected()) {
            throw new IllegalArgumentException("Topological sort only works in directed graph.");
        }
        this.g = g;
        res = new ArrayList<>();
        hasCycle = new DirectedCycleDetection(g).hasCycle();
        if (hasCycle) {
            return;
        }

        GraphDFS dfs = new GraphDFS(g);
        List<Integer> post = dfs.post();
        // 有向无环图 DAG dfs 的后序结果的逆序即为拓扑排序的解
        for (int i = post.size() - 1; i >= 0; i--) {
            res.add(post.get(i));
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public List<Integer> result() {
        return res;
    }

    public static void main(String[] args) {
        UnweightedGraph g = new UnweightedGraph("directed_cycle_detection.txt", true);
        TopoSort2 topoSort = new TopoSort2(g);
        System.out.println("Topological sort is: " + topoSort.result());
    }
}
