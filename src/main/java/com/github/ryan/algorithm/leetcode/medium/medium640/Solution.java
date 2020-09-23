package com.github.ryan.algorithm.leetcode.medium.medium640;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class Solution {

    // use regex
    public String solveEquation(String equation) {
        String[] lr = equation.split("=");
        int l = 0, r = 0;
        for (String x : lr[0].split("(?=\\+)|(?=-)")) {
            if (x.indexOf("x") >= 0) {
                l += Integer.parseInt(coeff(x));
            } else {
                r -= Integer.parseInt(x);
            }
        }

        for (String x : lr[1].split("(?=\\+)|(?=-)")) {
            if (x.indexOf("x") >= 0) {
                l -= Integer.parseInt(coeff(x));
            } else {
                r += Integer.parseInt(x);
            }
        }

        if (l == 0) {
            return r == 0 ? "Infinite solutions" : "No solution";
        } else {
            return "x=" + r / l;
        }
    }

    private String coeff(String x) {
        int len = x.length();
        if (len > 1 && x.charAt(len - 2) >= '0' && x.charAt(len - 2) <= '9') {
            return x.replace("x", "");
        } else {
            return x.replace("x", "1");
        }
    }

    public static void main(String[] args) {
        String str = "x+5-3+x";
        // String[] res = str.split("\\+|-");
        String[] res = str.split("(?=\\+)|(?=-)");
        // res = [x, +5, -3, +x]
        log.info("res = {}", Arrays.toString(res));
    }
}
