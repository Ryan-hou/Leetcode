package com.github.ryan.algorithm.leetcode.medium.medium735;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int ast : asteroids) {
            collision: {
                while (!stack.isEmpty() && ast < 0
                        && stack.peek() > 0) {
                    if (stack.peek() < -ast) {
                        stack.pop();
                        continue;
                    } else if (stack.peek() == -ast) {
                        stack.pop();
                    }
                    break collision;
                } // end while
                stack.push(ast);
            } // end collision
        }

        int[] res = new int[stack.size()];
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }

}
