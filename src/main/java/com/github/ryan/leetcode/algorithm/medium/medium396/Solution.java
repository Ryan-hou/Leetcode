package com.github.ryan.leetcode.algorithm.medium.medium396;

public class Solution {

    // https://leetcode.com/problems/rotate-function/discuss/319248/Java-1-ms-and-39.4MB-space
    // F(i + 1) - F(i) = sum(B) - n * B(n - 1)
    public int maxRotateFunction(int[] A) {
        if (A == null || A.length < 1) return 0;

        int cur = 0, sum = 0, len = A.length;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            cur += i * A[i];
        }
        int res = cur;
        for (int i = A.length - 1; i >= 0; i--) {
            cur = cur + sum - len * A[i];
            res = Math.max(res, cur);
        }
        return res;
    }
}
