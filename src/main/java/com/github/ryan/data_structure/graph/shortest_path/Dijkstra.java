package com.github.ryan.data_structure.graph.shortest_path;

import com.github.ryan.data_structure.graph.base.GraphUtil;
import com.github.ryan.data_structure.graph.base.WeightedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Dijkstra {

    private WeightedGraph G;
    /**
     * 单源最短路径的源
     */
    private int s;
    /**
     * 记录源点到某一顶点的最短路径是否已经确定
     */
    private boolean[] visited;

    /**
     * 记录源点到顶点V的最短路径值
     */
    private int[] dis;

    private int[] pre;

    public Dijkstra(WeightedGraph G, int s) {
        this.G = G;
        GraphUtil.validateVertex(G, s);
        this.s = s;

        visited = new boolean[G.V()];
        pre = new int[G.V()];
        Arrays.fill(pre, -1);

        dis = new int[G.V()];
        /**
         * Integer.MAX_VALUE 作为无穷大，是一个哨兵值
         * 存在溢出的可能性，可以用 long 类型或者大整型替代
         * 或者通过其他标志来单独标志无穷大
         */
        Arrays.fill(dis, Integer.MAX_VALUE);

        dis[s] = 0;
        pre[s] = s;
        // 每轮确定到某一个顶点的最短路径，一共 V 轮
        // 时间复杂度 O(V^2)
        while (true) {
            // 性能瓶颈 -> 通过其他动态数据结构优化
            // 1. 获取当前未确定最短路径的顶点的最小值
            int curdis = Integer.MAX_VALUE, curv = -1;
            for (int v = 0; v < G.V(); v++) {
                if (!visited[v]) {
                    if (dis[v] < curdis) {
                        curdis = dis[v];
                        curv = v;
                    }
                }
            }

            if (curv == -1) {
                break;
            }

            // 2. 确定源点到curv的最短距离
            visited[curv] = true;

            // 3. 更新curv的相邻顶点
            for (int w : G.adj(curv)) {
                if (!visited[w]) {
                    if (dis[curv] + G.getWeight(curv, w) < dis[w]) {
                        dis[w] = dis[curv] + G.getWeight(curv, w);
                        pre[w] = curv;
                    }
                }
            }

        } // end while
    }


    public boolean connectedTo(int t) {
        GraphUtil.validateVertex(G, t);
        return visited[t];
    }

    public int disTo(int t) {
        GraphUtil.validateVertex(G, t);
        return dis[t];
    }

    public Iterable<Integer> path(int t) {
        List<Integer> res = new ArrayList<>();
        if (!connectedTo(t)) {
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
        Dijkstra dijkstra = new Dijkstra(wg, 0);
        for (int v = 0; v < wg.V(); v++) {
            System.out.print(dijkstra.dis[v] + " ");
        }
        System.out.println();

        System.out.println("0 -> 3: " + dijkstra.path(3));
    }

}
