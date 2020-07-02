package com.github.ryan.leetcode.algorithm.medium.medium1060;

public class Solution {

    public int missingElement(int[] nums, int k) {
        int cur = nums[0];
        int idx = 1;
        while (k > 0 && idx < nums.length) {
            int next = nums[idx];
            for (int i = cur + 1; i < next; i++) {
                k--;
                if (k == 0) {
                    return i;
                }
            }
            cur = next;
            idx++;
        }
        return cur + k;
    }

}
