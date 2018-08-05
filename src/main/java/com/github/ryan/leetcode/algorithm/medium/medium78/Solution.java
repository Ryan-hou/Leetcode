package com.github.ryan.leetcode.algorithm.medium.medium78;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ryan-hou
 * @description:
 * @className: Solution
 * @date March 03,2017
 */
public class Solution {
    /**
     * 法一:
     * 求一个set所有的子set:
     * set大小为0时,子set大小为1
     * set大小为1时,子set大小为2
     * set大小为2时,子set大小为4
     * set大小为3时,子set大小为8
     * 依次类推:
     * set大小为n时,子set大小为2^n
     * 考虑使用位运算来简化求解过程: n位二进制代表的信息量就是 2^n
     * set大小为n时,即用n位二进制来表示,二进制位为1时,该位置下标代表的set元素命中;
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> resultUnderNElement = new ArrayList<>();
        // size 为所有的子集个数,共2^n个
        int size = 1 << nums.length;

        for (int i = 0; i < size; i++) {
            resultUnderNElement.clear();
            for (int j = 0; j < nums.length; j++) {
                if ((1 & (i >> j)) == 1)
                    resultUnderNElement.add(nums[j]);
            }
            // result 的元素就是每个子 Set,总共 2^n 个
            // 注意这里要新建List对象,而不是每次持有同一个引用: result.add(resultUnderNElement)
            result.add(new ArrayList<Integer>(resultUnderNElement));
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = subsets(nums);
        for (List<Integer> subSet : result) {
            System.out.println("subSet: " + subSet);
        }
    }
}
