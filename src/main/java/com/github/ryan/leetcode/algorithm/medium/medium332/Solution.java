package com.github.ryan.leetcode.algorithm.medium.medium332;

import java.util.*;

public class Solution {


    // Euler Path -> Hierhozer algorithm
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, Queue<String>> g = new HashMap<>();
        for (int i = 0; i < tickets.size(); i++) {
            List<String> flight = tickets.get(i);
            String start = flight.get(0);
            String end = flight.get(1);
            // corner case: use priority queue rather than TreeSet
            // cause tickets may be duplicated.
            g.putIfAbsent(start, new PriorityQueue<>());
            g.get(start).add(end);
        }

        List<String> res = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        String curv = "JFK";
        stack.push(curv);
        while (!stack.isEmpty()) {
            if (g.containsKey(curv) && g.get(curv).size() > 0) {
                stack.push(curv);
                String next = g.get(curv).poll();
                curv = next;
            } else {
                res.add(curv);
                curv = stack.pop();
            }
        }

        Collections.reverse(res);
        return res;
    }
}
