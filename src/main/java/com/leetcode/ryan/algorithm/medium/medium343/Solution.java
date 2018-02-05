package com.leetcode.ryan.algorithm.medium.medium343;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date February 05,2018
 */
@Slf4j
public class Solution {

    // 使用动态规划递推，自底向上
    public static int integerBreakUseDp(int n) {
        assert n >= 2;

        // memo[i]表示将数字i分割（至少分割成两部分）后得到的最大乘积
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);

        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            // 求解memo[i]
            for (int j = 1; j <= i - 1; j++) {
                // j + (i - j)
                memo[i] = max3(memo[i], j * (i - j), j * memo[i - j]);
            }
        }

        return memo[n];
    }

    private static int[] memo;

    // 记忆化搜索--自顶向下使用递归
    public static int integerBreak(int n) {
        assert n >= 2;
        memo = new int[n + 1];
        Arrays.fill(memo, -1);

        return breakInteger(n);
    }

    // 将n进行分割(至少分割两部分)，可以获得的最大乘积
    private static int breakInteger(int n) {
        if (n == 1) {
            return 1;
        }

        if (memo[n] != -1) {
            return memo[n];
        }

        int res = -1;
        for (int i = 1; i <= n -1 ; i++) {
            // i + (n-i)
            res = max3(res, i * (n - i), i * breakInteger(n - i));
        }
        memo[n] = res;
        return res;
    }

    private static int max3(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    public static void main(String[] args) {
        int n = 10;
        log.info("Break {}'s max multiply value = {}.", n, integerBreak(n));
        log.info("Break {}'s max multiply value = {}.", n, integerBreakUseDp(n));
    }
}
