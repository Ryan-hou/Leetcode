package com.github.ryan.algorithm.leetcode.easy.easy1047;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    public String removeDuplicates(String S) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char ch : S.toCharArray()) {
            if (stack.isEmpty() || stack.peek() != ch) {
                stack.push(ch);
            } else {
                stack.pop();
            }
        }

        StringBuilder b = new StringBuilder();
        while (!stack.isEmpty()) {
            b.append(stack.pop());
        }
        return b.reverse().toString();
    }

}
