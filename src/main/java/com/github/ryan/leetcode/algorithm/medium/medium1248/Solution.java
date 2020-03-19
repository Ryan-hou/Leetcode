package com.github.ryan.leetcode.algorithm.medium.medium1248;

public class Solution {

    // Use sliding window
    public int numberOfSubarrays(int[] nums, int k) {
        int l = 0, r = 0;
        int odd = 0, res = 0;
        for (; r < nums.length; r++) {
            if (nums[r] % 2 != 0) {
                odd++;
            }

            while (l < r && odd > k) {
                if (nums[l] % 2 != 0) {
                    odd--;
                }
                l++;
            }
            if (odd == k) {
                res++;
                for (int i = l; i <= r && nums[i] % 2 == 0; i++) {
                    res++;
                }
            }
        }
        return res;
    }

}
