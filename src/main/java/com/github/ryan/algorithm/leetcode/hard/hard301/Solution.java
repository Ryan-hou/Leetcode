package com.github.ryan.algorithm.leetcode.hard.hard301;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 14,2019
 */
public class Solution {


    // learn from this post:
    // https://leetcode.com/problems/remove-invalid-parentheses/discuss/308256/Java-Solution-Using-BFS
    // use BFS -> find all solution at the minimum length
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null) return res;

        Queue<String> q = new ArrayDeque<>();
        q.offer(s);
        // when found is true, stop searching and find all result at this length
        boolean found = false;
        Set<String> visited = new HashSet<>(); // avoid unnecessary isValid call
        Set<String> set = new HashSet<>(); // process duplicate result
        while (!q.isEmpty() && !found) {
            // process one level
            int num = q.size();
            for (int i = 0; i < num; i++) {
                String curStr = q.poll();
                if (!visited.contains(curStr)) {
                    if (isValid(curStr)) {
                        found = true;
                        set.add(curStr);
                    }
                    // add next level strings
                    if (!found) {
                        visited.add(curStr);
                        for (int j = 0; j < curStr.length(); j++) {
                            if (curStr.charAt(j) == '(' || curStr.charAt(j) == ')') {
                                q.offer(curStr.substring(0, j) + curStr.substring(j + 1, curStr.length()));
                            }
                        }
                    }
                }
            } // end for

        } // end while
        return new ArrayList(set);
    }

    // true -> valid
    private boolean isValid(String s) {
        assert s != null;
        Deque<Character> stack = new ArrayDeque<>(s.length());
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty()) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
