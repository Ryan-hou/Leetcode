package com.github.ryan.algorithm.leetcode.medium.medium12;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 25,2019
 */
public class Solution {

    public String intToRoman(int num) {
        Pair[] map = buildMap();
        StringBuilder b = new StringBuilder();
        for (int i = map.length - 1; i >= 0; i--) {
            if (num >= map[i].val) {
                int count = num / map[i].val;
                num -= count * map[i].val;
                append(b, map[i].symbol, count);
            }
        }
        return b.toString();
    }

    private static final class Pair {
        int val;
        String symbol;
        Pair(int val, String symbol) {
            this.val = val;
            this.symbol = symbol;
        }
    }

    private Pair[] buildMap() {
        Pair[] map = new Pair[13];
        map[0] = new Pair(1, "I");
        map[1] = new Pair(4, "IV");
        map[2] = new Pair(5, "V");
        map[3] = new Pair(9, "IX");
        map[4] = new Pair(10, "X");
        map[5] = new Pair(40, "XL");
        map[6] = new Pair(50, "L");
        map[7] = new Pair(90, "XC");
        map[8] = new Pair(100, "C");
        map[9] = new Pair(400, "CD");
        map[10] = new Pair(500, "D");
        map[11] = new Pair(900, "CM");
        map[12] = new Pair(1000, "M");
        return map;
    }

    private void append(StringBuilder b, String str, int times) {
        while (times > 0) {
            b.append(str);
            times--;
        }
    }
}
