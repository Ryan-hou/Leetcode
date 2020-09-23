package com.github.ryan.algorithm.leetcode.medium.medium554;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public int leastBricks(List<List<Integer>> wall) {
        int min = wall.size();
        // key -> length from left, value -> rows at this length
        Map<Integer, Integer> map = new HashMap<>();
        for (List<Integer> rows : wall) {
            int localMax = 0, len = 0;
            for (int i = 0; i < rows.size() - 1; i++) {
                len += rows.get(i);
                int count = map.getOrDefault(len, 0) + 1;
                localMax = Math.max(localMax, count);
                map.put(len, count);
            }
            min = Math.min(wall.size() - localMax, min);
        }
        return min;
    }

}
