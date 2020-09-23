package com.github.ryan.algorithm.leetcode.easy.easy415;

public class Solution {

    public String addStrings(String num1, String num2) {
        if (num1 == null || num2 == null) return null;
        if (num1.length() == 0) return num2;
        if (num2.length() == 0) return num1;

        StringBuilder res = new StringBuilder();
        int len1 = num1.length(), len2 = num2.length();
        int i = len1 - 1, j = len2 - 1;
        int len = Math.min(len1, len2);
        int carry = 0;
        while (len-- > 0) {
            char ch1 = num1.charAt(i--);
            char ch2 = num2.charAt(j--);
            int sum = ch1 - '0' + (ch2 - '0');
            int n = (sum + carry) % 10;
            carry = (sum + carry) / 10;
            res.append(n);
        }

        while (i >= 0) {
            char ch1 = num1.charAt(i--);
            int n = (ch1 - '0' + carry) % 10;
            carry = (ch1 - '0' + carry) / 10;
            res.append(n);
        }
        while (j >= 0) {
            char ch2 = num2.charAt(j--);
            int n = (ch2 - '0' + carry) % 10;
            carry = (ch2 - '0' + carry) / 10;
            res.append(n);
        }
        if (carry != 0) res.append(carry);
        return res.reverse().toString();
    }

}
