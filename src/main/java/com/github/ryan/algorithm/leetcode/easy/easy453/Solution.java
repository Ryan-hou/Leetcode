package com.github.ryan.algorithm.leetcode.easy.easy453;

import java.util.Arrays;

public class Solution {

    public int minMoves(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int min = nums[0], max = nums[len - 1];
        int moves = max - min; // try to make max is the equal element
        for (int i = 1; i < len - 1; i++) {
            if (nums[i] != min) {
                moves += (nums[i] - min);
            }
        }
        return moves;
    }

}
