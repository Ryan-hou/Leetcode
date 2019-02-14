package com.github.ryan.leetcode.algorithm.easy.easy122;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date February 14,2019
 */
public class Solution {

    // Space complexity: O(n). Depth of recursion is n
    // Time complexity: O(n^n). Recursive funciton is called n^n times.
    public int maxProfit(int[] prices) {
        return calculate(prices, 0);
    }

    // [s...len) 的最大收益
    public int calculate(int[] prices, int s) {
        // 递归出口
        if (s >= prices.length) return 0;

        int max = 0;
        for (int start = s; start < prices.length; start++) {
            int maxProfit = 0;
            for (int i = start + 1; i < prices.length; i++) {
                if (prices[start] < prices[i]) {
                    int profit = calculate(prices, i + 1) + prices[i] - prices[start];

                    if (profit > maxProfit) {
                        maxProfit = profit;
                    }
                }
            }

            if (maxProfit > max) {
                max = maxProfit;
            }
        }
        return max;
    }

    // The key point is we need to consider every peak immediately following
    // a valley to maximize the profit
    // Time complexity: O(n). Single pass.
    // Space complexity: O(1). Constant space required.
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int i = 0;
        int maxProfit = 0;
        int valley, peak;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            valley = prices[i];

            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            peak = prices[i];

            maxProfit += (peak - valley);
        }
        return maxProfit;
    }
}
