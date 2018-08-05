package com.github.ryan.leetcode.algorithm.easy.easy35;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date March 06,2018
 */
@Slf4j
public class Solution {

    // 二分搜索简单修改
    public static int searchInsert(int[] nums, int target) {
        assert nums != null;

        // 在［l,r］区间查找target
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        // 二分搜索查找不到直接返回-1即可，这里需要改为返回该元素应该所在位置的索引
        return l;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        int target = 7;
        log.info("num index = {}", searchInsert(nums, target));
    }
}
