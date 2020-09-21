package com.github.ryan.personal.data_structure.graph.base;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * 无权图
 * 简单图,不含自环边和平行边
 */
@Slf4j
public class UnweightedGraph implements Graph {

    private int V;
    private int E;
    /**
     * 顶点对应的相邻顶点集合
     * 也可以采用 HashSet, 这里使用红黑树的顺序性来方便测试
     */
    private TreeSet<Integer>[] adj;

    public UnweightedGraph(String filename) {
        String parentPath = UnweightedGraph.class.getResource("/").getPath();
        String subPath = "graph" + File.separator + filename;
        File file = new File(parentPath + subPath);

        try (Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();
            if (V < 0) {
                throw new IllegalArgumentException("V must be non-negative");
            }
            E = scanner.nextInt();
            if (E < 0) {
                throw new IllegalArgumentException("E must be non-negative");
            }

            adj = new TreeSet[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeSet<>();
            }

            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                GraphUtil.validateVertex(this, a);
                int b = scanner.nextInt();
                GraphUtil.validateVertex(this, b);

                if (a == b) {
                    throw new IllegalArgumentException("Self Loop is Detected!");
                }
                if (adj[a].contains(b)) {
                    throw new IllegalArgumentException("Parallel Edges are Detected!");
                }

                adj[a].add(b);
                adj[b].add(a);
            }

        } catch (Exception e) {
            log.error("UnweightedGraph construct exception!", e);
        }
    }

    @Override
    public int V() {
        return V;
    }

    @Override
    public int E() {
        return E;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        GraphUtil.validateVertex(this, v);
        GraphUtil.validateVertex(this, w);
        return adj[v].contains(w);
    }

    @Override
    public Iterable<Integer> adj(int v) {
        GraphUtil.validateVertex(this, v);
        return adj[v];
    }

    @Override
    public int degree(int v) {
        GraphUtil.validateVertex(this, v);
        return adj[v].size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int v = 0; v < V; v++) {
            sb.append(String.format("%d : ", v));
            for (int w : adj[v]) {
                sb.append(String.format("%d ", w));
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        Graph g = new UnweightedGraph("g.txt");
        System.out.println(g);
    }
}
