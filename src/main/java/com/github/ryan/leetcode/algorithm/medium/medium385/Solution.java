package com.github.ryan.leetcode.algorithm.medium.medium385;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Solution {


     // This is the interface that allows for creating nested lists.
     // You should not implement it, or speculate about its implementation
     public class NestedInteger {
         // Constructor initializes an empty nested list.
         public NestedInteger() {}

         // Constructor initializes a single integer.
         public NestedInteger(int value) {}

         // @return true if this NestedInteger holds a single integer, rather than a nested list.
         public boolean isInteger() { return false; }

         // @return the single integer that this NestedInteger holds, if it holds a single integer
         // Return null if this NestedInteger holds a nested list
         public Integer getInteger() { return null; }

         // Set this NestedInteger to hold a single integer.
         public void setInteger(int value) {}

         // Set this NestedInteger to hold a nested list and adds a nested integer to it.
         public void add(NestedInteger ni) {}

         // @return the nested list that this NestedInteger holds, if it holds a nested list
         // Return null if this NestedInteger holds a single integer
         public List<NestedInteger> getList() { return null; }
     }


    public NestedInteger deserialize(String s) {
        if (s == null || s.length() < 1) return null;
        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.valueOf(s));
        }

        String num = "";
        Deque<NestedInteger> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '[') {
                stack.push(new NestedInteger());
            } else if (ch == ']') {
                if (!num.isEmpty()) {
                    stack.peek().add(new NestedInteger(Integer.valueOf(num)));
                    num = "";
                }
                NestedInteger ni = stack.pop();
                if (stack.isEmpty()) {
                    return ni;
                } else {
                    stack.peek().add(ni);
                }
            } else if (ch == ',') {
                if (!num.isEmpty()) {
                    stack.peek().add(new NestedInteger(Integer.valueOf(num)));
                    num = "";
                }
            } else {
                num += ch;
            }
        }
        return null;
    }
}
