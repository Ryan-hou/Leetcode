package com.leetcode.ryan.algorithm.medium15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ryan-hou
 * @description:
 * @className: Solution
 * @date February 25,2017
 */
public class Solution {

    /**
     * 法一:
     * 先排序,然后定义首尾两个指针,同时向中间移动
     * 时间复杂度O(n^2)
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3)
            return result;

        Arrays.sort(nums);
        // 外层循环:遍历所有的不重复的可作为第一个结果元素的元素
        for (int i = 0; i < nums.length - 2; i++) {
            // 去重
            if (i == 0 || nums[i] != nums[i - 1]) {
                int lo = i + 1, hi = nums.length - 1, sum = 0 - nums[i];
                while (lo < hi) {
                    if (nums[lo] + nums[hi] == sum) {
                        result.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        // 去重: 注意 if-else 分支操作和 lo < hi 的顺序在前(避免数组下标越界异常)
                        while (lo < hi && nums[lo] == nums[lo + 1]) lo++;
                        while (lo < hi && nums[hi] == nums[hi - 1]) hi--;
                        lo++;
                        hi--;
                    } else if (nums[lo] + nums[hi] < sum) {
                        lo++;
                    } else {
                        hi--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = threeSum(nums);
        for (List<Integer> list : result) {
            System.out.println(Arrays.toString(list.toArray()));
        }
    }
}
