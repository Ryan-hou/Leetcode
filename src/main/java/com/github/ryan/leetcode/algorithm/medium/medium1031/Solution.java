package com.github.ryan.leetcode.algorithm.medium.medium1031;

public class Solution {

    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int len = A.length;
        // sum[i] -> sum of A[0] + ... + A[i]
        int[] sum = new int[len];
        sum[0] = A[0];
        for (int i = 1; i < len; i++) {
            sum[i] = sum[i - 1] + A[i];
        }

        int LMax = sum[L - 1];
        int MMax = sum[M - 1];
        int res = 0;
        for (int i = 0; i < len; i++) {
            if (i >= L && i + M - 1 < len) {
                res = Math.max(res, LMax + (sum[i + M - 1] - sum[i - 1]));
            }
            if (i >= M && i + L - 1 < len) {
                res = Math.max(res, MMax + (sum[i + L - 1] - sum[i - 1]));
            }

            if (i >= L) {
                LMax = Math.max(LMax, sum[i] - sum[i - L]);
            }
            if (i >= M) {
                MMax = Math.max(MMax, sum[i] - sum[i - M]);
            }
        }
        return res;
    }

}
