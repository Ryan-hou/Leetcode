package com.github.ryan.algorithm.leetcode.easy.easy219;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date January 23,2018
 */
@Slf4j
public class Solution {

    /**
     * 思路一：暴力解法，找出所有的i,j 使得 nums[i] == nums[j], |i-j| <= k
     * 时间复杂度O(n^2)
     */

    /**
     * 思路二：滑动窗口+查找表
     * 构造长度为k+1的滑动窗口，每次在移动窗口时，比较下一个要遍历的元素是否在滑动窗口所构成的查找表中
     * 时间复杂度：O(n)
     * 空间复杂度：O(k)
     */
    public static boolean containsNearbyDuplicate(int[] nums, int k) {

        if (nums == null || nums.length <= 1) {
            return false;
        }

        Set<Integer> record = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (record.contains(nums[i])) {
                return true;
            }

            record.add(nums[i]);

            //保持record中最多有k个元素
            if (record.size() == k + 1) {
                record.remove(nums[i - k]);
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int[] nums = {2, 3, 5, 6, 7, 2, 9};
        int k = 5;
        boolean res = containsNearbyDuplicate(nums, k);
        log.info("result = {}", res);
    }
}
