package com.github.ryan.algorithm.leetcode.medium.medium376;

public class Solution {

    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;

        int len = nums.length;
        // up[i] -> max length from [0, i] and i is up
        int[] up = new int[len];
        // down[i] -> max length from [0, i] and i is down
        int[] down = new int[len];
        up[0] = 1;
        down[0] = 1;
        for (int i = 1; i < len; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    up[i] = Math.max(up[i], down[j] + 1);
                } else if (nums[i] < nums[j]) {
                    down[i] = Math.max(down[i], up[j] + 1);
                } else {
                    // nums[i] == nums[j]
                    up[i] = Math.max(up[j], up[i]);
                    down[i] = Math.max(down[j], down[i]);
                }
            }
        }
        return Math.max(up[len - 1], down[len - 1]);
    }

}
