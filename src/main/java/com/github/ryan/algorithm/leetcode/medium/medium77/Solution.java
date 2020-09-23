package com.github.ryan.algorithm.leetcode.medium.medium77;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date February 02,2018
 */

/**
 * 组合问题：
 * 树形问题，使用回溯法搜寻每一个解，注意使用剪枝优化计算
 */
@Slf4j
public class Solution {

    private static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> combine(int n, int k) {

        res.clear();
        if (n <= 0 || k <= 0 || k > n) {
            return res;
        }

        List<Integer> c = new ArrayList<>();
        generateCombinations(n, k, 1, c);

        return new ArrayList<>(res);
    }

    // 求解C(n,k)，当前已经找到的组合存储在c中，需要从start开始搜索新的元素
    private static void generateCombinations(int n, int k, int start, List<Integer> c) {

        if (c.size() == k) {
            System.out.println("res add " + c + ", return");

            res.add(new ArrayList<>(c));
            return;
        }

        // i<=n 通过优化达到剪枝的目的
        // 还有 k-c.size() 个空位，所以，［i...n］中至少要有k-c.size()个元素
        // i最多为 n-(k-c.size())+1
        for (int i = start; i <= n - (k - c.size()) + 1; i++) {
            System.out.println("c = " + c + ", add " + i);

            c.add(i);
            generateCombinations(n, k, i + 1, c);
            // 回溯找到一个解后，恢复状态
            c.remove((Integer) i);
        }
        return;
    }


    public static void main(String[] args) {
        int n = 4, k = 2;
        log.info("result = {}", combine(n, k));
    }
}
