package com.github.ryan.leetcode.algorithm.easy.easy977;

public class Solution {

    public int[] sortedSquares(int[] A) {
        int n = A.length;
        int j = 0;
        while (j < n && A[j] < 0) {
            j++;
        }
        int i = j - 1;

        int[] res = new int[n];
        int idx = 0;
        // two pointer -> i,j
        while (i >= 0 && j < n) {
            if (A[i] * A[i] < A[j] * A[j]) {
                res[idx] = A[i] * A[i];
                idx++;
                i--;
            } else {
                res[idx] = A[j] * A[j];
                idx++;
                j++;
            }
        }

        while (i >= 0) {
            res[idx] = A[i] * A[i];
            idx++;
            i--;
        }
        while (j < n) {
            res[idx] = A[j] * A[j];
            idx++;
            j++;
        }
        return res;
    }

}
