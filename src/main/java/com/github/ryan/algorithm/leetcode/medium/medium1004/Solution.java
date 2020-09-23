package com.github.ryan.algorithm.leetcode.medium.medium1004;

public class Solution {

    // use sliding window
    public int longestOnes(int[] A, int K) {
        int i = 0, j = 0;
        int res = 0;
        while (i < A.length) {
            if (A[i] == 0) {
                K--;
            }

            if (K >= 0) {
                res = Math.max(res, i - j + 1);
            } else {
                if (A[j] == 0) {
                    K++;
                }
                j++;
            }

            i++;
        }
        return res;
    }

}
