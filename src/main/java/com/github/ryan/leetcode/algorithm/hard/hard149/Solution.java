package com.github.ryan.leetcode.algorithm.hard.hard149;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 31,2019
 */
public class Solution {

    // solution from:
    // https://leetcode.com/problems/max-points-on-a-line/discuss/328269/A-Java-solution-with-my-understanding
    public int maxPoints(int[][] points) {
        if (points == null) return 0;
        if (points.length < 3) return points.length;

        // key -> slope,value -> number of points at this slope
        Map<Long, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < points.length; i++) {
            map.clear();
            int dup = 1;
            for (int j = i + 1; j < points.length; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                if (dx == 0 && dy == 0) {
                    dup++;
                } else {
                    int gcd = getGcd(dx, dy);
                    // slope: higher 32-bit is dy, lower 32-bit is dx
                    long slope = (((long) (dy / gcd)) << 32) + dx / gcd;
                    map.put(slope, map.getOrDefault(slope, 0) + 1);
                }
            }
            max = Math.max(max, dup);
            for (Map.Entry<Long, Integer> entry : map.entrySet()) {
                max = Math.max(max, entry.getValue() + dup);
            }
        }
        return max;
    }

    private int getGcd(int a, int b) {
        return b == 0 ? a : getGcd(b, a % b);
    }
}
