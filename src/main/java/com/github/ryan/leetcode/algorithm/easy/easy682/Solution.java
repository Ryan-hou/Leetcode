package com.github.ryan.leetcode.algorithm.easy.easy682;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    public int calPoints(String[] ops) {
        if (ops == null || ops.length < 1) return 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(Integer.valueOf(ops[0]));
        for (int i =  1; i < ops.length; i++) {
            if ("C".equals(ops[i])) {
                stack.pop();
            } else if ("D".equals(ops[i])) {
                int val = Integer.valueOf(stack.peek());
                stack.push(val * 2);
            } else if ("+".equals(ops[i])) {
                int n1 = Integer.valueOf(stack.pop());
                int n2 = Integer.valueOf(stack.pop());
                stack.push(n2);
                stack.push(n1);
                stack.push(n1 + n2);
            } else {
                // integer
                stack.push(Integer.valueOf(ops[i]));
            }
        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
