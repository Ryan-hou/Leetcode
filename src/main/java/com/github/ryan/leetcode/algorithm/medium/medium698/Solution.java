package com.github.ryan.leetcode.algorithm.medium.medium698;

import java.util.Arrays;

public class Solution {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % k != 0) {
            return false;
        }
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        return canPartition(nums, k, sum / k, 0, 0, visited);
    }

    private boolean canPartition(int[] nums, int k, int part, int start, int curSum, boolean[] visited) {
        if (k == 0) return true;
        if (curSum > part) return false;
        if (curSum == part) {
            return canPartition(nums, k - 1, part, 0, 0, visited);
        }

        for (int i = start; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (canPartition(nums, k, part, i + 1, curSum + nums[i], visited)) {
                    return true;
                }
                visited[i] = false;
            }
        } // end for
        return false;
    }

}
