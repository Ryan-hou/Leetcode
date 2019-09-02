package com.github.ryan.leetcode.algorithm.medium.medium775;

public class Solution {

    // the local inversion is a special case of global inversion.
    // If we can find a global inversion which is not local inversion, then the permutation is not ideal.
    // In other words, we need to check whether there exists i and j where j - i > 1 and A[i] > A[j].
    public boolean isIdealPermutation(int[] A) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < A.length - 2; i++) {
            max = Math.max(max, A[i]);
            if (A[i + 2] < max) {
                return false;
            }
        }
        return true;
    }

}
