package com.github.ryan.algorithm.leetcode.hard.hard1220;

import java.util.Arrays;

public class Solution {

    private final int mod = 1000000007;
    private char[] vowels = {'#', 'a', 'e', 'i', 'o', 'u'};
    private int N;
    private int[][] memo;

    public int countVowelPermutation(int n) {
        N = n;
        memo = new int[n][6];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dfs(0, 0);
    }

    private int dfs(int index, int pre) {
        if (index == N) {
            return 1;
        }

        if (memo[index][pre] != -1) {
            return memo[index][pre];
        }

        long res = 0;
        for (int i = 1; i < vowels.length; i++) {
            char ch = vowels[i];
            switch (vowels[pre]) {
                case 'a' : {
                    if (ch == 'e') {
                        res = (res + dfs(index + 1, i)) % mod;
                    }
                    break;
                }
                case 'e' : {
                    if (ch == 'a' || ch == 'i') {
                        res = (res + dfs(index + 1, i)) % mod;
                    }
                    break;
                }
                case 'i' : {
                    if (ch != 'i') {
                        res = (res + dfs(index + 1, i)) % mod;
                    }
                    break;
                }
                case 'o' : {
                    if (ch == 'i' || ch == 'u') {
                        res = (res + dfs(index + 1, i)) % mod;
                    }
                    break;
                }
                case 'u' : {
                    if (ch == 'a') {
                        res = (res + dfs(index + 1, i)) % mod;
                    }
                    break;
                }
                default :
                    res = (res + dfs(index + 1, i)) % mod;
            } // end switch
        } // end for

        memo[index][pre] = (int) res;
        return memo[index][pre];
    }
}
