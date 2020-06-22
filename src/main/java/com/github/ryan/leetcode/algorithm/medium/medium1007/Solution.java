package com.github.ryan.leetcode.algorithm.medium.medium1007;

public class Solution {

    public int minDominoRotations(int[] A, int[] B) {
        int rot = minRotations(A[0], A, B);
        if (rot != -1 || A[0] == B[0]) {
            return rot;
        } else {
            return minRotations(B[0], A, B);
        }
    }

    private int minRotations(int x, int[] a, int[] b) {
        int aRot = 0;
        int bRot = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != x && b[i] != x) return -1;

            if (a[i] != x) {
                // b[i] == x
                aRot++;
            } else if (b[i] != x) {
                // b[i] != x and a[i] == x
                bRot++;
            }
        }
        return Math.min(aRot, bRot);
    }

}
