package com.github.ryan.algorithm.leetcode.medium.medium494;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date February 26,2018
 */
@Slf4j
public class Solution {

    /**
     * 使用递归的思路：
     * 自顶向下解决
     *
     * @param nums
     * @param S
     * @return
     */
    public static int findTargetSumWays(int[] nums, int S) {
        assert nums != null && nums.length > 0;
        return findSubTargetSumWays(-1, nums, S);
    }

    // 获取从index位置之后的数组中元素和为s的解法总数
    public static int findSubTargetSumWays(int index, int[] nums, int S) {
        index++;
        if (index == nums.length - 1) {
            if (nums[index] == S && S == 0) {
                // 0为corner case
                return 2;
            } else if (nums[index] == S || -nums[index] == S) {
                return 1;
            } else {
                return 0;
            }
        }

        // 递归树存在重复计算，可以使用记忆化搜索来优化计算
        return findSubTargetSumWays(index, nums, S - nums[index])
                + findSubTargetSumWays(index, nums, S + nums[index]);
    }


    /**
     * 转化问题，使用动态规划自底向上的进行递推
     * <p>
     * subset sum problem(01背包问题的变式)，参见：
     * https://leetcode.com/problems/partition-equal-subset-sum/
     * <p>
     * 如何转化问题？
     * The recursive solution is very slow, because its runtime is exponential
     * The original problem statement is equivalent to:
     * Find a subset of nums that need to be positive, and the rest of them negative,
     * such that sum is equals to target
     * Let P be the positive subset and N be the negative subset
     * sum(P) - sum(N) = target
     * sum(P) + sum(N) + sum(P) - sum(N) = target + sum(nums)
     * sum(P) = (target + sum(nums)) / 2
     * <p>
     * So the original problem has been converted to a subset sum problem as follows:
     * Find a subset of P of nums such that:
     * sum(P) = (target + sum(nums)) / 2
     *
     */
    public static int findTargetSumWaysUseDp(int[] nums, int s) {
        assert (nums != null && nums.length > 0);

        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum < s || ((s + sum) % 2 != 0)) {
            return 0;
        }
        return subsetSum(nums, (s + sum) >>> 1);
    }

    // 在nums数组中选取元素填满容量为s的"包"
    private static int subsetSum(int[] nums, int s) {
        // dp[i]表示填充容量为i的包的方式
        int[] dp = new int[s + 1];
        dp[0] = 1;
        for (int n : nums) {
            for (int i = s; i >= n; i--) {
                dp[i] = dp[i] + dp[i - n];
            }
        }
        return dp[s];
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1};
        int S = 3;
        log.info("Sum ways = {}", findTargetSumWaysUseDp(nums, S));
    }
}
