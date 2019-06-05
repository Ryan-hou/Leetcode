package com.github.ryan.leetcode.algorithm.hard.hard32;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 05,2019
 */
public class Solution {


    // use map:
    // time complexity O(n) / space complexity O(n)
    public int longestValidParentheses(String s) {
        if (s == null) return 0;
        // key -> count of '(', value -> index of s at current count of '('
        Map<Integer, Integer> map = new HashMap<>(s.length() * 2);
        map.put(0, -1); // corner case
        int count = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            } else {
                // s.charAt(i) == ')'
                count--;
            }

            if (s.charAt(i) == ')' && map.containsKey(count)) {
                // get distance from this ')' to previous '('
                max = Math.max(max, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return max;
    }


    // use dp:
    // time complexity: O(n) / space complexity: O(n)
    public int longestValidParentheses2(String s) {
        if (s == null || s.length() <= 1) return 0;
        // dp[i]: the length of the longest valid substring ending at ith index
        // dp[0] = 0
        int[] dp = new int[s.length()];
        int max = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    // ....()
                    dp[i] = (i - 2 > 0 ? dp[i - 2] : 0) + 2;
                } else {
                    // s.charAt(i - 1) == ')'
                    // ....))
                    int idx = i - dp[i - 1] - 1;
                    if (idx >= 0 && s.charAt(idx) == '(') {
                        dp[i] = dp[i - 1] + 2 + (idx - 1 > 0 ? dp[idx - 1] : 0);
                    }
                }
                max = Math.max(max, dp[i]);
            }
            // else dp[i] = 0
        }
        return max;
    }

    // brute force:
    // time complexity: O(n^3) / space complexity: O(n)
    public int longestValidParentheses3(String s) {
        if (s == null) return 0;

        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j <= s.length(); j += 2) {
                if (isValid(s.substring(i, j))) {
                    max = Math.max(max, j - i);
                }
            }
        }
        return max;
    }

    private boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
            } else if (!stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
