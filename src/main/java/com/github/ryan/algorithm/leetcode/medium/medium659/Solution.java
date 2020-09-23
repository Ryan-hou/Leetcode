package com.github.ryan.algorithm.leetcode.medium.medium659;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public boolean isPossible(int[] nums) {
        // key -> num, value -> num's freq
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) {
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }

        for (int n : nums) {
            if (freq.get(n) == 0) continue;
            int count = 0;
            int num = n;
            while (freq.getOrDefault(num, 0) > 0) {
                count++;
                //  try not to consume the current num
                // because we know the current num can by connected with previous num
                // based on freq.get(num) <= freq.get(num - 1)
                if (count > 3 && freq.get(num) <= freq.get(num - 1)) break;
                freq.put(num, freq.get(num) - 1);
                num++;
            }
            if (count < 3) return false;
        }
        return true;
    }

}
