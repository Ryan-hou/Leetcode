package com.github.ryan.leetcode.algorithm.medium.medium227;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    public int calculate(String s) {
        Deque<Character> op = new ArrayDeque<>();
        Deque<Integer> ns = new ArrayDeque<>();
        // + - * / ascii -> 43 45 42 47
        int[] opMap = new int[6];
        opMap['+' - 42] = 1;
        opMap['-' - 42] = 1;
        opMap['*' - 42] = 2;
        opMap['/' - 42] = 2;

        int i = 0;
        while (i < s.length()) {
            char ch = s.charAt(i);
            if (ch == ' ') {
                i++;
                continue;
            }
            // process number
            if (Character.isDigit(ch)) {
                int num = 0;
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                ns.push(num);
                continue;
            }

            // process operator
            while (!op.isEmpty()
                    && opMap[op.peek() - 42] >= opMap[ch - 42]) {
                pushBackNum(ns, op);
            }
            op.push(ch);
            i++;
        } // end while

        while (!op.isEmpty()) {
            pushBackNum(ns, op);
        }
        return ns.pop();
    }

    private void pushBackNum(Deque<Integer> ns, Deque<Character> op) {
        int n1 = ns.pop();
        int n2 = ns.pop();
        char chOper = op.pop();
        if (chOper == '+') {
            ns.push(n2 + n1);
        } else if (chOper == '-') {
            ns.push(n2 - n1);
        } else if (chOper == '*') {
            ns.push(n2 * n1);
        } else {
            ns.push(n2 / n1);
        }
    }
}
