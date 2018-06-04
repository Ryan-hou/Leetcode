package com.leetcode.ryan.algorithm.medium.medium287;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date June 02,2018
 */
@Slf4j
public class Solution {

    // 时间复杂度O(nlogn)，用于排序；空间复杂度 O(1)
    // 排序操作修改了原数组，不太符合要求
    public static int findDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException("nums's length is less than 2!");
        }

        Arrays.sort(nums);
        int prev = nums[0];
        int i = 1, length = nums.length;
        while (i < length) {
            if (nums[i] == prev) {
                return prev;
            } else {
                prev = nums[i++];
            }
        }
        throw new IllegalArgumentException("nums don't have duplicate number!");
    }

    /**
     * 思路：
     * 以数组值为下标索引，构成单链表，数组重复的值就是单链表构成环的起始点
     * 实现方法参考：142题：detectCycle
     * 时间复杂度：O(n) 空间复杂度O(1), 同时没有修改原数组
     * @param nums
     * @return
     */
    public static int findDuplicateUse2Pointer(int[] nums) {

        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException("nums's length is less than 2!");
        }

        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        // s = r - m
        fast = 0;
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;

    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 3, 4, 2};
        log.info("nums's duplicate number = {}", findDuplicateUse2Pointer(nums));
    }
}
