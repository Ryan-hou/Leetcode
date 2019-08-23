package com.github.ryan.leetcode.algorithm.hard.hard218;

import java.util.*;

public class Solution {

    public List<List<Integer>> getSkyline(int[][] buildings) {

        List<List<Integer>> res = new ArrayList<>();
        PriorityQueue<Point> pq = new PriorityQueue<>();
        // key -> height, value: number of point at this height
        TreeMap<Integer, Integer> map = new TreeMap<>();

        pq.offer(new Point(0, 0, true));
        for (int[] b : buildings) {
            pq.offer(new Point(b[2], b[0], true));
            pq.offer(new Point(b[2], b[1], false));
        }

        int max = 0;
        while (!pq.isEmpty()) {
            Point p = pq.poll();
            if (p.start) {
                map.put(p.height, map.getOrDefault(p.height, 0) + 1);
            } else {
                map.put(p.height, map.get(p.height) - 1);
                if (map.get(p.height) == 0) {
                    map.remove(p.height);
                }
            }

            int nextMax = map.lastKey();
            if (nextMax != max) {
                res.add(Arrays.asList(p.x, nextMax));
                max = nextMax;
            }
        }
        return res;
    }

    private static class Point implements Comparable<Point> {
        int height;
        int x; // 坐标值
        boolean start;

        Point(int height, int x, boolean start) {
            this.height = height;
            this.x = x;
            this.start = start;
        }

        @Override
        public int compareTo(Point p) {
            if (this.x != p.x) {
                return this.x - p.x;
            } else {
                // this.x == p.x
                if (this.start != p.start) {
                    return this.start ? -1 : 1;
                } else {
                    return this.start ? p.height - this.height : this.height - p.height;
                }
            }
        }
    }
}
