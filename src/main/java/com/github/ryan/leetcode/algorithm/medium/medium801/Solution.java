package com.github.ryan.leetcode.algorithm.medium.medium801;

import java.util.Arrays;

public class Solution {

    // use dp
    public int minSwap(int[] A, int[] B) {
        int len = A.length;
        // swap[i] -> swap nums that
        // swap A[i] and B[i] and make A[0...i] and B[0...i] correct
        int[] swap = new int[len];
        // nonswap[i] -> don't swap A[i] and B[i]
        int[] nonswap = new int[len];
        Arrays.fill(swap, Integer.MAX_VALUE);
        Arrays.fill(nonswap, Integer.MAX_VALUE);
        // initialize
        swap[0] = 1;
        nonswap[0] = 0;
        for (int i = 1; i < len; i++) {
            if (A[i - 1] < A[i] && B[i - 1] < B[i]) {
                nonswap[i] = nonswap[i - 1]; // don't swap A[i - 1] and A[i]
                swap[i] = swap[i - 1] + 1; // swap A[i - 1] and A[i]
            }
            // use Math.min to compare with the first if condition
            if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
                // swap A[i] and don't swap A[i - 1]
                swap[i] = Math.min(swap[i], nonswap[i - 1] + 1);
                // swap A[i - 1] and don't swap A[i]
                nonswap[i] = Math.min(nonswap[i], swap[i - 1]);
            }
        }
        return Math.min(swap[len - 1], nonswap[len - 1]);
    }
}
