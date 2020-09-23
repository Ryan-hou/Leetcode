package com.github.ryan.algorithm.leetcode.easy.easy189;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date September 18,2018
 */
@Slf4j
public class Solution {


    // 法一：最简单直观，开辟新的数组，空间换时间
    // we place every element of the array at its correct position i.e.
    // the number at index i in the original array is placed at the index (i+k)%(length of array).
    // Then, we copy the new array to the original one.
    // 空间复杂度 O(n), 时间复杂度 O(n)
    public static void rotate1(int[] nums, int k) {

        if (nums == null || nums.length == 0) return;
        int length = nums.length;
        int[] temp = new int[length];
        for (int i = 0; i < length; i++) {
            temp[(i + k) % length] = nums[i];
        }
        for (int i = 0; i < length; i++) {
            nums[i] = temp[i];
        }
    }

    // AB -> BA 等价于 AB reverse 然后A,B分别再reverse
    // 把 rotate 转为 reverse
    // 时间复杂度O(n),空间复杂度 O(1)
    public static void rotate2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return;

        int n = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, n - 1);
        reverse(nums, n, nums.length - 1);
    }

    // 反转nums数组中[start,end]区间的元素
    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            end--;
            start++;
        }
    }

    // 每次rotate一个元素，每次rotate一个元素时，需要移动整个数组
    // 在每次覆盖元素之前需要先暂存一下待覆盖的元素
    // 时间复杂度O(n*(k % num.length)),空间复杂度O(1)
    public static void rotate3(int[] nums, int k) {
        if (nums == null || nums.length == 0) return;

        int n = k % nums.length;
        for (int i = 0; i < n; i++) {

            int previous = nums[nums.length - 1];
            for (int j = 0; j < nums.length; j++) {
                int temp = nums[j];
                nums[j] = previous;
                previous = temp;
            }
        }
    }

    // Using Cyclic Replacements
    // We can directly place every number of the array at its required correct position.
    // But if we do that, we will destroy the original element.
    // Thus, we need to store the number being replaced in a temp variable.
    // Then, we can place the replaced number(temp) at its correct position and so on,
    // n times, where nn is the length of array.
    // 时间复杂度 O(n),空间复杂度O(1)
    // 不是特别容易理解，建议画图理一下思路
    public static void rotate4(int[] nums, int k) {
        if (nums == null || nums.length == 0) return;

        int n = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {

            int current = start;
            int prev = nums[start];
            do {
                int next = (current + n) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (current != start);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        int k = 3;
        rotate4(nums, k);
        // 5,6,7,1,2,3,4
        log.info("nums after rotate = {}", Arrays.toString(nums));
    }
}
