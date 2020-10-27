package com.github.ryan.algorithm.leetcode.easy.easy905;

public class Solution {

    /**
     * One pass solution: use two pointers
     * @param A
     * @return
     */
    public int[] sortArrayByParity(int[] A) {
        int l = 0, r = A.length - 1;
        while (l < r) {
            if ((A[l] & 1) == 0) {
                // A[l] is even
                l++;
            } else {
                // A[l] is odd

                while (l < r && ((A[r] & 1) == 1)) {
                    // A[r] is odd
                    r--;
                }

                if (l < r) {
                    // A[r] is even
                    int tmp = A[r];
                    A[r] = A[l];
                    A[l] = tmp;
                    l++;
                    r--;
                }
            }
        }
        return A;
    }

}
