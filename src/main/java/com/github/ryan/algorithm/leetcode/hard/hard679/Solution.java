package com.github.ryan.algorithm.leetcode.hard.hard679;

public class Solution {

    public boolean judgePoint24(int[] nums) {
        if (nums == null || nums.length < 1) return false;

        double[] n = new double[nums.length];
        for (int i = 0; i < nums.length; i++) {
            n[i] = nums[i];
        }
        return helper(n);
    }

    private boolean helper(double[] n) {
        if (n.length == 1) return Math.abs(n[0] - 24) < 0.1;

        for (int i = 0; i < n.length; i++) {
            for (int j = i + 1; j < n.length; j++) {
                double[] newn = new double[n.length - 1];
                int idx = 0;
                for (int k = 0; k < n.length; k++) {
                    if (k == i || k == j) continue;

                    newn[idx] = n[k];
                    idx++;
                }

                for (double c : compute(n[i], n[j])) {
                    newn[idx] = c;
                    if (helper(newn)) return true;
                }
            }
        } // end for i
        return false;
    }

    private double[] compute(double i, double j) {
        return new double[] { i + j, i - j, j - i, i * j, i / j, j / i };
    }

}
