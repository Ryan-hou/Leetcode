package com.github.ryan.algorithm.leetcode.medium.medium33;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date May 29,2019
 */
public class Solution {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int l = 0, r = nums.length - 1;
        while (l <= r) {

            int mid = l + (r - l) / 2;
            if (nums[mid] == target) return mid;

            if (nums[mid] < nums[r]) {
                // mid --> r: 递增
                if (target > nums[mid] && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    // target < nums[min] || target > nums[r]
                    r = mid - 1;
                }
            } else {
                // nums[mid] > nums[r]
                // l --> mid: 递增
                if (target >= nums[l] && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }

        }
        return -1;
    }

}
