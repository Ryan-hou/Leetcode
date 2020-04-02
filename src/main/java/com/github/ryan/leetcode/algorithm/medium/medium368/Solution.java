package com.github.ryan.leetcode.algorithm.medium.medium368;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums == null || nums.length < 1) return new ArrayList<>();

        Arrays.sort(nums);
        int len = nums.length;
        // dp[i] is the length of the largest subset from nums[0] to nums[i]
        int[] dp = new int[len];
        Arrays.fill(dp, 1);

        int maxLen = 1, index = 0;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                index = i;
            }
        }

        List<Integer> res = new ArrayList<>();
        int last = nums[index];
        res.add(last);
        maxLen--;
        for (int i = index - 1; i >= 0; i--) {
            if (last % nums[i] == 0 && dp[i] == maxLen) {
                res.add(nums[i]);
                maxLen--;
            }
        }
        return res;
    }

}
