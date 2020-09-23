package com.github.ryan.algorithm.leetcode.easy.easy53;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date February 23,2018
 */
@Slf4j
public class Solution {

    /**
     * 思路：自底向上使用动态规划
     *
     * The maximum is initially A[0]. Suppose we've solved the problem
     * for A[1...i-1];how can we extend that to A[1...i]? The maximum sum
     * in the first i elements is either the maximum sum in the first
     * i - 1 elements(which we'll call maxSoFar), or it is that of a
     * subvector that ends in position i(which we'll call maxEndHere)
     *
     * maxEndHere is either A[i] plus the previous maxEndHere, or just A[i], whichever is larger
     *
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        assert nums != null && nums.length >= 1;

        int maxSoFar = nums[0], maxEndHere = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxEndHere = Math.max(maxEndHere + nums[i], nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndHere);
        }
        return maxSoFar;
    }

    // 根据上面思路，优化代码，使执行更快
    public static int maxSubArray2(int[] nums) {
        assert nums != null;

        int maxEndHere = 0, maxSoFar = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            maxEndHere += nums[i];
            if (maxEndHere > maxSoFar) maxSoFar = maxEndHere;
            if (maxEndHere < 0) maxEndHere = 0;
        }
        return maxSoFar;
    }

    public static void main(String[] args) {

        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        log.info("Max subArray = {}", maxSubArray2(nums));
    }
}
