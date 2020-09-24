package com.github.ryan.data_structure.graph.shortest_path;

import com.github.ryan.data_structure.graph.base.GraphUtil;
import com.github.ryan.data_structure.graph.base.WeightedGraph;

import java.util.*;

/**
 * 使用优先队列优化 Dijkstra 算法, 时间复杂度 O(ElogE)
 * todo: 使用索引堆优化，时间复杂度 O(ElogV)
 */
public class Dijkstra2 {

    private WeightedGraph G;
    private int s;
    private boolean[] visited;
    private int[] dis;
    private int[] pre;

    private static class Node implements Comparable<Node> {
        // 顶点 v
        int v;
        // 源点到顶点 v 的距离值
        int dis;

        public Node(int v, int dis) {
            this.v = v;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node another) {
            return Integer.compare(this.dis, another.dis);
        }
    }

    public Dijkstra2(WeightedGraph G, int s) {
        this.G = G;
        GraphUtil.validateVertex(G, s);
        this.s = s;

        visited = new boolean[G.V()];
        dis = new int[G.V()];
        Arrays.fill(dis, Integer.MAX_VALUE);
        pre = new int[G.V()];
        Arrays.fill(pre, -1);

        dis[s] = 0;
        pre[s] = s;
        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(s, 0));
        // 最多所有的边都会入队然后出队
        // 时间复杂度 O(ElogE)
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visited[cur.v]) {
                continue;
            }

            visited[cur.v] = true;
            for (int w : G.adj(cur.v)) {
                if (!visited[w]) {
                    if (dis[cur.v] + G.getWeight(cur.v, w) < dis[w]) {
                        // 本质是一次松弛操作
                        pre[w] = cur.v;
                        // 顶点存在重复添加
                        pq.offer(new Node(w, cur.dis + G.getWeight(cur.v, w)));
                    }
                }
            }
        } // end while
    }

    public boolean isConnected(int t) {
        GraphUtil.validateVertex(G, t);
        return visited[t];
    }

    public int disTo(int t) {
        GraphUtil.validateVertex(G, t);
        return dis[t];
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
        Dijkstra2 dijkstra2 = new Dijkstra2(wg, 0);
        System.out.println("0 -> 3: " + dijkstra2.path(3));
    }
}
