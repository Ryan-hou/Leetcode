package com.github.ryan.algorithm.leetcode.medium.medium120;

import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date December 21,2018
 */
public class Solution {

    // 使用动态规划自底向上处理
    // 时间复杂度O(n^2), 空间复杂度O(n)
    public int minimumTotal(List<List<Integer>> triangle) {
        // from bottom to top, use DP
        if (triangle == null || triangle.size() == 0) return 0;

        int n = triangle.size();
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(n - 1).get(i);
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                int v = triangle.get(i).get(j);
                dp[j] = v + Math.min(dp[j], dp[j + 1]);
            }
        }

        return dp[0];
    }
}
