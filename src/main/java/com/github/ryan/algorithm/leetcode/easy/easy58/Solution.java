package com.github.ryan.algorithm.leetcode.easy.easy58;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date February 23,2018
 */
@Slf4j
public class Solution {

    public static int lengthOfLastWord(String s) {

        if (s == null || s.trim().length() == 0) {
            return 0;
        }

        String des = s.trim();
        char[] chars = des.toCharArray();
        int tail = chars.length - 1;
        int length = 0;
        // 数组下标不变量：index >= 0 && index < length
        while (tail >= 0 && chars[tail] != ' ') {
            tail--;
            length++;
        }
        return length;
    }

    public static void main(String[] args) {
        String test = "Hello world  ";
        log.info("Length of Last word = {}", lengthOfLastWord(test));
    }
}
