package com.github.ryan.personal.algorithm.learn.backtracking;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 *
 * @className Knapsack01
 * @date December 25,2018
 */
@Slf4j
public class Knapsack01 {

    /**
     * 问题描述：
     * 我们有一个背包，背包总的承载重量是 Wkg。现在我们有 n 个物品，
     * 每个物品的重量不等，并且不可分割。我们现在期望选择几件物品，装载到背包中。
     * 在不超过背包所能装载重量的前提下，如何让背包中物品的总重量最大？
     */
    private int max;
    // items为物品数据，值为物品的重量;w为背包的容量
    public int getMaxWeight(int[] items, int w) {
        max = Integer.MIN_VALUE;
        _maxWeight(0, w, items, items.length, 0);
        return max;
    }

    // max为背包最大能装的物品重量;cw为当前已经装进去的物品重量;n为物品数量;i为当前要考虑的物品下标;
    private void _maxWeight(int cw, int w, int[] items, int n, int i) {
        // 递归出口
        if (i == n || cw == w) {
            if (cw > max) {
                max = cw;
            }
            return;
        }
        _maxWeight(cw, w, items, n, i + 1); // 不考虑第i个物品
        if (cw + items[i] <= w) {
            // 已经超过可以背包承受的重量的时候，就不要再装了
            _maxWeight(cw + items[i], w, items, n, i + 1);
        }

    }

    public static void main(String[] args) {
        int[] items = {5, 6, 4, 2, 3};
        int w = 14;
        log.info("Max wight = {}", new Knapsack01().getMaxWeight(items, w));
    }

}
