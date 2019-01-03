package com.github.ryan.leetcode.algorithm.easy.easy121;

import lombok.extern.slf4j.Slf4j;

import java.util.PriorityQueue;

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

    // 方法二：
    // 使用最大堆，从数组末尾向前遍历，依次计算当前元素和后面最大元素的差值
    // 时间复杂度O(nlogn) 空间复杂度O(n)
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;

        int max = 0, n = prices.length;
        PriorityQueue<Integer> pq
                = new PriorityQueue<>(prices.length, (o1, o2) -> (o2 - o1));
        pq.add(prices[n - 1]);

        for (int i = n - 2; i >= 0; i--) {
            int curMax = pq.peek() - prices[i];
            if (curMax > max) {
                max = curMax;
            }
            pq.add(prices[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        log.info("Max profit = {}", maxProfit(prices));
    }


}
