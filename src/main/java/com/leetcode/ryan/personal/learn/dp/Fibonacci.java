package com.leetcode.ryan.personal.learn.dp;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Fibonacci
 * @date February 05,2018
 */

/**
 * 动态规划：
 * Dynamic programming(aka dynamic optimization) is a method for solving a complex
 * problem by breaking it down into a collection of simpler subproblems, solving each
 * of those subproblems just once, and storing their solutions - ideally, using a
 * memory-based data structure.
 * 将原问题拆解成若干子问题，同时保存子问题的答案，使得每个子问题只求解一次，最终获得原问题的答案
 *
 * 递归问题 －－－ 重叠子问题(最优子结构) －－ 1）记忆化搜索(自顶向下的解决问题) OR 2）动态规划（自底向上的解决问题）
 * 通常情况下，自顶向下的思考问题容易些，我们一般可以先自顶向下的思考问题的解，然后再转为自底向上的动态规划
 */

@Slf4j
public class Fibonacci {

    private static int[] memo;

    // 最简单直接的解法，使用递归，自顶向下
    public static int fib1(int n) {

        if (n == 0) {
            return 0;
        }

        if (n == 1) {
           return 1;
        }

        return fib1(n - 1) + fib1(n - 2);
    }

    // 使用记忆化搜索优化fib1，减少大量的重复计算(自顶向下)
    public static int fib2(int n) {

        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        if (memo[n] == -1) {
            memo[n] = fib2(n - 1) + fib2(n - 2);
        }
        return memo[n];
    }

    // 动态规划 －－ 自底向上
    public static int fib3(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);

        memo[0] = 0;
        memo[1] = 1;
        for (int i = 2; i <= n ; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }


    public static void main(String[] args) {
//        int n = 45;
//
//        memo = new int[n + 1];
//        Arrays.fill(memo, -1);
//
//        log.info("fib {} = {}", n, fib1(n));
//        log.info("fib {} = {}", n, fib2(n));
        int n = 1000;
        log.info("fib {} = {}", n, fib3(n));


    }

}
