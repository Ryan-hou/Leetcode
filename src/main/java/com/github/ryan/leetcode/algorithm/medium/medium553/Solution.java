package com.github.ryan.leetcode.algorithm.medium.medium553;

public class Solution {

    public String optimalDivision(int[] nums) {
        // d > 2 -> b/(c/d) > b/c/d
        // use math
        if (nums == null || nums.length < 1) return "";
        if (nums.length == 1) return String.valueOf(nums[0]);
        if (nums.length == 2) return nums[0] + "/" + nums[1];

        StringBuilder b = new StringBuilder();
        b.append(nums[0]).append("/(");
        for (int i = 1; i < nums.length; i++) {
            b.append(nums[i]);
            if (i != nums.length - 1) {
                b.append("/");
            }
        }
        b.append(")");
        return b.toString();
    }

}
