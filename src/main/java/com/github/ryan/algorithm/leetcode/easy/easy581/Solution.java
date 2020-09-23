package com.github.ryan.algorithm.leetcode.easy.easy581;

import java.util.Arrays;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date January 25,2019
 */
public class Solution {

    // 思路一：借助排序
    public int findUnsortedSubarray(int[] nums) {
        assert(nums != null);

        int[] original = Arrays.copyOf(nums, nums.length);
        // O(NlogN)
        Arrays.sort(nums);
        int[] sorted = nums;

        int len = nums.length;
        int s = 0, e = len - 1; // [s...e] is the unsorted subarray
        while (s < len && original[s] == sorted[s]) {
            s++;
        }
        while (e > -1 && original[e] == sorted[e]) {
            e--;
        }
        return e - s + 1 < 0 ? 0 : e - s + 1;
    }

    // 思路二：双指针双向查找
    public int findUnsortedSubarray2(int[] nums) {
        assert(nums != null && nums.length != 0);

        // 双向查找:
        // end找的是不受逆序影响的最大索引（此索引后的部分必然是正序的）
        // beg找的是不受逆序影响的最小索引（此索引前的部分必然是正序的）
        // 时间复杂度 O(n),空间复杂度O(1)

        // use the variables beg and end to keep track of minimum subarray A[beg...end]
        // If end < beg < 0 at the end of the for loop, then the array is already fully sorted
        int n = nums.length, beg = -1, end = -2;
        int min = nums[n - 1], max = nums[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[n - 1 - i]);
            if (nums[i] < max) { end = i; }
            if (nums[n - 1 - i] > min) { beg = n - 1 - i; }
        }
        return end - beg + 1;
    }
}
