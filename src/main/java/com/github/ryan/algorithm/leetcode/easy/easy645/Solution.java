package com.github.ryan.algorithm.leetcode.easy.easy645;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date January 03,2019
 */
public class Solution {

    // 法一：最直接，使用Set，遍历两遍，时间复杂度O(n),空间复杂度O(n)
    public int[] findErrorNums(int[] nums) {
        assert nums != null && nums.length >= 2 && nums.length <= 10000;

        int[] res = new int[2];
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (set.contains(n)) {
                res[0] = n;
            }
            set.add(n);
        }

        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)) {
                res[1] = i;
                break;
            }
        }

        return res;
    }

    // 法二：不使用Set，使用数组作为map，速度更快
    public int[] findErrorNums2(int[] nums) {
        assert nums != null && nums.length >= 2 && nums.length <= 10000;

        int res[] = new int[2];
        boolean[] map = new boolean[nums.length + 1];
        for (int n : nums) {
            if (map[n]) res[0] = n;
            map[n] = true;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!map[i + 1]) {
                res[1] = i + 1;
                return res;
            }
        }
        throw new IllegalArgumentException();
    }

    // 法三：使用数学计算，遍历一遍 one pass
    public int[] findErrorNums3(int[] nums) {
        assert nums != null && nums.length >= 2 && nums.length <= 10000;

        boolean[] map = new boolean[nums.length + 1];
        int dup = 0, n = nums.length;
        long sum = (n * (n + 1)) / 2;

        for (int i : nums) {
            if (map[i]) dup = i;
            sum -= i;
            map[i] = true;
        }
        return new int[] {dup, dup + (int) sum};
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 4};
        new Solution().findErrorNums2(nums);
    }
}
