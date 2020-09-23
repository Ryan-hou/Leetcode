package com.github.ryan.algorithm.leetcode.easy.easy38;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 07,2019
 */
public class Solution {

    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }

        String str = countAndSay(n - 1);
        StringBuilder res = new StringBuilder();
        int count = 1;
        int i = 1;
        for (; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                count++;
            } else {
                res.append(count).append(str.charAt(i - 1));
                count = 1;
            }
        }
        res.append(count).append(str.charAt(i - 1));
        return res.toString();
    }
}
