package com.github.ryan.algorithm.leetcode.medium.medium162;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 09,2019
 */
public class Solution {

    // tc: O(n)
    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) return i;
        }
        return nums.length - 1;
    }

    // use binary search: tc -> O(logn)
    public int findPeakElement2(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[mid + 1]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
