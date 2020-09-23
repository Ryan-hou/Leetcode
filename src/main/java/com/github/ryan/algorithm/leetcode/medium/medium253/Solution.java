package com.github.ryan.algorithm.leetcode.medium.medium253;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {

    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length < 1) return 0;

        // sort by start time
        Arrays.sort(intervals, (a1, a2) -> a1[0] - a2[0]);
        // min heap by end time
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= pq.peek()) {
                pq.poll();
            }
            pq.offer(intervals[i][1]);
        }
        return pq.size();
    }

}
