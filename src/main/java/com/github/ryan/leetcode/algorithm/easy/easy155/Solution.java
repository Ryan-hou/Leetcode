package com.github.ryan.leetcode.algorithm.easy.easy155;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ryan_hou
 * @description:
 * @className: Solution
 * @date April 25,2017
 */
public class Solution {


    /**
     * 思路: 问题的关键在于常数时间內获取栈中的最小值,所以在入栈出栈时需要动态的记录当前栈的最小值
     * 每次在入栈时判断是不是比当前栈最小值小,是的话,把先入栈当前最小值,然后入栈该值并更新栈的最小值,
     * 这样每次在出栈当前栈最小值时,都会拿到剩余栈的最小值,(对应的pop了两次)还是利用栈的记忆特性
     */
    private static class MinStack {

        int min = Integer.MAX_VALUE;

        Deque<Integer> stack = new ArrayDeque<>();

        public MinStack() {

        }

        public void push(int x) {
            if (x <= min) {
                stack.push(min);
                min = x;
            }
            stack.push(x);
        }

        public void pop() {
            if (stack.pop() == min) min = stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println("Current min:" + minStack.getMin()); // Returns -3.
        minStack.pop();
        System.out.println("Top:" + minStack.top()); // Returns 0.
        System.out.println("Current min:" + minStack.getMin()); // Returns -2.
    }
}
