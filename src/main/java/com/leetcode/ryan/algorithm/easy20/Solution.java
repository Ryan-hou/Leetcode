package com.leetcode.ryan.algorithm.easy20;

import java.util.Stack;

/**
 * @author Ryan-hou
 * @description:
 * @className: Solution
 * @date March 07,2017
 */
public class Solution {

    private static char revertChar2Pair(char c) {
        switch (c) {
            case '(':
                return ')';
            case '[':
                return ']';
            case '{':
                return '}';
            case ')':
                return '(';
            case ']':
                return '[';
            case '}':
                return '{';
            default:
                return 0;
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
        char[] brackets = s.toCharArray();
        Stack<Character> bracketStack = new Stack<>();
        for (char c : brackets) {
            if (bracketStack.size() < 1 ||
                    bracketStack.peek().charValue() != revertChar2Pair(c)) {
                bracketStack.push(c);
            } else {
                bracketStack.pop();
                continue;
            }
        }
        if (bracketStack.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        String testOne = "{[()]}";
        String testTwo = "[]{}()[";
        System.out.println("testOne: " + isValid(testOne));
        System.out.println("testTwo: " + isValid(testTwo));
    }
}
