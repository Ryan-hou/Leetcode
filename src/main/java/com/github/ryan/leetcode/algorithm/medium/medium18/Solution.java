package com.github.ryan.leetcode.algorithm.medium.medium18;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date April 10,2019
 */
@Slf4j
public class Solution {


    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) return res;

        Set<String> dupSet = new HashSet<>();
        int len = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < len - 3; i++) {
            int sum3 = target - nums[i];
            for (int j = i + 1; j < len - 2; j++) {
                int sum2 = sum3 - nums[j];
                // two pointer
                int m = j + 1, n = len - 1;
                while (m < n) {
                    if (nums[m] + nums[n] == sum2) {
                        String str = "" + nums[i] + nums[j] + nums[m] + nums[n];
                        if (!dupSet.contains(str)) {
                            res.add(Arrays.asList(nums[i], nums[j], nums[m], nums[n]));
                            dupSet.add(str);
                        }
                        m++;
                        n--;
                    } else if (nums[m] + nums[n] < sum2) {
                        m++;
                    } else {
                        // nums[m] + nums[n] > sum2
                        n--;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        log.info("res = {}", new Solution().fourSum(nums, target));
    }
}
