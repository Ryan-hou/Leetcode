package com.github.ryan.algorithm.leetcode.hard.hard269;

import java.util.*;

public class Solution {

    public String alienOrder(String[] words) {
        // Initializing data structure
        // key -> character from a~z, value -> key's adjecnt character
        Map<Character, List<Character>> adjMap = new HashMap<>();
        // key -> character from a~z, value -> this character's indegree
        Map<Character, Integer> indegreeMap = new HashMap<>();
        for (String w : words) {
            for (char ch : w.toCharArray()) {
                indegreeMap.put(ch, 0);
                adjMap.put(ch, new ArrayList<>());
            }
        }

        // Construct adjMap and indegreeMap
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            if (w2.length() < w1.length() && w1.startsWith(w2)) {
                // quick return -> invalid order
                return "";
            }

            for (int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
                char ch1 = w1.charAt(j);
                char ch2 = w2.charAt(j);
                if (ch1 != ch2) {
                    adjMap.get(ch1).add(ch2);
                    indegreeMap.put(ch2, indegreeMap.get(ch2) + 1);
                    break; // be careful to forget break
                }
            }
        }

        // BFS
        StringBuilder b = new StringBuilder();
        Queue<Character> q = new ArrayDeque<>();
        for (Character ch : indegreeMap.keySet()) {
            if (indegreeMap.get(ch) == 0) {
                q.offer(ch);
            }
        }

        while (!q.isEmpty()) {
            Character ch = q.poll();
            b.append(ch);
            for (Character next : adjMap.get(ch)) {
                indegreeMap.put(next, indegreeMap.get(next) - 1);
                if (indegreeMap.get(next) == 0) {
                    q.offer(next);
                }
            }
        }

        if (b.length() != adjMap.size()) {
            // invalid order
            return "";
        } else {
            return b.toString();
        }
    }

}
