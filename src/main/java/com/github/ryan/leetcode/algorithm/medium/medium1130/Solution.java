package com.github.ryan.leetcode.algorithm.medium.medium1130;

public class Solution {

    public int mctFromLeafValues(int[] arr) {
        if (arr == null || arr.length == 0) return 0;

        int[][] memo = new int[arr.length][arr.length];
        // min sum of non leaf nodes from arr[0] to arr[arr.length - 1] inclusive
        return minSumOfNonLeafNodes(arr, 0, arr.length - 1, memo);
    }

    private int minSumOfNonLeafNodes(int[] arr, int start, int end, int[][] memo) {
        if (start >= end) return 0;
        if (memo[start][end] != 0) return memo[start][end];

        int res = Integer.MAX_VALUE;
        for (int i = start; i < end; i++) {
            int left = minSumOfNonLeafNodes(arr, start, i, memo);
            int right = minSumOfNonLeafNodes(arr, i + 1, end, memo);

            int maxLeft = 0, maxRight = 0;
            for (int j = start; j <= i; j++) {
                maxLeft = Math.max(maxLeft, arr[j]);
            }
            for (int j = i + 1; j <= end; j++) {
                maxRight = Math.max(maxRight, arr[j]);
            }

            int valueOfTheNonLeafNode = maxLeft * maxRight;
            res = Math.min(res, valueOfTheNonLeafNode + left + right);
        }
        memo[start][end] = res;
        return res;
    }

}
