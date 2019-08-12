package com.github.ryan.leetcode.algorithm.medium.medium166;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 12,2019
 */
public class Solution {

    public String fractionToDecimal(int numerator, int denominator) {
        int n = numerator, d = denominator;
        if (n == 0) return "0";
        // avoid int overflow
        long nL = (long) n, dL = (long) d;
        boolean positive = (nL * dL) < 0L ? false : true;
        nL = Math.abs(nL);
        dL = Math.abs(dL);
        long intPart = nL / dL;
        long rem = nL % dL;
        if (rem == 0) {
            return positive ? String.valueOf(intPart) : "-" + String.valueOf(intPart);
        }

        StringBuilder b = new StringBuilder();
        // key -> rem, value -> rem / d's index in StringBuilder
        Map<Long, Integer> map = new HashMap<>();
        int idx = 0;
        while (rem != 0 && !map.containsKey(rem)) {
            map.put(rem, idx++);
            // imitate division
            nL = rem * 10;
            rem = nL % dL;
            b.append(nL / dL);
        }

        if (rem == 0) {
            String res = intPart + "." + b.toString();
            return positive ? res : "-" + res;
        }
        b.insert(map.get(rem), "(");
        b.append(")");
        String res = intPart + "." + b.toString();
        return positive ? res : "-" + res;
    }

}
