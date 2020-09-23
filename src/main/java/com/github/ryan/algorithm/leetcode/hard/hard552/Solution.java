package com.github.ryan.algorithm.leetcode.hard.hard552;

public class Solution {

    public int checkRecord(int n) {
        int mod = 1000000007;
        // two group: have A and no A
        // have A can end with [A, L, LL, P]
        // no A can end with [L, LL, P]
        // dp[i][j] -> length of i + 1 and end with j's record
        long[][] dp = new long[n][7];
        // have A:
        dp[0][0] = 1; // have A and end with A
        dp[0][1] = 0;
        dp[0][2] = 0;
        dp[0][3] = 0;
        // no A:
        dp[0][4] = 1; // no A and end with L
        dp[0][5] = 0;
        dp[0][6] = 1;

        for (int i = 1; i < n; i++) {
            // end with A
            dp[i][0] = (dp[i - 1][4] + dp[i - 1][5] + dp[i - 1][6]) % mod;

            // end with P
            dp[i][3] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][3]) % mod;
            dp[i][6] = (dp[i - 1][4] + dp[i - 1][5] + dp[i - 1][6]) % mod;

            // end with L
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][3]) % mod;
            dp[i][2] = dp[i - 1][1];
            dp[i][4] = dp[i - 1][6];
            dp[i][5] = dp[i - 1][4];
        }

        long res = 0;
        for (int i = 0; i < 7; i++) {
            res = (res + dp[n - 1][i]) % mod;
        }
        return (int) res;
    }

}
