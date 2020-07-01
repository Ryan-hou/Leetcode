package com.github.ryan.leetcode.algorithm.hard.hard1345;

import java.util.*;

public class Solution {

    public int minJumps(int[] arr) {
        // key -> arr[i], value -> all indexes that value equals arr[i]
        Map<Integer, List<Integer>> dict = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            dict.computeIfAbsent(arr[i], t -> new ArrayList<>()).add(i);
        }

        // q is index queue
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        int jumps = 0;
        Set<Integer> visited = new HashSet<>();
        // BFS
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                int idx = q.poll();
                visited.add(idx);
                if (idx == arr.length - 1) {
                    return jumps;
                }
                if (idx + 1 < arr.length && !visited.contains(idx + 1)) {
                    q.offer(idx + 1);
                    visited.add(idx + 1);
                }
                if (idx - 1 >= 0 && !visited.contains(idx - 1)) {
                    q.offer(idx - 1);
                    visited.add(idx - 1);
                }
                if (dict.containsKey(arr[idx])) {
                    for (int k : dict.get(arr[idx])) {
                        if (!visited.contains(k)) {
                            q.offer(k);
                            visited.add(k);
                        }
                    }
                    dict.remove(arr[idx]);
                }
            } // end for
            jumps++;
        } // end while
        return jumps;
    }

}
