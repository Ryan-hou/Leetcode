package com.leetcode.ryan.algorithm.hard224;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date July 21,2017
 */
@Slf4j
public class Solution {

    private static int calculate(String s) {
        int len = s.length(), result = 0, sign = 1;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                int sum = s.charAt(i) - '0';
                while (i + 1 < len && Character.isDigit(i + 1)) {
                    sum = s.charAt(i + 1) + sum * 10;
                    i++;
                }
                result += sum * sign;
            } else if ('+' == s.charAt(i))
                sign = 1;
            else if ('-' == s.charAt(i))
                sign = -1;
            else if ('(' == s.charAt(i)) {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (')' == s.charAt(i)) {
                result = result * stack.pop() + stack.pop();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String expression = "(1+(4+5+2)-3)+(6+8)";
        log.info("result = {}", calculate(expression));
    }
}
