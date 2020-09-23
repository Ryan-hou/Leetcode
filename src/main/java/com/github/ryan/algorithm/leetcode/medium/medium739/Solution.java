package com.github.ryan.algorithm.leetcode.medium.medium739;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 12,2019
 */
public class Solution {

    public int[] dailyTemperatures(int[] T) {
        if (T == null || T.length <= 0) return T;

        // use stack keep decreasing array's index
        int len = T.length;
        int[] res = new int[len]; // default value is 0
        Deque<Integer> stack = new ArrayDeque<>(len);
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                int prevIdx = stack.pop();
                res[prevIdx] = i - prevIdx;
            }
            stack.push(i);
        }
        return res;
    }
}
