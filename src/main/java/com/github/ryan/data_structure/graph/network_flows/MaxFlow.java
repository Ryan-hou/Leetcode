package com.github.ryan.data_structure.graph.network_flows;

import com.github.ryan.data_structure.graph.base.GraphUtil;
import com.github.ryan.data_structure.graph.base.WeightedGraph;

import java.util.*;

public class MaxFlow {

    /**
     * 网络流
     */
    private WeightedGraph network;

    /**
     * Residual Graph: 残量图
     */
    private WeightedGraph rG;
    /**
     * 最大流
     */
    private int maxFlow;

    /**
     * 源点
     */
    private int s;
    /**
     * 汇点
     */
    private int t;

    public MaxFlow(WeightedGraph network, int s, int t) {
        if (!network.isDirected()) {
            throw new IllegalArgumentException("MaxFlow only works in directed graph.");
        }

        if (network.V() < 2) {
            throw new IllegalArgumentException("The network should have at least 2 vertices.");
        }
        GraphUtil.validateVertex(network, s);
        GraphUtil.validateVertex(network, t);
        if (s == t) {
            throw new IllegalArgumentException("s and t should be different.");
        }
        if (network.indegree(s) > 0) {
            throw new IllegalArgumentException("s's indegree should be zero.");
        }
        if (network.outdegree(t) > 0) {
            throw new IllegalArgumentException("t's outdegree should be zero.");
        }

        this.network = network;
        this.s = s;
        this.t = t;
        maxFlow = 0;

        // 构造残量图
        rG = new WeightedGraph(network.V(), true);
        for (int v = 0; v < network.V(); v++) {
            for (int w : network.adj(v)) {
                int c = network.getWeight(v, w);
                rG.addEdge(v, w, c);
                rG.addEdge(w, v, 0);
            }
        }

        // Ford-Fulkerson 思想实现框架
        while (true) {
            // 求解增广路径
            List<Integer> augPath = getAugmentingPath();
            if (augPath.size() == 0) {
                // 不存在新的增广路径，算法结束
                break;
            }

            // 计算增广路径的最小值
            int f = Integer.MAX_VALUE;
            for (int i = 1; i < augPath.size(); i++) {
                int v = augPath.get(i - 1);
                int w = augPath.get(i);
                f = Math.min(f, rG.getWeight(v, w));
            }
            // 更新最大流
            maxFlow += f;

            // 根据增广路径更新残量图 rG
            for (int i = 1; i < augPath.size(); i++) {
                int v = augPath.get(i - 1);
                int w = augPath.get(i);
                if (network.hasEdge(v, w)) {
                    rG.setWeight(v, w, rG.getWeight(v, w) - f);
                    rG.setWeight(w, v, rG.getWeight(w, v) + f);
                } else {
                    rG.setWeight(w, v, rG.getWeight(w, v) + f);
                    rG.setWeight(v, w, rG.getWeight(v, w) - f);
                }
            }
        }
    }

    // Edmonds-Karp 算法 -> BFS 求解增广路径
    private List<Integer> getAugmentingPath() {
        Queue<Integer> q = new ArrayDeque<>();
        int[] pre = new int[network.V()];
        // -1 表示顶点没有被访问
        Arrays.fill(pre, -1);

        q.offer(s);
        pre[s] = s;
        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == t) {
                break;
            }

            for (int next : rG.adj(cur)) {
                if (pre[next] == -1 && rG.getWeight(cur, next) > 0) {
                    pre[next] = cur;
                    q.add(next);
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        if (pre[t] == -1) {
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

    public int getMaxFlow() {
        return maxFlow;
    }

    public int flow(int v, int w) {
        if(!network.hasEdge(v, w))
            throw new IllegalArgumentException(String.format("No edge %d-%d", v, w));
        return rG.getWeight(w, v);
    }

    public static void main(String[] args) {
        WeightedGraph network = new WeightedGraph("network.txt", true);
        MaxFlow maxFlow = new MaxFlow(network, 0, 3);
        System.out.println("Max flow: " + maxFlow.getMaxFlow());
        for (int v = 0; v < network.V(); v++) {
            for (int w : network.adj(v)) {
                System.out.println(String.format("%d-%d: %d / %d", v, w, maxFlow.flow(v, w), network.getWeight(v, w)));
            }
        }
    }
}
