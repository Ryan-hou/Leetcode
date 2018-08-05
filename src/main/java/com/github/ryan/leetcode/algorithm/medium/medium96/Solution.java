package com.github.ryan.leetcode.algorithm.medium.medium96;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date March 16,2018
 */
@Slf4j
public class Solution {


    /**
     * 思路：DP
     * G(n): the number of unique BST for a sequence of length n
     * F(i, n), 1 =< i <= n：the number of unique BST, where the number i is the root
     * of BST, and the sequence ranges from 1 to n
     *
     * G(n) = F(1,n) + F(2,n) + .... + F(n,n)
     * F(i,n) = G(i-1) * G(n-i), 1 <= i <= n
     *
     * so: 状态转移方程为
     * G(n) = G(0)*G(n-1) + G(1)*G(n-2) + .... + G(n-1)*G(0)
     * @param n
     * @return
     */
    public int numTrees(int n) {
        assert n >= 0;

        // dp[k] 表示从1..k构成的bst个数
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j ++) {
                // dp[k]中，以i为根的bst个数为：dp[i-1]*dp[k-i]
                // dp[k]等于分别以1到k为根节点组成的bst的总个数
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }


    public static void main(String[] args) {

    }
}
