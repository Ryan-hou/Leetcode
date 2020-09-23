package com.github.ryan.algorithm.leetcode.hard.hard164;

import java.util.Arrays;

public class Solution {

    // Use bucket sort
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;

        int n = nums.length;
        int min = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            min = Math.min(nums[i], min);
            max = Math.max(nums[i], max);
        }
        if (min == max) return 0;

        int bucketNum = n - 1;
        // n - 2 elements(exclude min and max) with n - 1 buckets, at least one bucket is empty
        // min and max element in every bucket
        int[] bucketMin = new int[bucketNum];
        int[] bucketMax = new int[bucketNum];
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, -1);
        int bucketSize = (int) Math.ceil((double) (max - min) / bucketNum);

        for (int i = 0; i < n; i++) {
            if (nums[i] == min || nums[i] == max) {
                continue;
            }

            int idx = (nums[i] - min) / bucketSize;
            bucketMin[idx] = Math.min(nums[i], bucketMin[idx]);
            bucketMax[idx] = Math.max(nums[i], bucketMax[idx]);
        }

        int maxGap = 0;
        int prevMax = min;
        for (int i = 0; i < n - 1; i++) {
            if (bucketMax[i] == -1) {
                continue;
            }
            maxGap = Math.max(maxGap, bucketMin[i] - prevMax);
            prevMax = bucketMax[i];
        }
        maxGap = Math.max(maxGap, max - prevMax);
        return maxGap;
    }
}
