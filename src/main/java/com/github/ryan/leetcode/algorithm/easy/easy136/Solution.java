package com.github.ryan.leetcode.algorithm.easy.easy136;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date February 22,2018
 */
@Slf4j
public class Solution {

    /**
     * 思路：
     * 整数和其自身异或结果为0
     * 时间复杂度：O(n)
     * 空间复杂度: O(1)
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        assert nums != null && nums.length > 0;

        int result = 0;
        for (int num : nums) {
          result ^= num;
        }
        return result;
    }

    public static int singleNumber2(int[] nums) {
        assert nums != null && nums.length > 0;

        Set<Integer> set = Sets.newHashSetWithExpectedSize(nums.length / 2 + 1);
        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }
        return (Integer) set.toArray()[0];
    }

    public static void main(String[] args) {
        int[] nums = {12, 12, 34, 34, -8, -8, 9};
        log.info("single number = {}", singleNumber2(nums));
    }
}
