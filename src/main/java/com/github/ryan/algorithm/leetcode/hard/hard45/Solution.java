package com.github.ryan.algorithm.leetcode.hard.hard45;

import java.util.Arrays;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 22,2019
 */
public class Solution {

    // use dp
    // space complexity: O(n)
    // time complexity: O(n^2)
    public int jump(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;

        int len = nums.length;
        // dp[i] -> jump to nums[i]'s minimum value
        int[] dp = new int[len];
        Arrays.fill(dp, len);
        dp[0] = 0;
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] < nums[i - 1]) continue;
            for (int j = 1; j <= nums[i] && i + j < len; j++) {
                dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
            }
        }
        return dp[len - 1];
    }
}
