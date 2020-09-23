package com.github.ryan.algorithm.leetcode.hard.hard312;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 18,2019
 */
public class Solution {

    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int len = nums.length;
        // dp[i][j] -> nums[i...j]'s max coin
        int[][] dp = new int[len][len];
        for (int l = 1; l <= len; l++) {
            for (int start = 0; start <= len - l; start++) {
                int end = start + l - 1;
                for (int i = start; i <= end; i++) {
                    int prev = (start - 1 < 0 ? 1 : nums[start - 1]);
                    int next = (end + 1 >= len ? 1 : nums[end + 1]);
                    int coins = nums[i] * prev * next;
                    coins += (i == start ? 0 : dp[start][i - 1]);
                    coins += (i == end ? 0 : dp[i + 1][end]);
                    dp[start][end] = Math.max(dp[start][end], coins);
                }
            }
        }
        return dp[0][len - 1];
    }

}
