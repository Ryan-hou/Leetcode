package com.github.ryan.leetcode.algorithm.easy.easy268;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 11,2019
 */
public class Solution {

    public int missingNumber(int[] nums) {
        int len = nums.length;
        // sum = 1 + 2 + 3 + ... + len
        int sum = ((len + 1) * len ) / 2;
        for (int n : nums) {
            sum -= n;
        }
        return sum;
    }
}
