package com.leetcode.ryan.algorithm.easy.easy1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ryan-hou
 * @description:
 * @className: Solution
 * @date March 08,2017
 */
public class Solution {

    /**
     * 法一:
     * 题目比较简单,我们使用 Map 来做查询
     */
    public static int[] twoSumOne(int[] nums, int target) {
        int i = 0;
        Map<Integer, Integer> resultMap = new HashMap<>();
        for (int temp : nums) {
            if (resultMap.get(temp) == null) {
                resultMap.put(target - temp, i++);
            } else {
                int[] result = new int[2];
                result[0] = i;
                result[1] = resultMap.get(temp);
                return result;
            }
        }
        return new int[0];
    }

    /**
     * 法二:
     * 由题目可知,只有一种结果,可以排序后,定义首尾指针移动来求得结果
     */
    public static int[] twoSumTwo(int[] nums, int target) {
        Map<Integer, Integer> indexsMap = new HashMap<>();
        int i = 0;
        for (int temp : nums) {
            indexsMap.put(temp, i++);
        }
        Arrays.sort(nums);
        i = 0;
        int j = nums.length - 1;
        for (int temp : nums) {
           while (i < j) {
               if (nums[i] + nums[j] < target) {
                   i++;
                   continue;
               }
               if (nums[i] + nums[j] > target) {
                   j--;
                   continue;
               }
               if (nums[i] + nums[j] == target) {
                   int[] result = new int[2];
                   result[0] = indexsMap.get(nums[i]);
                   result[1] = indexsMap.get(nums[j]);
                   return result;
               }
           }
        }
        return new int[0];
    }

    /**
     * 使用map作为查找表，因为数组元素可能存在重复，所以不能一次性把数组元素存入map(会存在覆盖)
     * 一边遍历一遍查找，比第一种方法更加直观
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSumThree(int[] nums, int target) {
        Map<Integer, Integer> findMap = new HashMap<>(nums.length * 2);
        for (int i = 0; i < nums.length; i++) {

            int complement = target - nums[i];
            if (findMap.get(complement) != null) {
                int[] res = {i, findMap.get(complement)};
                return res;
            }

            findMap.put(nums[i], i);
        }
        throw new IllegalArgumentException("The input has no solution.");
    }


    public static void main(String[] args) {
        int[] nums = {1,4,2,7,6};
        int target = 9;
        System.out.println(Arrays.toString(twoSumThree(nums, target)));
    }
}
