package com.github.ryan.leetcode.algorithm.easy.easy371;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 18,2019
 */
public class Solution {

    // 该位运算对于正负数均成立，负数时可以举例子验证
    public int getSum(int a, int b) {

        while (b != 0) {
            int carry = a & b;
            a ^= b;
            b = carry << 1;
        }
        return a;
    }
}
