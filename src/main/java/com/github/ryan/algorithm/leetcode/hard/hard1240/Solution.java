package com.github.ryan.algorithm.leetcode.hard.hard1240;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    /**
     * key -> hash the histogram(skyline array) to a number
     * value -> tile count for current skyline array
     */
    private Map<Long, Integer> dict = new HashMap<>();

    private int res = Integer.MAX_VALUE;


    /**
     * The basic idea is to fill the entire block bottom up.
     * In every step, find the lowest left unfilled square first,
     * and select a square with different possible sizes to fill it.
     * We maintain a height array (skyline) with length n while dfs.
     * This skyline is the identity of the state.
     * The final result we ask for is the minimum number of squares
     * for the state [m, m, m, ....m] (The length of this array is n).
     */
    public int tilingRectangle(int n, int m) {
        if (n == m) return 1;
        if (n > m) return tilingRectangle(m, n);
        dfs(n, m, new int[n], 0);
        return res;
    }


    private void dfs(int n, int m, int[] h, int cnt) {
        if (cnt >= res) return;
        int pos = -1, minH = Integer.MAX_VALUE;
        long histo = 0, base = m + 1;
        for (int i = 0; i < n; i++) {
            histo = histo * base + h[i];
            if (h[i] < minH) {
                pos = i;
                minH = h[i];
            }
        }

        // skyline is [m, m, m, ... m]
        if (minH == m) {
            res = Math.min(res, cnt);
            return;
        }

        if (dict.containsKey(histo) && dict.get(histo) <= cnt) {
            return;
        }

        dict.put(histo, cnt);
        int end = pos;
        while (end + 1 < n
                && h[end + 1] == h[pos]
                && (end + 1 - pos + 1 + minH) <= m) {
            end++;
        }

        for (int i = end; i >= pos; i--) {
            // shallow copy, but array h stores basic data type's data
            int[] next = h.clone();
            for (int j = pos; j <= i; j++) {
                next[j] += i - pos + 1; // new added square edge length
            } // end for
            dfs(n, m, next, cnt + 1);
        }
    }

}
