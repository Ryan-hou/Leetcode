package com.github.ryan.leetcode.algorithm.medium.medium767;

import java.util.*;

public class Solution {

    // hard358 is the same template, just replace 2 to K
    public String reorganizeString(String S) {
        StringBuilder b = new StringBuilder();
        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : S.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }
        // max heap (by freq)
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((e1, e2) -> {
            if (e1.getValue().equals(e2.getValue())) {
                return e1.getKey().compareTo(e2.getKey());
            } else {
                return e2.getValue().compareTo(e1.getValue());
            }
        });
        // Initialize data
        pq.addAll(freq.entrySet());
        freq.clear();

        // cooling queue
        // coolingQueue wait for 2 ( or K) charcaters to come in between the same charcater
        Queue<Map.Entry<Character, Integer>> coolingq = new ArrayDeque<>();

        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> e = pq.poll();
            b.append(e.getKey());
            e.setValue(e.getValue() - 1);
            coolingq.offer(e);
            if (coolingq.size() < 2) {
                continue;
            }

            while (!coolingq.isEmpty()) {
                Map.Entry<Character, Integer> entry = coolingq.poll();
                if (entry.getValue() > 0) {
                    pq.offer(entry);
                }
            }
        }

        return b.length() == S.length() ? b.toString() : "";
    }

}
