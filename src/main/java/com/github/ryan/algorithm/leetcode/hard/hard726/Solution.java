package com.github.ryan.algorithm.leetcode.hard.hard726;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.TreeMap;

public class Solution {

    public String countOfAtoms(String formula) {
        int n = formula.length();
        // map: key -> name of atomic element, value -> count of this atomic element
        Deque<Map<String, Integer>> stack = new ArrayDeque<>();
        stack.push(new TreeMap<>());

        for (int i = 0; i < n; ) {
            if (formula.charAt(i) == '(') {
                stack.push(new TreeMap<>());
                i++;
            } else if (formula.charAt(i) == ')') {
                Map<String, Integer> top = stack.pop();
                i++;
                int iStart = i, mul = 1;
                while (i < n && Character.isDigit(formula.charAt(i))) {
                    i++;
                }
                if (i > iStart) {
                    mul = Integer.parseInt(formula.substring(iStart, i));
                }
                for (String name : top.keySet()) {
                    int count = top.get(name);
                    stack.peek().put(name, stack.peek().getOrDefault(name, 0) + count * mul);
                }
            } else {
                int iStart = i;
                i++;
                while (i < n && Character.isLowerCase(formula.charAt(i))) {
                    i++;
                }
                String name = formula.substring(iStart, i);
                iStart = i;
                while (i < n && Character.isDigit(formula.charAt(i))) {
                    i++;
                }
                int count = i > iStart ? Integer.parseInt(formula.substring(iStart, i)) : 1;
                stack.peek().put(name, stack.peek().getOrDefault(name, 0) + count);
            }
        } // end for

        StringBuilder res = new StringBuilder();
        for (String name : stack.peek().keySet()) {
            res.append(name);
            int mul = stack.peek().get(name);
            if (mul > 1) {
                res.append(String.valueOf(mul));
            }
        }
        return res.toString();
    }

}
