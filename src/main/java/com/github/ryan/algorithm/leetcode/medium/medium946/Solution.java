package com.github.ryan.algorithm.leetcode.medium.medium946;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new ArrayDeque<>();
        int idx = 0, len = popped.length;
        for (int n : pushed) {
            stack.push(n);
            while (!stack.isEmpty() && idx < len
                    && stack.peek() == popped[idx]) {
                stack.pop();
                idx++;
            }
        }
        return idx == len;
    }

}
