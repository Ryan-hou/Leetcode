package com.github.ryan.leetcode.algorithm.medium.medium986;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if (A == null || B == null) return null;

        List<int[]> res = new ArrayList<>();
        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            int low = Math.max(A[i][0], B[j][0]);
            int high = Math.min(A[i][1], B[j][1]);
            if (low <= high) {
                res.add(new int[] { low, high });
            }

            if (A[i][1] < B[j][1]) {
                i++;
            } else if (A[i][1] > B[j][1]) {
                j++;
            } else {
                // A[i][1] == B[j][1]
                i++;
                j++;
            }
        }

        return res.toArray(new int[res.size()][]);
    }

}
