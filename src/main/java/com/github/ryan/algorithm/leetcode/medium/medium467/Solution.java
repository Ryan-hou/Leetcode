package com.github.ryan.algorithm.leetcode.medium.medium467;

public class Solution {

    public int findSubstringInWraproundString(String p) {
        if (p == null || p.length() < 1) return 0;

        // dp[i] -> length of the longest consecutive character
        // until i + 'a' in p
        int[] dp = new int[26];
        dp[p.charAt(0) - 'a'] = 1;
        int count = 1;
        for (int i = 1; i < p.length(); i++) {
            int cur = p.charAt(i) - 'a';
            int prev = p.charAt(i - 1) - 'a';
            if ((prev + 1) % 26 == cur) {
                count++;
            } else {
                count = 1;
            }
            dp[cur] = Math.max(dp[cur], count);
        }

        int sum = 0;
        for (int num : dp) {
            sum += num;
        }
        return sum;
    }

}
