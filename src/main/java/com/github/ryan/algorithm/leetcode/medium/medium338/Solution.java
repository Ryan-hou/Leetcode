package com.github.ryan.algorithm.leetcode.medium.medium338;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date April 09,2019
 */
public class Solution {

    // O(n*sizeof(integer))
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            res[i] = countBitNum(i);
        }
        return res;
    }

    private int countBitNum(int num) {
        int count = 0;
        while (num != 0) {
            if ((num & 1) == 1) {
                count++;
            }
            num >>>= 1;
        }
        return count;
    }

    // Use DP: O(n)
    public int[] countBits2(int num) {
        int[] dp = new int[num + 1];
        if (num == 0) return dp;
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= num; i++) {
            dp[i] = (i % 2) + dp[i >> 1];
        }
        return dp;
    }
}
