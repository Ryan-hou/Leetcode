package com.github.ryan.algorithm.leetcode.medium.medium565;

public class Solution {

    public int arrayNesting(int[] nums) {

        boolean[] visited = new boolean[nums.length];
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                int count = 1;
                int next = nums[i];
                while (nums[i] != nums[next]) {
                    count++;
                    visited[next] = true;
                    next = nums[next];
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }

}
