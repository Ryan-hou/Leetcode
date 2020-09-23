package com.github.ryan.algorithm.leetcode.hard.hard992;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int subarraysWithKDistinct(int[] A, int K) {
        int count = 0;
        Map<Integer, Integer> freq1 = new HashMap<>();
        Map<Integer, Integer> freq2 = new HashMap<>();
        int idx1 = 0, idx2 = 0;
        for (int i = 0; i < A.length; i++) {
            int cur = A[i];
            freq1.put(cur, freq1.getOrDefault(cur, 0) + 1);
            freq2.put(cur, freq2.getOrDefault(cur, 0) + 1);

            // the longest qualified subarray(K distinct numbers) till now: [idx1, i]
            while (freq1.size() > K) {
                int num = A[idx1];
                if (freq1.get(num) == 1) {
                    freq1.remove(num);
                } else {
                    // freq1.get(num) > 1
                    freq1.put(num, freq1.get(num) - 1);
                }
                idx1++;
            }

            // the longest non-qualified subarrray(K - 1 distinct numbers) till now: [idx2, i]
            while (freq2.size() >= K) {
                int num = A[idx2];
                if (freq2.get(num) == 1) {
                    freq2.remove(num);
                } else {
                    freq2.put(num, freq2.get(num) - 1);
                }
                idx2++;
            }

            // With idx1 and idx2, it is safe to remove the numbers between [idx1, idx2) but
            // still maintain the qualification state of freq1.
            count += (idx2 - idx1);
        }
        return count;
    }

}
