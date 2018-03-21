package com.leetcode.ryan.algorithm.medium.medium50;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date March 21,2018
 */
@Slf4j
public class Solution {

    public static double myPow(double x, int n) {
        // 递归出口
        if (n == 0) {
          return 1;
        }
        // overflow-conscious x^n=(1/x)*x^(n+1)
        if (n == Integer.MIN_VALUE) {
            return 1 / x * myPow(x, n + 1);
        }
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }

        return n % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
    }

    public static void main(String[] args) {
        double x = 2.0;
        int n = -2;
        log.info("{} pow {} = {},", x, n, myPow(x, n));
    }
}
