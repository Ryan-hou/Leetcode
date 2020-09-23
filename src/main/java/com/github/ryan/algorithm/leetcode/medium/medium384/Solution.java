package com.github.ryan.algorithm.leetcode.medium.medium384;

import java.util.Random;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 13,2019
 */
public class Solution {

    private Random r;
    private int[] nums;
    private int[] original;

    public Solution(int[] nums) {
        r = new Random();
        this.nums = nums;
        this.original = new int[nums.length];
        System.arraycopy(nums, 0, original, 0, nums.length);
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        System.arraycopy(original, 0, nums, 0, original.length);
        return nums;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int sz = nums.length;
        for (int i = sz; i > 1; i--) {
            swap(nums, i - 1, r.nextInt(i));
        }
        return nums;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(nums);
     * int[] param_1 = obj.reset();
     * int[] param_2 = obj.shuffle();
     */

}
