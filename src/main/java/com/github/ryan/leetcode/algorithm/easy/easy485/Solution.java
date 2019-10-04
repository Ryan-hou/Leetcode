package com.github.ryan.leetcode.algorithm.easy.easy485;

public class Solution {

    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0;
        int cur = 0;
        for (int n : nums) {
            if (n == 1) {
                cur++;
            } else {
                res = Math.max(res, cur);
                cur = 0;
            }
        }
        return Math.max(res, cur);
    }

}
