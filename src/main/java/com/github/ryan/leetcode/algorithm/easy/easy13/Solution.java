package com.github.ryan.leetcode.algorithm.easy.easy13;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 25,2019
 */
public class Solution {

    public int romanToInt(String s) {
        if (s == null || s.length() < 1) return 0;

        int[] map = new int[26];
        map['I' - 'A'] = 1;
        map['V' - 'A'] = 5;
        map['X' - 'A'] = 10;
        map['L' - 'A'] = 50;
        map['C' - 'A'] = 100;
        map['D' - 'A'] = 500;
        map['M' - 'A'] = 1000;

        int res = 0;
        char[] arr = s.toCharArray();
        int i = 0;
        int len = arr.length;
        while (i < len) {
            char ch = arr[i];
            if (ch == 'C' && i + 1 < len
                    && (arr[i + 1] == 'D' || arr[i + 1] == 'M')) {
                res += (map[arr[i + 1] - 'A'] - 100);
                i += 2;
            } else if (ch == 'X' && i + 1 < len
                    && (arr[i + 1] == 'L' || arr[i + 1] == 'C')) {
                res += (map[arr[i + 1] - 'A'] - 10);
                i += 2;
            } else if (ch == 'I' && i + 1 < len
                    && (arr[i + 1] == 'V' || arr[i + 1] == 'X')) {
                res += (map[arr[i + 1] - 'A'] - 1);
                i += 2;
            } else {
                res += map[ch - 'A'];
                i += 1;
            }

        }
        return res;
    }

}
