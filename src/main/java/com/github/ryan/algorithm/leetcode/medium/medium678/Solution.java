package com.github.ryan.algorithm.leetcode.medium.medium678;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    public boolean checkValidString(String s) {
        // how many * you have seen so far,
        // and also of how many of those * you used as closing paranthesis
        int count = 0;
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push('(');
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    if (count == 0) {
                        return false;
                    } else {
                        count--;
                    }
                } else {
                    // stack is not empty
                    stack.pop();
                }
            } else {
                // c == '*'
                count++;
                if (!stack.isEmpty()) {
                    stack.pop();
                    // When we greedily use * to complete one (),
                    // we might actually break up the existing valid pair in the sequence.
                    // And hence we might alienate one ), which could have matched with current (.
                    // So we need to account for that.
                    count++;
                }
            }
        }
        return stack.isEmpty();
    }

}
