package com.github.ryan.data_structure.graph.base.dfs;

import com.github.ryan.data_structure.graph.base.UnweightedGraph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class GraphDFS {

    private UnweightedGraph ug;
    private boolean[] visited;

    // dfs 的先序遍历结果
    private List<Integer> pre = new ArrayList<>();
    // dfs 的后序遍历结果
    private List<Integer> post = new ArrayList<>();

    /**
     * 有向图&无向图均支持
     * @param ug
     */
    public GraphDFS(UnweightedGraph ug) {
        this.ug = ug;
        visited = new boolean[ug.V()];

        for (int v = 0; v < ug.V(); v++) {
            if (!visited[v]) {
                dfs(v);
            }
        }
    }

    // 从顶点v开始深度优先遍历
    private void dfs(int v) {
        visited[v] = true;
        // dfs 先序遍历结果
        pre.add(v);
        for (int w : ug.adj(v)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
        // dfs 后序遍历结果
        post.add(v);
    }

    /**
     * 使用栈通过迭代的方式实现dfs
     * @param v
     */
    private void dfs1(int v) {
        visited[v] = true;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(v);
        pre.add(v);
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            for (int w : ug.adj(cur)) {
                if (!visited[w]) {
                    visited[w] = true;
                    pre.add(w);
                    stack.push(w);
                }
            }
        }
    }

    public List<Integer> post() {
        return post;
    }

    public List<Integer> pre() {
        return pre;
    }

    public static void main(String[] args) {
        UnweightedGraph ug = new UnweightedGraph("g.txt");
        GraphDFS dfs1 = new GraphDFS(ug);
        System.out.println("DFS preorder: " + dfs1.pre());
        System.out.println("DFS postorder: " + dfs1.post());

        UnweightedGraph ug1 = new UnweightedGraph("g.txt", true);
        GraphDFS dfs2 = new GraphDFS(ug1);
        System.out.println("Directed Graph DFS preorder: " + dfs2.pre());
        System.out.println("Directed Graph DFS postorder: " + dfs2.post());

    }
}
