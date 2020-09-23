package com.github.ryan.algorithm.leetcode.medium.medium300;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date February 06,2018
 */
@Slf4j
public class Solution {

    /**
     * 使用动态规划－－自底向上进行递推
     * 时间复杂度 O(n^2)
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        // memo[i]表示以nums[i]为结尾的最长上升子序列的长度
        int[] memo = new int[nums.length];
        Arrays.fill(memo, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    memo[i] = Math.max(memo[i], 1 + memo[j]);
                }
            }
        }

        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            res = Math.max(res, memo[i]);
        }
        return res;
    }


    public static void main(String[] args) {

        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        log.info("Nums {}'s length of LIS is {}", Arrays.toString(nums), lengthOfLIS(nums));
    }
}
