package com.leetcode.ryan.algorithm.medium503;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author ryan_hou
 * @description:
 * @className: Solution
 * @date April 25,2017
 */
public class Solution {

    /**
     * 方法一:
     * 思路: 用栈保存递减子序列的下标值
     * @param nums
     * @return
     */
    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length, next[] = new int[n];
        Arrays.fill(next, -1);
        Deque<Integer> indexStack = new ArrayDeque<>();
        for (int i = 0; i < n * 2; i++) {
            int num = nums[i % n];
            while (!indexStack.isEmpty() && nums[indexStack.peek()] < num)
                next[indexStack.pop()] = num;
            if (i < n) indexStack.push(i);
        }
        return next;
    }

    public static void main(String[] args) {
        int[] nums = {8, 3, 2, 7, 4};
        System.out.println("result: " + Arrays.toString(nextGreaterElements(nums)));
    }
}
