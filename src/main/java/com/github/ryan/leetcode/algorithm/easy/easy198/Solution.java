package com.github.ryan.leetcode.algorithm.easy.easy198;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date February 05,2018
 */

/**
 * 注意其中对于状态的定义（也就是递归函数的定义）：
 * 考虑偷取[x...n-1]范围里的房子（函数的定义）
 * 根据对状态的定义，决定状态的转移：
 * f(0) = max {v(0) + f(2), v(1) + f(3), v(2) + f(4), ...,
 *      v(n-3) + f(n-1), v(n-2), v(n-1)} (状态转移方程)
 *
 * 状态实际上就是定义了我们的函数(递归函数)要做什么，
 * 而状态转移定义了我们的函数怎么做，也就是递归函数怎么写
 */
@Slf4j
public class Solution {

    // memo[i] 表示考虑抢劫nums[i...n-1]所能获得的最大收益
    private static int[] memo;

    // 考虑抢劫num[index...nums.size()-1] 这个范围的所有房子
    private static int tryRob(int[] nums, int index) {
        if (index >= nums.length) {
            return 0;
        }

        if (memo[index] != -1) {
            return memo[index];
        }

        int res = 0;
        for (int i = index; i < nums.length; i++) {
            res = Math.max(res, nums[i] + tryRob(nums, i + 2));
        }
        memo[index] = res;
        return res;
    }

    // 使用记忆化搜索－－自顶向下的递归
    public static int rob(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return tryRob(nums, 0);
    }

    // 使用动态规划－－递推，自底向上
    public static int robUseDp(int[] nums) {
       int n = nums.length;
        if (n == 0) {
            return 0;
        }

        // memo[i]表示考虑抢劫nums[i...n-1]所能获得的最大收益
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        memo[n - 1] = nums[n - 1];
        for (int i = n - 2; i >=0; i--) {
            //memo[i]
            for (int j = i; j < n; j++) {
                memo[i] = Math.max(memo[i], nums[j] + (j + 2 < n ? memo[j + 2] : 0));
            }
        }
        return memo[0];
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 1, 2, 3};
        log.info("Rob {} get max value = {}", Arrays.toString(nums), rob(nums));
        log.info("Rob {} get max value = {}", Arrays.toString(nums), robUseDp(nums));
    }
}
