package com.github.ryan.leetcode.algorithm.easy.easy20;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Ryan-hou
 * @description:
 * @className: Solution
 * @date March 07,2017
 */
public class Solution {

    private static char revertChar2Pair(char c) {

        switch (c) {

            case ')':
                return '(';
            case ']':
                return '[';
            case '}':
                return '{';
            default :
                throw new IllegalArgumentException("char is illegal");
        }
    }

    /**
     * 方法一:
     * 题目比较简单,使用栈来记忆操作顺序
     * @param s
     * @return
     */
    private static boolean isValid(String s) {
        if (s == null || "".equals(s)) {
            return true;
        }

        Deque<Character> bracketStack = new ArrayDeque<>();
        char[] brackets = s.toCharArray();

        for (char c : brackets) {

            if (c == '(' || c == '{' || c == '[') {
                bracketStack.push(c);
            } else {
                if (bracketStack.isEmpty()) {
                    return false;
                }

                char top = bracketStack.pop();
                if (top != revertChar2Pair(c)) {
                    return false;
                }

            }
        }

        if (bracketStack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Very clean code
     * @param s
     * @return
     */
    public static boolean isValidClean(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c){
                return false;
            }
        }
        return stack.isEmpty();
    }


    public static void main(String[] args) {
        String testOne = "][()";
        String testTwo = "[]{}()[]";
        System.out.println("testOne: " + isValidClean(testOne));
        System.out.println("testTwo: " + isValidClean(testTwo));
    }
}
