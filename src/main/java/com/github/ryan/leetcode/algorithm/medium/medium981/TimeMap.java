package com.github.ryan.leetcode.algorithm.medium.medium981;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// Use HashMap and TreeMap
public class TimeMap {

    private Map<String, TreeMap<Integer, String>> map;

    /** Initialize your data structure here. */
    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new TreeMap<>());
        map.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return "";

        TreeMap<Integer, String> tm = map.get(key);
        Integer t = tm.floorKey(timestamp);
        return t == null ? "" : tm.get(t);
    }

}


/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
