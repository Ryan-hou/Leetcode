package com.github.ryan.algorithm.leetcode.medium.medium11;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 06,2019
 */
public class Solution {

    // two pointers:
    // time complexity: O(n) / space complexity: O(1)
    public int maxArea(int[] height) {
        assert height != null && height.length > 1;
        int l = 0, r = height.length - 1;
        int res = Integer.MIN_VALUE;
        while (l < r) {
            if (height[l] < height[r]) {
                res = Math.max(res, height[l] * (r - l));
                l++;
            } else {
                // height[l] >= height[r]
                res = Math.max(res, height[r] * (r - l));
                r--;
            }
        }
        return res;
    }
}
