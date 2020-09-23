package com.github.ryan.algorithm.leetcode.medium.medium930;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int numSubarraysWithSum(int[] A, int S) {
        int n = A.length;
        // p[i] = A[0] + A[1] + ... A[i - 1]
        // hence we are looking for the number
        // of i < j with p[j] - p[i] = S
        int[] p = new int[n + 1];
        for (int i = 0; i < n; i++) {
            p[i + 1] = p[i] + A[i];
        }

        int res = 0;
        Map<Integer, Integer> count = new HashMap<>();
        for (int x : p) {
            res += count.getOrDefault(x, 0);
            count.put(x + S, count.getOrDefault(x + S, 0) + 1);
        }
        return res;
    }
}
