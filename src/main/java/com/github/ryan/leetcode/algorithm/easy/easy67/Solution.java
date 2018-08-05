package com.github.ryan.leetcode.algorithm.easy.easy67;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date March 02,2018
 */
@Slf4j
public class Solution {

    public static String addBinary(String a, String b) {
        assert a != null && b != null;
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0) { sum += a.charAt(i--) - '0'; }
            if (j >= 0) { sum += b.charAt(j--) - '0'; }
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        String a = "1010", b = "101";
        log.info("result = {}", addBinary(a, b));
    }
}
