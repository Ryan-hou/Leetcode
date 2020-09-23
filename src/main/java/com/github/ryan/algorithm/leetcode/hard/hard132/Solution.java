package com.github.ryan.algorithm.leetcode.hard.hard132;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 25,2019
 */
public class Solution {

    // use two dp array
    public int minCut(String s) {

        int len = s.length();
        char[] ch = s.toCharArray();
        // isPalinDp[i][j] -> s[i,j] is palindrom or not
        boolean[][] isPalinDp = new boolean[len][len];
        // num[i] -> s[0,i]'s minimal cut
        int[] num = new int[len];
        for (int i = 0; i < len; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j <= i; j++) {
                if (ch[j] == ch[i] && (j + 1 >= i || isPalinDp[j + 1][i - 1])) {
                    isPalinDp[j][i] = true;
                    min = j == 0 ? 0 : Math.min(min, num[j - 1] + 1);
                }
            }
            num[i] = min;
        }
        return num[len - 1];
    }
}
