package com.github.ryan.algorithm.leetcode.hard.hard847;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

    public int shortestPathLength(int[][] graph) {
        int V = graph.length;
        // state compression
        int allVisited = (1 << V) - 1;
        boolean[][] visited = new boolean[V][allVisited];

        // BFS
        Queue<int[]> q = new ArrayDeque<>();
        for (int v = 0; v < V; v++) {
            int state = 0;
            q.offer(new int[] {v, state | (1 << v)});
        }

        int step = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                int[] cur = q.poll();
                int curV = cur[0];
                int curState = cur[1];
                if (curState == allVisited) {
                    return step;
                }

                if (visited[curV][curState]) {
                    continue;
                }

                visited[curV][curState] = true;
                for (int w : graph[curV]) {
                    q.offer(new int[] {w, curState | (1 << w)});
                }
            }
            step++;
        } // end while
        return -1;
    }


}
