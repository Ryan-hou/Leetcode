package com.github.ryan.algorithm.leetcode.easy.easy70;

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

    private static int[] memo;

    public static int climbStairs(int n) {
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return caclWays(n);
    }

    // 记忆化搜索－－自顶向下的解决问题（和斐波那契数列问题本质一样）
    private static int caclWays(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        if (memo[n] == -1) {
            memo[n] = caclWays(n - 1) + caclWays(n - 2);
        }
        return memo[n];
    }

    // 动态规划－－自底向上
    public static int climbStairsUseDp(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);

        memo[0] = 1;
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }


    public static void main(String[] args) {
        int n = 60;
        log.info("{} stairs have {} ways to climb.", n, climbStairs(n));
        log.info("{} stairs have {} ways to climb.", n, climbStairsUseDp(n));
    }
}
