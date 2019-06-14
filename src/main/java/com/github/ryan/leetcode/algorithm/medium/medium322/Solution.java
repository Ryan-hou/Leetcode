package com.github.ryan.leetcode.algorithm.medium.medium322;

import java.util.Arrays;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 14,2019
 */
public class Solution {

    // use dp
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length < 1) return 0;

        // dp[i] -> fewest number of coins that make up i
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
