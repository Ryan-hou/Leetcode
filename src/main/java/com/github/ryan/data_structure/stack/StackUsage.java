package com.github.ryan.data_structure.stack;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className StackUsage
 * @date August 07,2018
 */
@Slf4j
public class StackUsage {

    /**
     * Leetcode 20th:
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
     * determine if the input string is valid.
     */
    public static boolean isValid(String s) {
        if (s == null || (s.length() & 1) != 0) return false;

        Stack<Character> stack = new ArrayStack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }
        return stack.isEmpty();
    }


    public static void main(String[] args) {
        String str = "()[]{}{}";
        log.info("str = {}, isValid? {}", str, isValid(str));
    }
}
