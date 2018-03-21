package com.leetcode.ryan.personal.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: SimpleAlgoTest
 * @date January 31,2018
 */
@Slf4j
public class SimpleAlgoTest {

    /**
     * 判断一个数是否是素数（质数）
     * 质数大于等于2 不能被它本身和1以外的数整除
     *
     * 在一般领域，对正整数n，如果用2到sqrt(n)之间的所有整数去除，均无法整除，则n为质数。
     * @param n
     * @return
     */
    public static boolean isPrime(int n) {
        assert n > 1;

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    /**
     * int转为字符串，注意int的不同情况
     * 时间复杂度 O(logn)
     * @param num
     * @return
     */
    public static String intToString(int num) {

        if (num == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        int n = Math.abs(num);
        while (n != 0) {
            sb.append("" + n % 10);
            n /= 10;
        }

        if (num > 0) {
            // 字符串reverse的时间复杂度为O(字符串的长度)，而字符串的长度与while循环的次数一致，
            // 所以时间复杂度为O(logn)
            return sb.reverse().toString();
        } else {
            return "-" + sb.reverse().toString();
        }

    }

    public static double pow(double x, int n) {

        // n = Integer.MIN_VALUE 会导致 overflow
        int num = Math.abs(n);
        double val = powUseRecur(x, num);

        if (n >= 0) {
           return val;
        } else {
            return 1 / val;
        }

    }

    /**
     * 计算x的n次幂，简单直接的做法是n次x相乘，时间复杂度为O(n)，但是存在着重复的计算
     * 可以通过递归的思路把时间复杂度降到O(logn)(在递归函数中只进行一次递归调用，递归深度为 log2N)
     * @param x
     * @param n
     * @return
     */
    private static double powUseRecur(double x, int n) {
        assert n >= 0;

        if (n == 0) {
            return 1;
        }

        double t = powUseRecur(x, n / 2);
        if (n % 2 == 1) {
            return x * t * t;
        } else {
            return t * t;
        }
    }


    public static void main(String[] args) {
//        int n = 7;
//        log.info("{} is prime? {}", n, isPrime(n));
//
//        int num = -7891;
//        log.info("{} intToString = {}", num, intToString(num));

        int x = 2, n = -3;
        log.info("{}^{} = {}", x, n, pow(x, n));
    }
}
