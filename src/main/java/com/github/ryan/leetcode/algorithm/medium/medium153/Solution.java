package com.github.ryan.leetcode.algorithm.medium.medium153;

public class Solution {

    // sorted array -> binary search
    public int findMin(int[] nums) {
        if (nums.length == 1) return nums[0];
        int left = 0, right = nums.length - 1;
        if (nums[right] > nums[left]) {
            return nums[0];
        }

        // Binary search
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid + 1 < nums.length && nums[mid + 1] < nums[mid]) {
                return nums[mid + 1];
            }
            if (mid - 1 >= 0 && nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }

            if (nums[mid] > nums[0]) {
                left = mid + 1;
            } else {
                // nums[mid] < nums[0]
                right = mid - 1;
            }
        }
        return -1;
    }

}
