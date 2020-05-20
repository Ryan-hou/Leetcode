package com.github.ryan.leetcode.algorithm.medium.medium201;

public class Solution {

    // Goal is to get the MSB of both m and n, we keep shifting m and n to the RIGHT
    // until they are same and keep a track how many times we shifted the integers.
    // Then shift either m or n to the LEFT by counter times.
    public int rangeBitwiseAnd(int m, int n) {
        int count = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            count++;
        }
        return m << count;
    }
}
