package com.github.ryan.algorithm.leetcode.medium.medium6;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 06,2019
 */
public class Solution {

    public String convert(String s, int numRows) {
        if (s == null || s.length() < 1 || numRows == 1) return s;

        List<StringBuilder> strRows = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            strRows.add(new StringBuilder());
        }

        boolean topDown = true;
        int idx = 0;
        while (idx < s.length()) {
            if (topDown) {
                for (int i = 0; i < numRows && idx < s.length(); i++) {
                    strRows.get(i).append(s.charAt(idx));
                    idx++;
                }
                topDown = false;
            } else {
                for (int i = numRows - 2; i > 0 && idx < s.length(); i--) {
                    strRows.get(i).append(s.charAt(idx));
                    idx++;
                }
                topDown = true;
            }
        }

        StringBuilder res = new StringBuilder();
        for (StringBuilder b : strRows) {
            res.append(b);
        }
        return res.toString();
    }
}
