package com.leetcode.ryan.personal.interview;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Basic
 * @date July 17,2018
 */
@Slf4j
public class Basic {

    // Google: 两个string，a-z组成并且带*，代表往回删一个字母，求最后结果是不是一样.
    // 时间复杂度O(length(a + b)), 空间复杂度 O(length(a + b))，引入了栈
    private static boolean isSameString(String a, String b) {
        assert a != null && b != null;

        Deque<Character> aStack = convertString2Stack(a);
        Deque<Character> bStack = convertString2Stack(b);
        if (aStack.size() != bStack.size()) {
            return false;
        }

        while (!aStack.isEmpty()) {
            if (!aStack.pop().equals(bStack.pop())) {
                return false;
            }
        }
        return true;
    }

    private static Deque<Character> convertString2Stack(String str) {
        Deque<Character> aStack = new ArrayDeque<>(str.length());

        char[] aArray = str.toCharArray();
        for (char c : aArray) {
            if (c == '*') {
                if (!aStack.isEmpty()) {
                    aStack.pop();
                }
            } else {
                aStack.push(c);
            }
        }

        return aStack;
    }

    public static void main(String[] args) {
        String a = "*****bd", b = "**c**bb*d";
        log.error("a equals b ? {}", isSameString(a, b));
    }

}
