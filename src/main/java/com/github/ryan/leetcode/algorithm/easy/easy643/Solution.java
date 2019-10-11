package com.github.ryan.leetcode.algorithm.easy.easy643;

public class Solution {

    public double findMaxAverage(int[] nums, int k) {
        double res = 0;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        res = sum / (double) k;
        int idx = 0;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i];
            sum -= nums[idx];
            idx++;
            res = Math.max(res, sum / (double) k);
        }
        return res;
    }

}
