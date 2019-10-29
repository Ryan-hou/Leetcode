package com.github.ryan.leetcode.algorithm.medium.medium713;

public class Solution {

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;
        int prod = 1, res = 0, left = 0;
        // use slide window
        for (int right = 0; right < nums.length; right++) {
            prod *= nums[right];
            while (prod >= k) {
                prod /= nums[left];
                left++;
            }
            res += (right - left + 1);
        }
        return res;
    }

}
