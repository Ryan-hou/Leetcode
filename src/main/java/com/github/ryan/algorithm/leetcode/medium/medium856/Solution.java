package com.github.ryan.algorithm.leetcode.medium.medium856;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    // use stack:
    // https://leetcode.com/problems/score-of-parentheses/solution/
    public int scoreOfParentheses(String S) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0); // the score of the current frame
        for (char ch : S.toCharArray()) {
            if (ch == '(') {
                stack.push(0);
            } else {
                int cur = stack.pop();
                int prior = stack.pop();
                stack.push(prior + Math.max(2 * cur, 1));
            }
        }
        return stack.pop();
    }

}
