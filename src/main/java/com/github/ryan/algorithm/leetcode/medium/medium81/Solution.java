package com.github.ryan.algorithm.leetcode.medium.medium81;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 16,2019
 */
public class Solution {

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length < 1) return false;

        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target || nums[r] == target) return true;

            if (nums[mid] < nums[r]) {
                // mid -> r: 递增
                if (target > nums[mid] && target < nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            } else if (nums[mid] > nums[r]) {
                if (target > nums[r] && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                // nums[mid] == nums[r]
                r--;
            }
        }
        return false;
    }

}
