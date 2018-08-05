package com.github.ryan.leetcode.algorithm.easy.easy121;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date February 24,2018
 */
@Slf4j
public class Solution {

    /**
     * 思路：
     *
     * We need to find the largest peak following the smallest valley.
     * We can maintain two variables - minprice and maxprofit corresponding
     * to the smallest valley and maximum profit(maximum difference between selling
     * price and minprice) obtained so far respectively.
     *
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        assert prices != null;

        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        log.info("Max profit = {}", maxProfit(prices));
    }


}
