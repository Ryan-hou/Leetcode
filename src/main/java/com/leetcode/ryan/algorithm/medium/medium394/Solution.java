package com.leetcode.ryan.algorithm.medium.medium394;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date July 11,2017
 */
@Slf4j
public class Solution {

    /**
     * 两个栈:一个用来记忆入栈的数字;一个用来保存入栈的 StringBuilder
     * 所有的字符共分为4种不同的情况讨论: '[' ']' '0-9' 和其他
     * @param s
     * @return
     */
    private static String decodeString(String s) {
        char[] charArray = s.toCharArray();
        StringBuilder cur = new StringBuilder();
        Deque<StringBuilder> strStack = new ArrayDeque<>();
        Deque<Integer> intStack = new ArrayDeque<>();
        int k = 0;

        for (char c : charArray) {
            if (Character.isDigit(c)) {
                k = k * 10 + c - '0';
            } else if (c == '[') {
                intStack.push(k);
                k = 0;
                strStack.push(cur);
                cur = new StringBuilder();
            } else if (c == ']') {
                StringBuilder temp = cur;
                cur = strStack.pop();
                for (k = intStack.pop(); k > 0; k--) cur.append(temp);
            } else cur.append(c);
        }
        return cur.toString();
    }

    public static void main(String[] args) {
//        String s = "3[a]2[bc]";
        String s = "3[a2[c]]";
        log.info("result = {}", decodeString(s));
    }
}
