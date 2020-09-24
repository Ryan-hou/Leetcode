package com.github.ryan.data_structure.graph.shortest_path;

import com.github.ryan.data_structure.graph.base.GraphUtil;
import com.github.ryan.data_structure.graph.base.WeightedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BellmanFord {

    private WeightedGraph G;
    /**
     * 单源最短路径的源点
     */
    private int s;

    private int[] dis;

    private int[] pre;

    /**
     * 是否存在负权环
     */
    private boolean hasNegativeCycle = false;

    public BellmanFord(WeightedGraph G, int s) {
        this.G = G;
        GraphUtil.validateVertex(G, s);
        this.s = s;

        dis = new int[G.V()];
        /**
         * Integer.MAX_VALUE 作为哨兵值，表示无穷大
         */
        Arrays.fill(dis, Integer.MAX_VALUE);
        pre = new int[G.V()];
        Arrays.fill(pre, -1);

        dis[s] = 0;
        pre[s] = s;

        /**
         * 对所有边进行 V - 1 轮松弛操作
         * 时间复杂度 O(VE)
         */
        for (int pass = 1; pass < G.V(); pass++) {
            for (int v = 0; v < G.V(); v++) {
                for (int w : G.adj(v)) {
                    if (dis[v] != Integer.MAX_VALUE
                            && dis[v] + G.getWeight(v, w) < dis[w]) {
                        dis[w] = dis[v] + G.getWeight(v, w);
                        pre[w] = v;
                    }
                }
            }
        }

        // negative cycle detect: 再做一次松弛操作
        outer:
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (dis[v] != Integer.MAX_VALUE
                        && dis[v] + G.getWeight(v, w) < dis[w]) {
                    hasNegativeCycle = true;
                    break outer;
                }
            }
        }

    }

    public boolean hasNegativeCycle() {
        return hasNegativeCycle;
    }

    public boolean isConnected(int w) {
        GraphUtil.validateVertex(G, w);
        return dis[w] != Integer.MAX_VALUE;
    }

    public int disTo(int w) {
        GraphUtil.validateVertex(G, w);
        if (hasNegativeCycle) {
            throw new RuntimeException("Exist negative cycle.");
        }

        return dis[w];
    }

    public Iterable<Integer> path(int t) {
        List<Integer> res = new ArrayList<>();
        if (!isConnected(t)) {
            return res;
        }

        int cur = t;
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        WeightedGraph wg = new WeightedGraph("dijkstra.txt");
        BellmanFord bf = new BellmanFord(wg, 0);
        if (!bf.hasNegativeCycle()) {
            for (int v = 0; v < wg.V(); v++) {
                System.out.print(bf.disTo(v) + " ");
            }
            System.out.println();

            System.out.println("0 -> 3: " + bf.path(3));
        } else {
            System.out.println("Exist negative cycle.");
        }
    }
}
