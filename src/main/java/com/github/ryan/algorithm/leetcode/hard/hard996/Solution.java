package com.github.ryan.algorithm.leetcode.hard.hard996;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    private Map<Integer, Integer> count;
    private Map<Integer, List<Integer>> g;

    /**
     * Construct a graph where an edge from i to j exits if
     * A[i] + A[j] is a perfect square. Our goal is to investigate
     * Hamiltonian paths of this graph: paths that visit all the
     * nodes exactly once.
     */
    public int numSquarefulPerms(int[] A) {
        count = new HashMap<>();
        g = new HashMap<>();

        for (int n : A) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }


        // g.get(v): values w in A for which v + w is a square
        // ie. v-w is an edge
        for (int x : count.keySet()) {
            g.put(x, new ArrayList<>());
            for (int y : count.keySet()) {
                // 这里可能会保存实际并不存在的自环边，但后续算法会通过count来排除这种自环边
                double a = Math.sqrt(x + y);
                if (a - (int) a == 0) {
                    g.get(x).add(y);
                }
            }
        }

        int N = A.length;
        // Add the number of paths that start at x, for all possible x
        int res = 0;
        for (int x : count.keySet()) {
            res += dfs(x, N - 1);
        }

        return res;
    }

    // 从顶点x开始dfs，还剩 todo 个顶点需要访问
    private int dfs(int x, int todo) {
        if (todo == 0) {
            return 1;
        }

        // todo != 0
        count.put(x, count.get(x) - 1);
        int res = 0;
        for (int y : g.get(x)) {
            if (count.get(y) != 0) {
                // 排除实际不存在的自环边的影响
                res += dfs(y, todo - 1);
            }
        }
        // backtracking
        count.put(x, count.get(x) + 1);
        return res;
    }

}
