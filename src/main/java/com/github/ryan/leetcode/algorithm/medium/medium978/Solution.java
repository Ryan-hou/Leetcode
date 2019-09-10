package com.github.ryan.leetcode.algorithm.medium.medium978;

public class Solution {

    public int maxTurbulenceSize(int[] A) {
        if (A == null || A.length < 1) return 0;
        int len = A.length;
        // valley[i] -> max turbulent end by i and A[i - 1] > A[i]
        int[] valley = new int[len];
        // peak[i] -> max turbulent end by i and A[i - 1] < A[i]
        int[] peak = new int[len];
        valley[0] = 1;
        peak[0] = 1;
        int res = 1;
        for (int i = 1; i < len; i++) {
            valley[i] = A[i - 1] > A[i] ? peak[i - 1] + 1 : 1;
            peak[i] = A[i - 1] < A[i] ? valley[i - 1] + 1 : 1;
            res = Math.max(res, Math.max(valley[i], peak[i]));
        }
        return res;
    }

}
