package com.github.ryan.leetcode.algorithm.medium.medium1376;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        for (int i = 0; i < n; i++) {
            g.computeIfAbsent(manager[i], x -> new ArrayList<>()).add(i);
        }
        return dfs(headID, g, informTime);
    }

    private int dfs(int cur, Map<Integer, List<Integer>> g, int[] time) {
        int max = 0;
        if (g.get(cur) != null) {
            for (int sub : g.get(cur)) {
                int t = dfs(sub, g, time);
                max = Math.max(max, t);
            }
        }
        return max + time[cur];
    }

}
