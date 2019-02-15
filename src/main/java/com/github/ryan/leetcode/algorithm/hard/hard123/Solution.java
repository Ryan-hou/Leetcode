package com.github.ryan.leetcode.algorithm.hard.hard123;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date February 15,2019
 */
public class Solution {

    // dp
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

        // k transactions, on i-th day
        // dp[k, i] = max(dp[k, i - 1], prices[i] - prices[j] + dp[k - 1, j - 1]), j in [0...i - 1]
        // Space complexity O(kn)
        // Time complexity O(kn^2)
        int len = prices.length;
        int[][] dp = new int[3][len];
        for (int k = 1; k <= 2; k++) {
            for (int i = 1; i < len; i++) {
                int min = prices[0];
                for (int j = 1; j <= i; j++) {
                    min = Math.min(min, prices[j] - dp[k - 1][j - 1]);
                }
                dp[k][i] = Math.max(dp[k][i - 1], prices[i] - min);
            }
        }
        return dp[2][len - 1];
    }

    // Time complexity O(kn)
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

        // k transactions, on i-th day
        // dp[k, i] = max(dp[k, i - 1], prices[i] - prices[j] + dp[k - 1, j - 1]), j in [0...i]
        // Space complexity O(kn)
        // Time complexity O(kn)
        int len = prices.length;
        int[][] dp = new int[3][len];
        for (int k = 1; k <= 2; k++) {
            // 优化min的求值，不需要多余的一重循环
            int min = prices[0];
            for (int i = 1; i < len; i++) {
                min = Math.min(min, prices[i] - dp[k - 1][i - 1]);
                dp[k][i] = Math.max(dp[k][i - 1], prices[i] - min);
            }
        }
        return dp[2][len - 1];
    }

    // too smart!
    /**
     * First assume that we have no money, so buy1 means that we have to borrow money from others,
     * we want to borrow less so that we have to make our balance as max as we can(because this is negative).
     *
     * sell1 means we decide to sell the stock, after selling it we have price[i] money
     * and we have to give back the money we owed, so we have price[i] - |buy1| = prices[i ] + buy1, we want to make this max.
     *
     * buy2 means we want to buy another stock, we already have sell1 money,
     * so after buying stock2 we have buy2 = sell1 - price[i] money left, we want more money left, so we make it max
     *
     * sell2 means we want to sell stock2, we can have price[i] money after selling it, and we have buy2 money left before,
     * so sell2 = buy2 + prices[i], we make this max.
     * So sell2 is the most money we can have.
     */
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

        int buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;
        int sell1 = 0, sell2 = 0;
        for (int p : prices) {
            buy1 = Math.max(buy1, -p);
            sell1 = Math.max(sell1, buy1 + p);
            buy2 = Math.max(buy2, sell1 - p);
            sell2 = Math.max(sell2, buy2 + p);
        }
        return sell2;
    }
}
