package com.github.ryan.data_structure.graph.shortest_path;

import com.github.ryan.data_structure.graph.base.GraphUtil;
import com.github.ryan.data_structure.graph.base.WeightedGraph;

import java.util.Arrays;

public class Floyed {

    private WeightedGraph G;
    private int[][] dis;
    private boolean hasNegCycle = false;

    public Floyed(WeightedGraph G) {
        this.G = G;
        dis = new int[G.V()][G.V()];

        // Initialize data
        for (int v = 0; v < G.V(); v++) {
            Arrays.fill(dis[v], Integer.MAX_VALUE);
        }

        for (int v = 0; v < G.V(); v++) {
            dis[v][v] = 0;
            for (int w : G.adj(v)) {
                dis[v][w] = G.getWeight(v, w);
            }
        }

        /**
         * 每轮循环求解出 [v,w] 顶点中间经过 [0...t] 这些点的最短路径
         * 时间复杂度 O(V^3)
         */
        for (int t = 0; t < G.V(); t++) {
            for (int v = 0; v < G.V(); v++) {
                for (int w = 0; w < G.V(); w++) {
                    if (dis[v][t] != Integer.MAX_VALUE
                            && dis[t][w] != Integer.MAX_VALUE
                            && dis[v][t] + dis[t][w] < dis[v][w]) {
                        dis[v][w] = dis[v][t] + dis[t][w];
                    }
                }
            }
        }

        // Negative cycle detection
        for (int v = 0; v < G.V(); v++) {
            if (dis[v][v] < 0) {
                hasNegCycle = true;
                break;
            }
        }
    }

    public boolean hasNegCycle() {
        return hasNegCycle;
    }

    public boolean isConnected(int v, int w) {
        GraphUtil.validateVertex(G, v);
        GraphUtil.validateVertex(G, w);
        return dis[v][w] != Integer.MAX_VALUE;
    }

    public int dis(int v, int w) {
        GraphUtil.validateVertex(G, v);
        GraphUtil.validateVertex(G, w);
        return dis[v][w];
    }

    public static void main(String[] args) {
        WeightedGraph wg = new WeightedGraph("dijkstra.txt");
        Floyed floyed = new Floyed(wg);
        if (!floyed.hasNegCycle()) {
            for (int v = 0; v < wg.V(); v++) {
                for (int w = 0; w < wg.V(); w++) {
                    System.out.print(floyed.dis(v, w) + " ");
                }
                System.out.println();
            }
            System.out.println();
        } else {
            System.out.println("Exist negative cycle.");
        }
    }
}
