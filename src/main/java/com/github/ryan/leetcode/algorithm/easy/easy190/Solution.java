package com.github.ryan.leetcode.algorithm.easy.easy190;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date July 19,2018
 */
@Slf4j
public class Solution {

    // you need treat n as an unsigned value
    public static int reverseBits(int n) {
        // quick return
        // if n == 0 or n == -1 or n的二进制表示为回文序列

        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            int end = n & 1; // 取n最后一位
            res += end;
            n >>>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int a = 43261596;
//        int a = 0;
//        int a = -1;
//        int a = 1;
        log.info("{} reverseBits = {}", a, reverseBits(a)); // 964176192
    }
}
