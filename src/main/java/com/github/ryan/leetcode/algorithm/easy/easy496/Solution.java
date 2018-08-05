package com.github.ryan.leetcode.algorithm.easy.easy496;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ryan_hou
 * @description:
 * @className: Solution
 * @date April 24,2017
 */
public class Solution {
    /**
     * 思路一: 参考 https://discuss.leetcode.com/topic/77916/java-10-lines-linear-time-complexity-o-n-with-explanation
     * 时间复杂度 O(n)
     * 1) 遍历 nums 数组,记录每一个元素对应的第一个比自己大的元素(通过栈结构记录),保存到map
     * 2) 遍历 findNums 数组, 通过1)得到的map来做搜寻
     */
    public static int[] nextGreaterElement(int[] findNums, int[] nums) {
        Deque<Integer> operatorStack = new ArrayDeque<>();
        Map<Integer, Integer> searchMap = new HashMap<>();

        for (int num : nums) {
            while (!operatorStack.isEmpty() && operatorStack.peek() < num)
                searchMap.put(operatorStack.pop(), num);
            // 保持栈数据递减
            operatorStack.push(num);
        }

        for (int i = 0; i < findNums.length; i++) {
            findNums[i] = searchMap.getOrDefault(findNums[i], -1);
        }
        return findNums;
    }

    public static void main(String[] args) {
        int[] findNums = {4,1,2};
        int[] nums = {1,3,4,2};
        System.out.println("result: " + Arrays.toString(nextGreaterElement(findNums, nums)));
    }
}
