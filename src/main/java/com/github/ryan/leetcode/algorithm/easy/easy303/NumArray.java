package com.github.ryan.leetcode.algorithm.easy.easy303;

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */

public class NumArray {

    // 处理静态数据，先预处理所有数据供后续调用
    // sum[i]: sum of nums[0, i - 1]
    private int[] sum;

    public NumArray(int[] nums) {
        sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        // sum[j + 1] -> nums[0, j], sum[i] -> nums[0, i - 1]
        return sum[j + 1] - sum[i];
    }

}
