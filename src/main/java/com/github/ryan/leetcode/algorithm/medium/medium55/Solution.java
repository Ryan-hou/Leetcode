package com.github.ryan.leetcode.algorithm.medium.medium55;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 13,2019
 */
public class Solution {

    // use dp: time complecity -> O(n^2), space complecity -> O(n)
    public boolean canJump(int[] nums) {
        int len = nums.length;
        // dp[i]: can jump to index i
        boolean[] dp = new boolean[len];
        dp[0] = true;
        for (int i = 1; i < len; i++) {
            dp[i] = false;
            for (int j = 0; j < i; j++) {
                if (dp[j] && j + nums[j] >= i) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len - 1];
    }

    // time complexity: O(n), space complexity: O(1)
    public boolean canJump2(int[] nums) {
        int max = 0; // current max index from now
        for (int i = 0; i < nums.length; i++) {
            if (max < i) {
                return false;
            }
            max = Math.max(max, nums[i] + i);
        }
        return true;
    }
}
