package com.github.ryan.data_structure.graph.matching;

import com.github.ryan.data_structure.graph.base.UnweightedGraph;
import com.github.ryan.data_structure.graph.base.dfs.BipartitionDetection;

import java.util.*;

/**
 * 匈牙利算法求解二分图最大匹配问题 BFS
 */
public class Hungarian {

    private UnweightedGraph g;

    /**
     * 二分图最大匹配数
     */
    private int maxMatching;

    /**
     * matching[v] 的值为顶点 v 对应的匹配顶点
     */
    private int[] matching;

    public Hungarian(UnweightedGraph g) {
        BipartitionDetection bd = new BipartitionDetection(g);
        if (!bd.isBipartite()) {
            throw new IllegalArgumentException("Hungarian only works for bipartite graph.");
        }

        this.g = g;
        maxMatching = 0;
        matching = new int[g.V()];
        Arrays.fill(matching, -1);

        /**
         * colors[v] == 0 表示顶点 v 在二分图的左侧
         */
        int[] colors = bd.colors();

        for (int v = 0; v < g.V(); v++) {
            // 从左侧的一个非匹配点出发
            if (colors[v] == 0 && matching[v] == -1) {
                if (bfs(v)) {
                    maxMatching++;
                }
            }
        }
    }

    /**
     * 从顶点 v 开始 bfs，寻找增广路径，存在增广路径则返回 true
     * @param v
     * @return
     */
    private boolean bfs(int v) {
        Queue<Integer> q = new ArrayDeque<>();
        // pre 数组用于求解增广路径和更新顶点的匹配关系
        // pre[v] == -1 表示顶点 v 还未被访问
        int[] pre = new int[g.V()];
        Arrays.fill(pre, -1);

        q.offer(v);
        pre[v] = v;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : g.adj(cur)) {
                if (pre[next] == -1) {
                    if (matching[next] != -1) {
                        // 顶点w存在匹配的顶点，沿着该顶点回到二分图左侧
                        pre[next] = cur;
                        pre[matching[next]] = next;
                        q.offer(matching[next]);
                    } else {
                        // 找到增广路径
                        pre[next] = cur;
                        List<Integer> augPath = getAugPath(pre, v, next);
                        // 更新节点的匹配关系
                        for (int i = 0; i < augPath.size(); i += 2) {
                            matching[augPath.get(i)] = augPath.get(i + 1);
                            matching[augPath.get(i + 1)] = augPath.get(i);
                        }
                        return true;
                    }
                }
            }
        } // end while
        return false;
    }

    private List<Integer> getAugPath(int[] pre, int start, int end) {
        List<Integer> path = new ArrayList<>();
        int cur = end;
        while (cur != start) {
            path.add(cur);
            cur = pre[cur];
        }
        path.add(start);
        return path;
    }

    public int maxMatching() {
        return maxMatching;
    }

    public boolean isPerfectMatching() {
        return maxMatching * 2 == g.V();
    }

    public static void main(String[] args) {
        UnweightedGraph g = new UnweightedGraph("matching.txt");
        Hungarian hungarian = new Hungarian(g);
        System.out.println("Max matching is: " + hungarian.maxMatching());
    }
}
