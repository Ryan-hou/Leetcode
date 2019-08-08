package com.github.ryan.leetcode.algorithm.medium.medium29;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 08,2019
 */
public class Solution {

    /**
     * 将10进制的计算方案 移到 二进制
     * 15/3  -> 15: 1111  3: 11
     * 左边第一位开始 1 < 11 结果: 位置0 余数 1
     * 第二位 余数*2 + 1 = 11 = 11 结果: 位置 1 余数0 以此类推
     * Integer 的最小值 的绝对值大于最大值  转为long类型
     */
    public int divide(int dividend, int divisor) {
        if (divisor == 1 || dividend == 0) {
            return dividend;
        }
        if (divisor == -1 && dividend != Integer.MIN_VALUE) {
            return -dividend;
        }

        boolean flag = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);
        long dividendL = Math.abs((long) dividend);
        long divisorL = Math.abs((long) divisor);
        long res = 0;
        // remainder
        long left = 0;
        for (int i = 31; i >= 0; i--) {
            long tmp = (dividendL >> i) & 1;
            long sum = (left << 1) + tmp;
            if (sum >= divisorL) {
                left = sum - divisorL;
                res += (1L << i);
            } else {
                left = sum;
            }
        }
        res = flag ? -res : res;
        return res > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) res;
    }

}
