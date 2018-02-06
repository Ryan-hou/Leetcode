package com.leetcode.ryan.personal.learn.dp;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Knapsack01
 * @date February 05,2018
 */

/**
 * 0-1背包问题：
 * 状态（对应两个参数）：递归函数的定义（函数干了什么）
 * F(n,C) 考虑将n个物品放进容量为C的背包，使得价值最大
 *
 * 状态转移方程：（递归函数怎么写）
 * F(i,c) = max(F(i-1,c), v(i)+F(i-1,c-w(i)))
 */

@Slf4j
public class Knapsack01 {

    // 记忆化搜索
    private static int[][] memo;

    // 使用记忆化搜索--自顶向下进行递归操作
    public static int knapsack01(final int[] w, final int[] v, final int c) {
        assert w.length == v.length;
        int n = w.length;

        memo = new int[n][c + 1];
        int[] oneDimension = new int[c + 1];
        Arrays.fill(oneDimension, -1);
        Arrays.fill(memo, oneDimension);

        return bestValue(w, v, n - 1, c);
    }

    // 用[0...index]的商品，填充容积为c的背包的最大价值
    private static int bestValue(int[] w, int[] v, int index, int c) {
        if (index < 0 || c <= 0) {
            return 0;
        }

        if (memo[index][c] != -1) {
            return memo[index][c];
        }

        int res = bestValue(w, v, index - 1, c);
        if (c >= w[index]) {
            res = Math.max(res, v[index] + bestValue(w, v, index - 1, c - w[index]));
        }
        memo[index][c] = res;
        return res;
    }


    // 使用动态规划－－自底向上
    // 时间复杂度：O(n*C): O((n-1)*(C+1)) n为物品个数，c为背包容量
    // 空间复杂度：O(n*C): O(n*(C+1))
    public static int knapsack01UseDp(int[] w, int[] v, int c) {
        assert w.length == v.length;
        int n = w.length;
        if (n == 0) {
            return 0;
        }

        int[][] memo = new int[n][c + 1];
        int[] oneDimension = new int[c + 1];
        Arrays.fill(oneDimension, -1);
        Arrays.fill(memo, oneDimension);

        // 只从index＝0的一件商品中选择
        for (int j = 0; j <= c; j++) {
            memo[0][j] = (j >= w[0] ? w[0] : 0);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= c; j++) {
                memo[i][j] = memo[i - 1][j];
                if (j >= w[i]) {
                    memo[i][j] = Math.max(memo[i][j], v[i] + memo[i - 1][j - w[i]]);
                }
            }
        }
        return memo[n - 1][c];
    }

    // 优化01背包问题的空间复杂度：
    // 二维数组的行维由n（n件物品）可以优化为两行，因为每添加一个物品时，memo只与上一行数据有关，可以通过覆盖,只使用两行数组
    // 通过 i%2根据奇偶行来判断如何覆盖数据

    // 进一步优化空间：每一行待计算的数据仅由当前数据和该行左侧的数据决定：max(memo[i-1][j], v[i] + memo[i-i][j-w[i]])
    // 使用一维数组即可，空间复杂度为 O(C) c为背包容量
    public static int knapsack01OptimizeSpace(final int[] w, int[] v, int c) {

        assert w.length == w.length;
        int n = w.length;
        if (n == 0) {
            return 0;
        }

        int[] memo = new int[c + 1];
        Arrays.fill(memo, -1);

        for (int j = 0; j <= c; j++) {
            memo[j] = (j >= w[0] ? v[0] : 0);
        }

        for (int i = 1; i < n; i++) {
            for (int j = c; j >= w[i]; j--) {
                memo[j] = Math.max(memo[j], v[i] + memo[j - w[i]]);
            }
        }
        return memo[c];
    }


    public static void main(String[] args) {
        int[] w = {1, 2, 3};
        int[] v = {6, 10, 12};
        int c = 5;
        log.info("Knapsack's max value = {}", knapsack01(w, v, c));
        log.info("Knapsack's max value = {}", knapsack01UseDp(w, v, c));
        log.info("Knapsack's max value = {}", knapsack01OptimizeSpace(w, v, c));
    }
}
