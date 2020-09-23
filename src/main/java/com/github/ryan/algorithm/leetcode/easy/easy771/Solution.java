package com.github.ryan.algorithm.leetcode.easy.easy771;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date December 29,2018
 */
public class Solution {

    // 数组作为查找表使用(map)
    public int numJewelsInStones(String J, String S) {
        if (J == null || S == null) return 0;

        // ASCII: 'A'=65 'a'=97
        // 97 + 25 - 65 + 1 = 58
        int[] map = new int[58];
        for (char c : J.toCharArray()) {
            map[c - 'A']++;
        }

        int num = 0;
        for (char c : S.toCharArray()) {
            if (map[c - 'A'] == 1) {
                num++;
            }
        }
        return num;
    }

}
