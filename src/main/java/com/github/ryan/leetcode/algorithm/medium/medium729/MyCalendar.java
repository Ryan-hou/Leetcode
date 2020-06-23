package com.github.ryan.leetcode.algorithm.medium.medium729;

import java.util.TreeMap;

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
public class MyCalendar {

    // key -> start of event, value -> end of event
    private TreeMap<Integer, Integer> events;

    public MyCalendar() {
        events = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Integer prev = events.floorKey(start);
        Integer next = events.ceilingKey(start);
        if ((prev == null || events.get(prev) <= start)
                && (next == null || end <= next)) {
            events.put(start, end);
            return true;
        } else {
            return false;
        }
    }
}
