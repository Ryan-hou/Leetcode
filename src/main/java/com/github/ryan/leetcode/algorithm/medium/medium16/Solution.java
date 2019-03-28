package com.github.ryan.leetcode.algorithm.medium.medium16;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date March 26,2019
 */
@Slf4j
public class Solution {

    public int threeSumClosest(int[] nums, int target) {
        assert nums != null && nums.length > 2;

        Arrays.sort(nums); // O(nlogn)
        int curMinGap = Integer.MAX_VALUE;
        int res = 0;
        // O(n^2)
        for (int i = 0; i < nums.length - 2; i++) {
            int newTarget = target - nums[i];
            int n = twoSumClosest(nums, i + 1, newTarget);
            if (n == newTarget) {
                return target;
            }

            int curGap = Math.abs(n + nums[i] - target);
            if (curGap < curMinGap) {
                curMinGap = curGap;
                res = n + nums[i];
            }
        }
        return res;
    }


    private int twoSumClosest(int[] nums, int start, int target) {
        int end = nums.length - 1;
        int res = 0;
        int curMinGap = Integer.MAX_VALUE;
        while (start < end) {
            int sum = nums[start] + nums[end];

            if (sum == target) {
                return target;
            }
            int curGap = Math.abs(sum - target);
            if (curGap < curMinGap) {
                curMinGap = curGap;
                res = sum;
            }
            if (sum < target) {
                start++;
            } else {
                // sum > target
                end--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        log.info("3Sum Closest = {}", new Solution().threeSumClosest(nums, target));
    }

}
