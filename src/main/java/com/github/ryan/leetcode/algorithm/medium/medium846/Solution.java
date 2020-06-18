package com.github.ryan.leetcode.algorithm.medium.medium846;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public boolean isNStraightHand(int[] hand, int W) {
        if (hand.length % W != 0) return false;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        // key -> num, value -> freq of this num
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : hand) {
            min = Math.min(min, n);
            max = Math.max(max, n);
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }

        for (int i = min; i <= max; i++) {
            if (freq.getOrDefault(i, 0) < 0) return false;
            if (freq.getOrDefault(i, 0) == 0) continue;
            if (max - i + 1 < W) return false;

            // freq.get(i) > 0
            for (int j = i + W - 1; j >= i; j--) {
                freq.put(j, freq.getOrDefault(j, 0) - freq.get(i));
            }
        }
        return true;
    }

}
