package com.github.ryan.leetcode.algorithm.medium.medium845;

public class Solution {

    public int longestMountain(int[] A) {
        int n = A.length;
        int res = 0, base = 0;
        while (base < n) {
            int end = base;
            // if base is a left-boundary
            if (end + 1 < n && A[end] < A[end + 1]) {
                // set end to the peak of this potential mountain
                while (end + 1 < n && A[end] < A[end + 1]) {
                    end++;
                }

                // if end is really a peak
                if (end + 1 < n && A[end] > A[end + 1]) {
                    // set end to the right-boundary of mountain
                    while (end + 1 < n && A[end] > A[end + 1]) {
                        end++;
                    }
                    // record candidate answer
                    res = Math.max(res, end - base + 1);
                }
            }

            base = Math.max(end, base + 1);
        }
        return res;
    }

}
