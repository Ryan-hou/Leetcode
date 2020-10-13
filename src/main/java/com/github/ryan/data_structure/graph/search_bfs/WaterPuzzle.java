package com.github.ryan.data_structure.graph.search_bfs;


import java.util.*;

/**
 * 智力题: 有两个水桶，一个装5升，⼀个装3升，桶没有刻度，
 * 怎么利用这两个水桶得到4升水？
 * 两个水桶当前的盛水状态表示为图中的顶点，使用状态压缩把两个水桶的盛水状态压缩为一个整数
 * (x, y) -> x * 10 + y
 */
public class WaterPuzzle {

    /**
     * 存储图中的路径 -> 即状态转移的过程 -> 即操作步骤
     */
    private int[] pre;
    /**
     * 结束时的状态
     */
    private int end = -1;

    public WaterPuzzle() {
        Queue<Integer> q = new ArrayDeque<>();
        // 顶点最大为 5*10+3，这里直接开辟 100 个空间，浪费的空间可以忽略不计
        boolean[] visited = new boolean[100];
        pre = new int[100];
        // 初始状态 0*0+0 -> 0
        q.offer(0);
        visited[0] = true;
        pre[0] = 0;

        // BFS
        while (!q.isEmpty()) {
            int cur = q.poll();
            int a = cur / 10, b = cur % 10;
            // max a = 5, max b = 3

            // 计算状态转移后的状态
            // 状态转移与状态检测分开处理
            List<Integer> nexts = new ArrayList<>();
            nexts.add(5 * 10 + b);
            nexts.add(a * 10 + 3);
            nexts.add(0 * 10 + b);
            nexts.add(a * 10 + 0);

            // a 桶的水倒向 b 桶
            int x = Math.min(a, 3 - b);
            nexts.add((a - x) * 10 + (b + x));
            // b 桶的水倒向 a 桶
            int y = Math.min(b, 5 - a);
            nexts.add((a + y) * 10 + (b - y));

            for (int next : nexts) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                    pre[next] = cur;
                    if (next / 10 == 4 || next % 10 == 4) {
                        end = next;
                        return;
                    }
                }
            }
        } // end while

    }

    public Iterable<Integer> result() {
        List<Integer> res = new ArrayList<>();
        if (end == -1) {
            return res;
        }

        int cur = end;
        while (cur != 0) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(0);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        // 0 -> 00, 2 -> 02
        // [0, 50, 23, 20, 2, 52, 43]
        System.out.println(new WaterPuzzle().result());
    }
}
