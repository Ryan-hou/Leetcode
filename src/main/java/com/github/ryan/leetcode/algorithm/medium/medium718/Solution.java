package com.github.ryan.leetcode.algorithm.medium.medium718;

public class Solution {

    public int findLength(int[] A, int[] B) {
        int res = 0;
        // dp[i][j] -> A[i...] and B[j...]'s repeated subarray
        int[][] dp = new int[A.length + 1][B.length + 1];
        for (int i = A.length - 1; i >= 0; i--) {
            for (int j = B.length - 1; j >= 0; j--) {
                if (A[i] == B[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                    if (res < dp[i][j]) res = dp[i][j];
                }
            }
        }
        return res;
    }

}
