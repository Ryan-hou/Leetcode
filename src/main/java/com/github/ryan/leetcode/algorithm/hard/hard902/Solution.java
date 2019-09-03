package com.github.ryan.leetcode.algorithm.hard.hard902;

public class Solution {

    public int atMostNGivenDigitSet(String[] D, int N) {
        String nStr = String.valueOf(N);
        int len = nStr.length();
        // dp[i] is the number of ways to write a valid number
        // if N became N[i], N[i + 1], ...
        // For example, if N = 2345, then dp[0] would be the number
        // of valid numbers at most 2345
        int[] dp = new int[len + 1];
        dp[len] = 1;

        for (int i = len - 1; i >= 0; i--) {
            // calculate dp[i]
            int val = nStr.charAt(i) - '0';
            for (String d : D) {
                int dVal = Integer.valueOf(d);
                if (dVal < val) {
                    dp[i] += Math.pow(D.length, len - i - 1);
                } else if (dVal == val) {
                    dp[i] += dp[i + 1];
                } else {
                    // dVal > val
                    break;
                }
            }
        }

        for (int i = 1; i < len; i++) {
            dp[0] += Math.pow(D.length, i);
        }
        return dp[0];
    }
}
