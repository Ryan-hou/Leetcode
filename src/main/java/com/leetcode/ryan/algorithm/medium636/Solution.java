package com.leetcode.ryan.algorithm.medium636;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date July 20,2017
 */
@Slf4j
public class Solution {

    /**
     * 问题的难点不在于用栈保存函数的调用层次,而是计算时间和pre的一些细节
     * @param n
     * @param logs
     * @return
     */
    private static int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        // pre means the start of the interval
        int pre = 0;
        for (String log : logs) {
            String[] arr = log.split(":");
            if ("start".equals(arr[1])) {
                // arr[2] is the start of next interval, doesn't belong to current interval
                if (!stack.isEmpty()) res[stack.peek()] += Integer.parseInt(arr[2]) - pre;
                stack.push(Integer.parseInt(arr[0]));
                pre = Integer.parseInt(arr[2]);
            } else {
                // arr[2] is end of current interval, belong to current interval.That's why we have +1 here
                res[stack.pop()] += Integer.parseInt(arr[2]) - pre + 1;
                pre = Integer.parseInt(arr[2]) + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> logs =
                Arrays.asList("0:start:0", "1:start:2", "1:end:5", "0:end:6");
        log.info("Exclusive time of function: {}", exclusiveTime(2, logs));
    }
}
