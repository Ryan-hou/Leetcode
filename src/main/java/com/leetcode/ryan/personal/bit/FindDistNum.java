package com.leetcode.ryan.personal.bit;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: FindDistNum
 * @date January 24,2018
 */
@Slf4j
public class FindDistNum {


    /**
     * 在一组整数中，除了一个单独的数字外，其余数字都两两一组
     * 思路：
     * 使用异或：两个相同的数字异或结果为0
     * @param nums
     * @return
     */
    public static int findNumFromPair(int[] nums) {
        if (nums == null || nums.length < 3) {
            throw new IllegalArgumentException("Wrong array input");
        }
        int res = 0;
        for (int num : nums) {
           res ^= num;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {22, 22, 44, 44, 99, 99, 6};
        log.info("result = {}", findNumFromPair(nums));
    }

}
