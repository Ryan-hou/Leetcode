package com.github.ryan.leetcode.algorithm.medium.medium560;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date April 09,2019
 */
public class Solution {

    // Time complexity: O(n^2) / Space complexity O(1)
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;

        int res = 0;
        int len = nums.length;
        for (int start = 0; start < len; start++) {
            int sum = 0;
            for (int end = start; end < len; end++) {
                sum += nums[end];
                if (sum == k) res++;
            }
        }
        return res;
    }

    // From solution 1, we know the key to solve this problem is SUM[i, j].
    // So if we know SUM[0, i - 1] and SUM[0, j], then we can easily get SUM[i, j].(做减法)
    // To achieve this, we just need to go through the array,
    // calculate the current sum and save number of all seen PreSum to a HashMap.

    // Time complexity: O(n) / Space complexity O(n)
    public int subarraySum2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;

        int res = 0, sum = 0;
        // k:sum v:count of this sum
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        for (int n : nums) {
            sum += n;
            if (preSum.containsKey(sum - k)) {
                res += preSum.get(sum - k);
            }
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }
        return res;
    }

}
