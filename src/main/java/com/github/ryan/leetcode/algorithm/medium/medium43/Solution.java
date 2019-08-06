package com.github.ryan.leetcode.algorithm.medium.medium43;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 06,2019
 */
@Slf4j
public class Solution {

    public String multiply(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        if (len1 > len2) {
            String tmp = num2;
            num2 = num1;
            num1 = tmp;
            int iTmp = len1;
            len1 = len2;
            len2 = iTmp;
        }
        if (len1 == 1 && num1.charAt(0) == '0') return "0";
        // len1 <= len2
        List<String> mulList = new ArrayList<>();
        for (int i = len1 - 1; i >= 0; i--) {
            int carry = 0;
            int n = num1.charAt(i) - '0';
            StringBuilder b = new StringBuilder();
            for (int j = len2 - 1; j >= 0; j--) {
                int cur = num2.charAt(j) - '0';
                int num = (cur * n + carry) % 10;
                carry = (cur * n + carry) / 10;
                b.append(num);
            }
            if (carry != 0) {
                b.append(carry);
            }
            b = b.reverse();
            appendNZero(b, len1 - 1 - i);
            mulList.add(b.toString());
        }

        int maxLen = mulList.get(mulList.size() - 1).length();
        int carry = 0;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < maxLen; i++) {
            int sum = carry;
            for (String str : mulList) {
                int cur = str.length() > i ? str.charAt(str.length() - 1 - i) - '0' : 0;
                sum += cur;
            }
            res.append(sum % 10);
            carry = sum / 10;
        }
        if (carry != 0) {
            res.append(carry);
        }
        return res.reverse().toString();
    }

    private void appendNZero(StringBuilder b, int n) {
        for (int i = 0; i < n; i++) {
            b.append(0);
        }
    }

    public static void main(String[] args) {
        log.info("res = {}", new Solution().multiply("123", "456"));
    }
}
