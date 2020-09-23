package com.github.ryan.algorithm.leetcode.medium.medium752;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Solution {

    // two adjacent num represents two nodes in a edge
    // BFS
    public int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet<>();
        for (String d : deadends) {
            dead.add(d);
        }

        Set<String> seen = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.offer("0000");
        // null represents level mark
        q.offer(null);
        seen.add("0000");
        int depth = 0;
        while (!q.isEmpty()) {
            String node = q.poll();
            if (node == null) {
                depth++;
                if (q.peek() != null) {
                    // add level mark
                    q.offer(null);
                }
            } else if (target.equals(node)) {
                return depth;
            } else if (!dead.contains(node)){
                for (int i = 0; i < 4; i++) {
                    // forward or backward
                    for (int j = -1; j <= 1; j += 2) {
                        int num = (node.charAt(i) - '0' + j + 10) % 10;
                        String next = node.substring(0, i) + (num + "") + node.substring(i + 1);
                        if (!seen.contains(next)) {
                            q.offer(next);
                            seen.add(next);
                        }
                    }
                }
            }
        } // end while
        return -1;
    }

}
