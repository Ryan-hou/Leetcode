package com.github.ryan.leetcode.algorithm.easy.easy628;

import java.util.Arrays;

public class Solution {

    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        return Math.max(nums[0] * nums[1] * nums[len - 1]
                , nums[len - 1] * nums[len - 2] * nums[len - 3]);
    }
}
