package com.github.ryan.data_structure.graph.base;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * 无权图(支持有向&无向)
 * 简单图,不含自环边和平行边
 */
@Slf4j
public class UnweightedGraph implements Graph, Cloneable {

    private int V;
    private int E;
    /**
     * 顶点对应的相邻顶点集合
     * 也可以采用 HashSet, 这里使用红黑树的顺序性来方便测试
     */
    private TreeSet<Integer>[] adj;

    /**
     * 是否有向
     */
    private boolean directed;

    /**
     * 顶点的入度和出度
     */
    private int[] indegree, outdegree;

    public UnweightedGraph(String filename, boolean directed) {
        this.directed = directed;

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

            if (directed) {
                indegree = new int[V];
                outdegree = new int[V];
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
                if (directed) {
                    outdegree[a]++;
                    indegree[b]++;
                }

                if (!directed) {
                    adj[b].add(a);
                }
            }

        } catch (Exception e) {
            log.error("UnweightedGraph construct exception!", e);
        }
    }

    public UnweightedGraph(String filename) {
        this(filename, false);
    }

    public UnweightedGraph(TreeSet<Integer>[] adj, boolean directed) {
        this.adj = adj;
        this.directed = directed;
        this.V = adj.length;
        this.E = 0;

        indegree = new int[V];
        outdegree = new int[V];
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                outdegree[v]++;
                indegree[w]++;
                this.E++;
            }
        }
        if (!directed) {
            this.E /= 2;
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
    public boolean isDirected() {
        return directed;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        GraphUtil.validateVertex(this, v);
        GraphUtil.validateVertex(this, w);
        return adj[v].contains(w);
    }

    @Override
    public void removeEdge(int v, int w) {
        GraphUtil.validateVertex(this, v);
        GraphUtil.validateVertex(this, w);
        if (adj[v].contains(w)) {
            E--;
            if (directed) {
                outdegree[v]--;
                indegree[w]--;
            }
        }
        adj[v].remove(w);
        if (!directed) {
            adj[w].remove(v);
        }
    }

    @Override
    public Iterable<Integer> adj(int v) {
        GraphUtil.validateVertex(this, v);
        return adj[v];
    }

    @Override
    public int degree(int v) {
        if(directed) {
            throw new RuntimeException("degree only works in undirected graph.");
        }
        GraphUtil.validateVertex(this, v);
        return adj[v].size();
    }

    @Override
    public int indegree(int v) {
        if (!directed) {
            throw new RuntimeException("indegree only works in directed graph.");
        }
        return indegree[v];
    }

    @Override
    public int outdegree(int v) {
        if (!directed) {
            throw new RuntimeException("outdegree only works in directed graph.");
        }
        return outdegree[v];
    }

    public UnweightedGraph reverse() {
        TreeSet<Integer>[] rAdj = new TreeSet[V];
        for (int v = 0; v < V; v++) {
            rAdj[v] = new TreeSet<>();
        }

        for (int v = 0; v < V; v++) {
            for (int w : this.adj(v)) {
                rAdj[w].add(v);
            }
        }
        return new UnweightedGraph(rAdj, directed);
    }

    @Override
    public Object clone() {
        UnweightedGraph cloned = null;
        try {
            cloned = (UnweightedGraph) super.clone();
            // deep copy
            cloned.adj = new TreeSet[V];
            for (int v = 0; v < V; v++) {
                cloned.adj[v] = new TreeSet<>();
                for (int w : this.adj(v)) {
                    cloned.adj[v].add(w);
                }
            }

            if (directed) {
                cloned.indegree = Arrays.copyOf(this.indegree, V);
                cloned.outdegree = Arrays.copyOf(this.outdegree, V);
            }
        } catch (CloneNotSupportedException e) {
            log.error("UnweightedGraph clone exception!", e);
        }

        return cloned;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d, directed = %b\n", V, E, directed));
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

        Graph g1 = new UnweightedGraph("g.txt", true);
        System.out.println(g1);
    }
}
