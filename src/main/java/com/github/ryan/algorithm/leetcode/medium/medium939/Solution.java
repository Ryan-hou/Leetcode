package com.github.ryan.algorithm.leetcode.medium.medium939;

import java.util.*;

public class Solution {

    public int minAreaRect(int[][] points) {
        // x axis -> cols, y axis -> rows

        // key -> x, value -> list of y at this x axis
        // sort by x
        Map<Integer, List<Integer>> colsMap = new TreeMap<>();
        for (int[] p : points) {
            int x = p[0], y = p[1];
            colsMap.computeIfAbsent(x, z -> new ArrayList<>()).add(y);
        }

        // key -> y pairs' hash, value -> x linked with this y pairs
        Map<Integer, Integer> lastX = new HashMap<>();
        int res = Integer.MAX_VALUE;
        for (int x : colsMap.keySet()) {
            List<Integer> rows = colsMap.get(x);
            // need sort to keep hash and y2 - y1 paired
            Collections.sort(rows);
            for (int i = 0; i < rows.size(); i++) {
                for (int j = i + 1; j < rows.size(); j++) {
                    int y1 = rows.get(i);
                    int y2 = rows.get(j);
                    int hash = 40001 * y1 + y2;
                    if (lastX.containsKey(hash)) {
                        res = Math.min(res, (x - lastX.get(hash)) * (y2 - y1));
                    }
                    lastX.put(hash, x);
                }
            }
        }

        return res < Integer.MAX_VALUE ? res : 0;
    }

}
