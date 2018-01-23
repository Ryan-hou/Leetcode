package com.leetcode.ryan.algorithm.medium220;

import lombok.extern.slf4j.Slf4j;

import java.util.TreeSet;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date January 23,2018
 */
@Slf4j
public class Solution {

    /**
     * 思路：
     * 与219题思路类似，采用滑动窗口控制距离k，采用查找表(范围查询)
     * 取值v，查找表中在 [v-t,v+t] 的范围内存在数据即可，即 ceil(v-t) <= v+t
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(k)
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

        if (nums == null || nums.length <= 1) {
            return false;
        }

        TreeSet<Long> set = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (set.ceiling((long) nums[i] - (long) t) != null
                    && set.ceiling((long) nums[i] - (long) t) <= (long) nums[i] + (long) t) {
                // nums[i] + t  可能存在整型溢出问题，采用 long 来表示（先梳理算法流程，然后再考虑数据范围）
                return true;
            }
            set.add((long) nums[i]);

            // 保持set中最多有k个元素
            if (set.size() == k + 1) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5,  8};
        int k = 2, t = 1;
        log.info("result = {}", containsNearbyAlmostDuplicate(nums, k, t));
    }

}
