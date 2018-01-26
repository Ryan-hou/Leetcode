package com.leetcode.ryan.algorithm.easy.easy167;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date January 12,2018
 */
@Slf4j
public class Solution {

    // 暴力穷举：O(n^2), 没有使用到数组的有序性

    /**
     * 有序性：联想到二分搜索，遍历数组，每次在剩余数组中用二分搜索法查询
     * 时间复杂度: O(nlogn) 比起 O(n^2) 时间复杂度有质的改进
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSumOne(int[] numbers, int target) {

        for (int i = 0; i < numbers.length; i++) {
            int index = findDestInLeft(i, numbers, target);
            if (index != -1) {
                int[] result = {i + 1, index + 1};
                return result;
            }
        }

        throw new IllegalArgumentException("The input has no solution.");
    }

    private static int findDestInLeft(int i, int[] numbers, int target) {
        int dest = target - numbers[i];

        int l = i + 1, r = numbers.length - 1; // 在[l...r]中查询dest
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (numbers[mid] == dest) {
                return mid;
            }

            if (numbers[mid] > dest) {
                r = mid - 1; // dest 在 [l...mid-1] 中
            } else {
                l = mid + 1; // dest 在［mid＋1...r］ 中
            }
        }

        return -1;
    }

    /**
     * 使用首尾对撞指针，遍历数组一遍即可
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum(int[] numbers, int target) {

        int l = 0, r = numbers.length - 1; // 在[l,r]寻找两个元素
        while (l < r) {
            if (numbers[l] + numbers[r] == target) {
                int[] res = {l + 1, r + 1};
                return res;
            } else if (numbers[l] + numbers[r] > target) {
                r--;
            } else {
                l++;
            }
        }

        throw new IllegalArgumentException("The input has no solution");
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 5, 7, 8};
        int target = 19;
        int[] result = twoSum(nums, target);
        log.info("result = {}", Arrays.toString(result));
    }

}
