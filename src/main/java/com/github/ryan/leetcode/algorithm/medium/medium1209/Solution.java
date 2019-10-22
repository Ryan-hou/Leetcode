package com.github.ryan.leetcode.algorithm.medium.medium1209;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    private static class Element {
        char ch;
        int count;
        Element(char ch) {
            this.ch = ch;
            this.count = 1;
        }
    }

    public String removeDuplicates(String s, int k) {
        Deque<Element> stack = new ArrayDeque<>();
        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty() && ch == stack.peek().ch) {
                stack.peek().count += 1;
                if (stack.peek().count == k) {
                    stack.pop();
                }
            } else {
                stack.push(new Element(ch));
            }
        }

        StringBuilder b = new StringBuilder();
        for (Element e : stack) {
            for (int i = 0; i < e.count; i++) {
                b.append(e.ch);
            }
        }
        return b.reverse().toString();
    }

}
