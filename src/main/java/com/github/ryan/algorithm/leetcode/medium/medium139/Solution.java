package com.github.ryan.algorithm.leetcode.medium.medium139;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 14,2019
 */
public class Solution {

    // use dp
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        int len = s.length();

        // dp[i] -> s[0...i] word break result
        boolean[] dp = new boolean[len];
        dp[0] = dict.contains(s.substring(0, 1));
        for (int i = 1; i < len; i++) {
            dp[i] = dict.contains(s.substring(0, i + 1));
            for (int j = 0; j < i && !dp[i]; j++) {
                dp[i] = dp[j] && dict.contains(s.substring(j + 1, i + 1));
            }
        }
        return dp[len - 1];
    }
}
