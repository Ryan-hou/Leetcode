package com.github.ryan.data_structure.graph.base;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * 带权图
 */
@Slf4j
public class WeightedGraph implements Graph {

    private int V;
    private int E;
    /**
     * 顶点对应的相邻顶点集合及相应的权值，这里的权值简化为整型
     * 也可以采用 HashMap, 这里使用红黑树的顺序性来方便测试
     */
    private TreeMap<Integer, Integer>[] adj;

    public WeightedGraph(String filename) {
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

            adj = new TreeMap[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeMap<>();
            }

            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                GraphUtil.validateVertex(this, a);
                int b = scanner.nextInt();
                GraphUtil.validateVertex(this, b);
                int weight = scanner.nextInt();

                if (a == b) {
                    throw new IllegalArgumentException("Self Loop is Detected!");
                }
                if (adj[a].containsKey(b)) {
                    // 带权图的平行边需根据具体情况进行处理，比如取值最小的边
                    throw new IllegalArgumentException("Parallel Edges are Detected!");
                }

                adj[a].put(b, weight);
                adj[b].put(a, weight);
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
        return adj[v].containsKey(w);
    }

    @Override
    public void removeEdge(int v, int w) {
        GraphUtil.validateVertex(this, v);
        GraphUtil.validateVertex(this, w);
        if (adj[v].containsKey(w)) {
            E--;
        }
        adj[v].remove(w);
        adj[w].remove(v);
    }

    @Override
    public Iterable<Integer> adj(int v) {
        GraphUtil.validateVertex(this, v);
        return adj[v].keySet();
    }

    @Override
    public int degree(int v) {
        GraphUtil.validateVertex(this, v);
        return adj[v].size();
    }

    @Override
    public Object clone() {
        WeightedGraph cloned = null;
        try {
            cloned = (WeightedGraph) super.clone();
            // deep copy
            cloned.adj = new TreeMap[V];
            for (int v = 0; v < V; v++) {
                cloned.adj[v] = new TreeMap<>();
                for (Map.Entry<Integer, Integer> entry : this.adj[v].entrySet()) {
                    cloned.adj[v].put(entry.getKey(), entry.getValue());
                }
            }
        } catch (CloneNotSupportedException e) {
            log.error("WeightedGraph clone exception!", e);
        }

        return cloned;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int v = 0; v < V; v++) {
            sb.append(String.format("%d : ", v));
            for (Map.Entry<Integer, Integer> entry : adj[v].entrySet()) {
                sb.append(String.format("(%d: %d) ", entry.getKey(), entry.getValue()));
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        Graph g = new WeightedGraph("mst.txt");
        System.out.println(g);
    }

}
