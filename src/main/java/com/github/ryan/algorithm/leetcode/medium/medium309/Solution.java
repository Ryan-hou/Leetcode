package com.github.ryan.algorithm.leetcode.medium.medium309;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date May 24,2019
 */
public class Solution {

    // use dp
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        // dp[i][0]: max profit till day i, last transaction is buy
        // dp[i][1]: max profit till day i, last transaction is sell
        int len = prices.length;
        int[][] dp = new int[len][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < len; i++) {
            // i from 1, so i - 1 is ok in array, but i - 2 is a corner case
            dp[i][1] = Math.max(dp[i - 1][1], prices[i] + dp[i - 1][0]);
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i] + (i - 2 < 0 ? 0 : dp[i - 2][1]));
        }

        return dp[len - 1][1];
    }
}
