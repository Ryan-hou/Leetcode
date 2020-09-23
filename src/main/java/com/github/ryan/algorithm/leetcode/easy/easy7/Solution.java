package com.github.ryan.algorithm.leetcode.easy.easy7;

/**
 * @author Ryan-hou
 * @description:
 * @className: Solution
 * @date March 07,2017
 */
public class Solution {

    /**
     * 法一:
     * 问题比较简单,考查了 int 类型的取值范围以及如何处理越界
     * 直观的做法,用long类型来保存越界的int类型
     * @param args
     */
    public static int reverseOne(int x) {
        String reverse = String.valueOf(Math.abs(x));
        StringBuilder sb = new StringBuilder(reverse).reverse();
        reverse = sb.toString();
        if (Long.parseLong(reverse) > (Math.pow(2, 31) - 1)) {
            return 0;
        } else {
            if (x >= 0)
                return Integer.parseInt(reverse);
            return 0 - Integer.parseInt(reverse);
        }
    }


    /**
     * 法二:
     * 通过整数溢出后,不可逆操作来处理溢出
     * @param x
     * @return
     */
    public static int reverseTwo(int x) {
        int result = 0;
        while (x != 0) {
            int tail = x % 10;
            int newResult = result * 10 + tail;
            if ((newResult - tail) / 10 != result)
                // 处理溢出
                return 0;
            result = newResult;
            x = x / 10;
        }
        return result;
    }



    public static void main(String[] args) {
        int test1 = -2147483648;
        System.out.println("reverse: " + reverseTwo(test1));
    }
}
