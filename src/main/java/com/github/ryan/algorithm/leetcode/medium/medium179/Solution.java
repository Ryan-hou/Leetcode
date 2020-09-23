package com.github.ryan.algorithm.leetcode.medium.medium179;

import java.util.Arrays;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 09,2019
 */
public class Solution {

    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) return "";
        if (checkZero(nums)) return "0";

        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));

        StringBuilder b = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) {
            b.append(strs[i]);
        }
        return b.toString();
    }

    private boolean checkZero(int[] nums) {
        for (int n : nums) {
            if (n != 0) {
                return false;
            }
        }
        return true;
    }
}
