package com.leetcode.ryan.algorithm.easy.easy217;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date July 20,2018
 */
@Slf4j
public class Solution {

    // 使用Set集合的性质，时间复杂度在Set集合没有退化为链表或红黑树时为O(n),空间复杂度为O(n)
    public boolean containsDuplicate(int[] nums) {
        // nums 为null的corner case，需要和面试官确认该情形的返回结果
        if (nums == null) {
            return false;
        }

        Set<Integer> numsSet = new HashSet<>(nums.length);
        for (int num : nums) {
            if (!numsSet.contains(num)) {
                numsSet.add(num);
            }
        }

        return numsSet.size() == nums.length ? false : true;
    }

    // 使用排序，时间复杂度为O(nlogn)，空间复杂度为O(1)
    public static boolean containsDuplicate2(int[] nums) {

        if (nums == null || nums.length < 2) {
            return false;
        }

        Arrays.sort(nums);

        int pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == pre) {
                return true;
            } else {
                pre = nums[i];
            }
        }
        return false;
    }


    public static void main(String[] args) {

    }
}
