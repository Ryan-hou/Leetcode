package com.leetcode.ryan.algorithm.medium.medium456;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date July 10,2017
 */
public class Solution {

    private static final Logger logger = LoggerFactory.getLogger(Solution.class);

    private static class Pair {
        int min, max;

        public Pair(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    /**
     * 问题抽象为:只要当前元素比前面所有元素的最小值大,最大值小,就一定可以构成 132 pattern
     * 所以需要构造一个 min_max Pair 数据结构
     * @param nums
     * @return
     */
    private static boolean find132Pattern(int[] nums) {
        if (nums == null || nums.length < 3)
            return false;

        Deque<Pair> result = new ArrayDeque<>();
        for (int i : nums) {
           if (result.isEmpty() || i < result.peek().min)
               // 保证 min 是全局最小的值
               result.push(new Pair(i, i));
           else if (i > result.peek().min) {
               Pair last = result.pop();
               if (i < last.max) return true;
               else {
                   last.max = i;
                   while (!result.isEmpty() && i >= result.peek().max) result.pop();
                   // At this time, n < stack.peek().max (if stack not empty)
                   if (!result.isEmpty() && i > result.peek().min) return true;
                   result.push(last);
               }
           }
        }
        return false;
    }

    public static void main(String[] args) {
        //int[] nums = {3, 1, 4, 2};
        //int[] nums = {1, 2, 3, 4};
        int[] nums = {3,5,4};
        boolean pattern = find132Pattern(nums);
        logger.info("Is 132 pattern ? {}", pattern);
    }
}
