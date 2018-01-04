package com.leetcode.ryan.algorithm.medium150;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date July 20,2017
 */
@Slf4j
public class Solution {

    /**
     * @param tokens
     * @return
     */
    private static int evalRPN(String[] tokens) {
        int a, b;
        Deque<Integer> stack = new ArrayDeque<>();
        for (String s : tokens) {
            switch (s) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    b = stack.pop();
                    a = stack.pop();
                    stack.push(a - b);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    b = stack.pop();
                    a = stack.pop();
                    stack.push(a/b);
                    break;
                default:
                    stack.push(Integer.parseInt(s));
                    break;
            }
        }
        return stack.pop();
    }

    /**
     * 使用数组作为栈: 数组下标作为栈顶
     * @param tokens
     * @return
     */
    private static int evalRPNTwo(String[] tokens) {
        // 数字的个数
        int[] nums = new int[tokens.length / 2 + 1];
        int index = 0;
        for (String s : tokens) {
            switch (s) {
                case "+":
                    nums[index - 2] = nums[index - 2] + nums[index - 1];
                    index--;
                    break;
                case "*":
                    nums[index - 2] = nums[index - 2] * nums[index - 1];
                    index--;
                    break;
                case "-":
                    nums[index - 2] = nums[index - 2] - nums[index - 1];
                    index--;
                    break;
                case "/":
                    nums[index - 2] = nums[index - 2] / nums[index - 1];
                    index--;
                    break;
                default:
                    nums[index++] = Integer.parseInt(s);
                    break;
            }
        }
        return nums[0];
    }

    public static void main(String[] args) {
        String[] tokens = {"2", "1", "+", "3", "*"};
        String[] tokens2 = {"4", "13", "5", "/", "+"};
        log.info("result = {}", evalRPN(tokens));
        log.info("result = {}", evalRPN(tokens2));
    }
}
