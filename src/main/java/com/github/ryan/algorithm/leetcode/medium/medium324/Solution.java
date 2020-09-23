package com.github.ryan.algorithm.leetcode.medium.medium324;

import java.util.Arrays;

public class Solution {

    // time complexity: O(nlogn), space complexity: O(n) -> actually beat 100% in leetcode
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        int[] tmp = Arrays.copyOf(nums, nums.length);
        int idx = tmp.length - 1;
        Arrays.sort(tmp);
        for (int i = 1; i < nums.length; i += 2) {
            nums[i] = tmp[idx--];
        }
        for (int i = 0; i < nums.length; i += 2) {
            nums[i] = tmp[idx--];
        }

    }

}
