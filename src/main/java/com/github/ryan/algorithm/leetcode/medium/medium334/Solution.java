package com.github.ryan.algorithm.leetcode.medium.medium334;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Solution {

    public boolean increasingTriplet(int[] nums) {
        if (nums == null) return false;
        int min = Integer.MAX_VALUE;
        int mid = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n < min) {
                min = n;
            } else if (n > min && n < mid) {
                mid = n;
            } else if (n > mid) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {5, 1, 5, 5, 2, 5, 4};
        log.info("res = {}", new Solution().increasingTriplet(nums));
    }
}
