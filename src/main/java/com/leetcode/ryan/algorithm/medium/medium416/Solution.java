package com.leetcode.ryan.algorithm.medium.medium416;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date February 06,2018
 */

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 01背包问题的变种：
 * 从给定数组中，选取未知个数的元素，填满容量为（sum/2）的背包
 */
@Slf4j
public class Solution {

    // memo[i][c] 表示使用索引为[0...i]的这些元素，是否可以完全填充一个容量为c的背包
    // -1表示未计算，0表示不可以填充，1表示可以填充
    private static int[][] memo;

    // 使用记忆化搜索--采用递归，自顶向下解决问题
    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n = 0; n < nums.length; n++) {
            sum += nums[n];
        }

        if (sum % 2 != 0) {
            return false;
        }

        memo = new int[nums.length][sum / 2 + 1];
        int[] oneDimension = new int[sum / 2 + 1];
        Arrays.fill(oneDimension, -1);
        Arrays.fill(memo, oneDimension);
        return tryPartition(nums, nums.length - 1, sum / 2);
    }

    // 使用nums[0...index]，是否可以完全填充一个容量为sum的背包
    private static boolean tryPartition(final int[] nums, int index, int sum) {
        if (sum == 0) {
            return true;
        }
        // 背包装不下了或者物品没有了
        if (sum < 0 || index < 0) {
            return false;
        }

        if (memo[index][sum] != -1) {
            // memo[index][sum]值为1表示可以填充
            return memo[index][sum] == 1;
        }

        memo[index][sum] =
                (tryPartition(nums, index - 1, sum)
                || tryPartition(nums, index - 1, sum - nums[index])) ? 1 : 0;
        return memo[index][sum] == 1;
    }

    // 使用动态规划－－采用递推（从小问题到大问题进行递推），自底向上的解决问题
    // 时间复杂度 O(n*C): C为背包容量，即 sum/2;n为物品个数，即nums的个数
    // 空间复杂度：使用一维数组，O(C)

    // 思路：F(n,C)表示考虑将n个物品填满容量为C的背包，状态转移方程为: F(i,c) = F(i-1,c) || F(i-1,c-w(i))
    public static boolean canPartitionUseDp(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            assert nums[i] > 0;
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }

        int n = nums.length;
        int C = sum / 2;
        // memo[i] 表示数组是否可以填充容量为i的背包
        boolean[] memo = new boolean[C + 1];

        for (int i = 0; i <= C; i++) {
            // 只考虑第一个物品看能否把容量为i的背包填满
            memo[i] = (nums[0] == i);
        }

        for (int i = 1; i < n; i++) {
            for (int j = C; j >= nums[i]; j--) {
                memo[j] = memo[j] || memo[j - nums[i]];
            }
        }
        return memo[C];
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        log.info("nums {} has partition result? {}", Arrays.toString(nums), canPartition(nums));
        log.info("nums {} has partition result? {}", Arrays.toString(nums), canPartitionUseDp(nums));
    }
}
