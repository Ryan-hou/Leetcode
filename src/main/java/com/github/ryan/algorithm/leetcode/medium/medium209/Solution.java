package com.github.ryan.algorithm.leetcode.medium.medium209;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date January 13,2018
 */
@Slf4j
public class Solution {

    /**
     * 暴力解法：求出所有的连续子数组的和(存在大量的重复计算，后续算法的优化方向)
     * 时间复杂度：O(n^3)
     * 空间复杂度: O(1)
     * @param s
     * @param nums
     * @return
     */
    public static int minSubArrayLenOne(int s, int[] nums) {

        int res = nums.length + 1;
        for (int l = 0; l < nums.length; l++) {
            for (int r = l; r < nums.length; r++) {
                int sum = 0;
                for (int i = l; i <= r; i++) {
                    sum += nums[i];
                }
                if (sum >= s) {
                    res = Math.min(res, r - l + 1);
                }
            }
        }

        if (res == nums.length + 1) {
            return 0;
        } else {
            return res;
        }

    }

    /**
     * 优化暴力解法：求nums[l,r]的和不使用循环
     * 时间复杂度: O(n^2)
     * 空间复杂度: O(n)：开辟了一个新的数组空间
     * @param s
     * @param nums
     * @return
     */
    public static int minSubArrayLenTwo(int s, int[] nums) {
        // sums[i]存放nums[0,i-1] 的和
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        int res = nums.length + 1;
        for (int l = 0; l < nums.length; l++) {
            for (int r = l; r < nums.length; r++) {
                // 使用 sums[r+1] - sums[l] 迅速得到 nums[l,r] 的和
                if (sums[r + 1] - sums[l] >= s) {
                    res = Math.min(res, r - l + 1);
                }
            }
        }

        if (res == nums.length + 1) {
            return 0;
        } else {
            return res;
        }
    }

    /**
     * 接着上面 O(n^2) 的算法继续优化，由于数组元素均为正整数，sums[0,nums.length]是单调递增的，即
     * 有序数组，可以考虑使用二分查找算法，把时间复杂度继续优化到 O(nlogn)
     * Since all elements are positive, the cumulative sum must be strictly increasing.
     * Then, a subarray sum can expressed as the difference between two cumulative sum.
     * Hence, given a start index for the cumulative sum array, the other end index
     * can be searched using binary search.
     * @param s
     * @param nums
     * @return
     */
    public static int minSubArrayLenThree(int s, int[] nums) {
        // sums[i]存放nums[0,i-1]的和
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        int res = nums.length + 1;
        for (int i = 0; i < sums.length; i++) {
            // 在[i+1,sums.length-1]中寻找与sums[i]相差s最小下标
            int end = binarySearch(i + 1, sums.length - 1, sums[i] + s, sums);
            if (end == sums.length) break;
            if (end - i < res) res = end - i;
        }

        return res == nums.length + 1 ? 0 : res;
    }

    private static int binarySearch(int l, int r, int target, int[] sums) {
        // 在[l,r]中寻找sums[mid]>=target 的最小l
        while (l <= r) {
          int mid = l + (r - l) / 2;
            if (sums[mid] >= target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    /**
     * 使用双指针操作（滑动窗口操作）
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     * @param s
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int s, int[] nums) {

        int l = 0, r = -1; // nums[l,r] 为我们的滑动窗口
        int sum = 0;
        int res = nums.length + 1;

        while (l < nums.length) {

            // 操作数组一定注意考虑数组下标越界的问题
            if (r + 1 < nums.length && sum < s) {
//                r++;
//                sum += nums[r];
                sum += nums[++r];
            } else {
//                sum -= nums[l];
//                l++;
                sum -= nums[l++];
            }

            if (sum >= s) {
                res = Math.min(res, r - l + 1);
            }
        }

        return  res == nums.length + 1 ? 0 : res;
    }


    public static void main(String[] args) {
        int[] nums = {2,3,1,2,4,3};
        int minLen = minSubArrayLenThree(10, nums);
        log.info("minLen = {}", minLen);
    }

}
