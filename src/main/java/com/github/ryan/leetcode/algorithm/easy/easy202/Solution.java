package com.github.ryan.leetcode.algorithm.easy.easy202;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date March 12,2018
 */
@Slf4j
public class Solution {

    /**
     * 思路：找到循环终止条件：
     * 1）一遍遍迭代直到找到和为1的情况，返回 true
     * 2）用set存储每一次计算的和，当出现重复时，即接下来会是之前计算的循环，返回 false
     * @param n
     * @return
     */
    public static boolean isHappy(int n) {
        assert n > 0;

        Set<Integer> findSet = new HashSet<>();

        while (findSet.add(n)) {
            int digitSum = 0;
            while (n != 0) {
                int digit = n % 10;
                digitSum += digit * digit;
                n = n / 10;
            }
            if (digitSum == 1) {
                return true;
            }
            n = digitSum;
        }
        return false;
    }

    public static void main(String[] args) {
        int num = 19;
        log.info("{} is happy number ? {}", num, isHappy(num));
    }
}
