package com.github.ryan.algorithm.leetcode.medium.medium34;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 11,2019
 */
public class Solution {

    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        if (nums == null || nums.length < 1) return res;

        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                // nums[mid] == target
                int i = mid, j = mid;
                while (i - 1 >= l && nums[i - 1] == target) {
                    i--;
                }
                while (j + 1 <= r && nums[j + 1] == target) {
                    j++;
                }
                res[0] = i;
                res[1] = j;
                return res;
            }
        }
        return res;
    }
}
