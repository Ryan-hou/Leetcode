package com.github.ryan.leetcode.algorithm.medium.medium402;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date July 10,2017
 */
public class Solution {

    private static final Logger logger = LoggerFactory.getLogger(Solution.class);

    /**
     * 思路: 从高位到低位,依次去掉数字更大的,比如: 2453, 第一个去掉4,因为4比2大,然后去掉5,依次类推
     * corner case: 22222 和 222334(数字递增),在最后出栈相应个数的数字
     * @param num
     * @param k
     * @return
     */
    private static String removeKdigits(String num, int k) {
        int len = num.length();
        if (len == k) return "0";

        int i = 0;
        Deque<Character> stack = new ArrayDeque<>();
        while (i < len) {
            char ch = num.charAt(i);

            while (k > 0
                    && !stack.isEmpty()
                    && stack.peek() > ch) {
                stack.pop();
                k--;
            }
            stack.push(ch);
            i++;
        }

        // 确保删除k个数字
        while (k > 0) {
            stack.pop();
            k--;
        }

        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }
        builder.reverse();

        // 去除起始多余的"0"
        while (builder.length() > 1
                && builder.charAt(0) == '0') {
            builder.deleteCharAt(0);
        }

        return builder.toString();

        /**
         * // corner case: deal with leading zeros
         * String res = str.toString().replaceFirst("^0*", "");
         * return "".equals(res) ? "0" : res;
         */
    }

    public static void main(String[] args) {
        String num = "1432219";
        int k = 3;
        String result = removeKdigits(num, k);
        logger.info("Remove {} digits is {}", k, result);
    }
}
