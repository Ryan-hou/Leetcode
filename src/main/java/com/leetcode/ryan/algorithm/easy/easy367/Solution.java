package com.leetcode.ryan.algorithm.easy.easy367;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date February 08,2018
 */
@Slf4j
public class Solution {

    /**
     * 思路一：使用数学性质：n^2 = 1 + 3 + 5 + .... + (2n - 1)
     * 时间复杂度 O(n)
     * @param num
     * @return
     */
    public static boolean isPerfectSquare(int num) {
        assert num > 0;

        int i = 1;
        while (num > 0) {
          num -= i;
          i += 2;
        }
        return num == 0;
    }

    /**
     * 思路二：
     * 从1开始到num，依次判断每个数的平方是否等于num，若大于num，则直接退出返回False。但是这样会存在溢出的情况，且运算速度较慢。
     * 可以采用二分法来改善时间复杂度。同时我们可以找出在int范围内的最大的平方根，为46340
     * @param num
     * @return
     */
    public static boolean isPerfectSquare2(int num) {
        int lo = 1;
        int hi = 46340;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (mid * mid < num) {
                lo = mid + 1;
            } else if (mid * mid > num) {
                hi = mid - 1;
            } else {
                // mid*mid=num
                return true;
            }
        }
        return false;
    }

    /**
     * 使用牛顿迭代法：
     * 主要就是切线逼近的思想，将 x^2 = n 转化为 x^2 - n = 0 的方式来求解，利用切线一直逼近方程的解
     * x(n+1) = x(n) - f(x(n))/f'(x(n)), 通过迭代，该式必然在f（x*）=0的时候收敛
     *
     * 牛顿迭代法参考：
     * http://blog.csdn.net/luoleicn/article/details/6527049
     * @param num
     * @return
     */
    public static boolean isPerfectSquare3(int num) {
        long x = num;
        while (x * x > num) {
            x = (x + num / x) >> 1;
        }
        return x * x == num;
    }


    public static void main(String[] args) {
        int n = 35;
        log.info("{} is perfect square ? {}", n, isPerfectSquare3(n));
    }
}
