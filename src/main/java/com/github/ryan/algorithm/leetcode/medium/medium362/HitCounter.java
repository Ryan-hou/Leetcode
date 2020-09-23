package com.github.ryan.algorithm.leetcode.medium.medium362;

import java.util.TreeMap;

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
public class HitCounter {

    // key -> timestamp, value -> hits at this timestamp
    private TreeMap<Integer, Integer> map;
    private static final int TIME_LIMIT = 300; // 300 seconds

    /** Initialize your data structure here. */
    public HitCounter() {
        map = new TreeMap<>();
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        map.put(timestamp, map.getOrDefault(timestamp, 0) + 1);
        // clean up before getHits called
        while (timestamp - map.firstKey() >= TIME_LIMIT) {
            map.pollFirstEntry();
        }
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while (!map.isEmpty() && timestamp - map.firstKey() >= TIME_LIMIT) {
            map.pollFirstEntry();
        }
        int count = 0;
        for (int hit : map.values()) {
            count += hit;
        }
        return count;
    }

}
