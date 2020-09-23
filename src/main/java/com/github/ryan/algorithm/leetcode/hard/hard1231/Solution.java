package com.github.ryan.algorithm.leetcode.hard.hard1231;

public class Solution {

    public int maximizeSweetness(int[] sweetness, int K) {
        // Binary Search
        int lo = 0;
        int hi = 0;
        for (int s : sweetness) {
            hi += s;
        }

        while (lo <= hi) {
            int level = lo + (hi - lo) / 2;
            // hypothesize that we can cut into at least `k + 1`  parts, each has `level` or more sum of sweetness.
            if (canCutByK(sweetness, level, K + 1)) {
                lo = level + 1;
            } else {
                hi = level - 1;
            }
        }
        return lo - 1; // the max level
    }

    private boolean canCutByK(int[] sweetness, int level, int pieces) {
        int cnt = 0;
        int sum = 0;
        for (int i = 0; i < sweetness.length; i++) {
            sum += sweetness[i];
            if (sum >= level) {
                cnt++;
                sum = 0;
            }
        }
        return cnt >= pieces;
    }

}
