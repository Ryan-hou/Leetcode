package com.github.ryan.algorithm.leetcode.hard.hard135;

import java.util.Arrays;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 31,2019
 */
public class Solution {

    // time complexity: O(n)
    // space complexity: O(n)
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length < 1) return 0;

        int len = ratings.length;
        int[] candies = new int[len];
        Arrays.fill(candies, 1);
        // process from left to right
        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        int sum = candies[len - 1];
        // process from right to left
        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i + 1] + 1, candies[i]);
            }
            sum += candies[i];
        }
        return sum;
    }
}
