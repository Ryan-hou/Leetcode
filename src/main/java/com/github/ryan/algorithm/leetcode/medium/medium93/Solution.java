package com.github.ryan.algorithm.leetcode.medium.medium93;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 24,2019
 */
public class Solution {

    // backtracking
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 12) return res;

        restoreHelper(s, 0, new ArrayList<>(), res);
        return res;
    }

    private void restoreHelper(String s, int num, List<String> ipAddr, List<String> res) {
        if (num == 4) {
            if ("".equals(s)) {
                StringBuilder b = new StringBuilder();
                for (String addr : ipAddr) {
                    b.append(addr);
                }
                res.add(b.toString());
            }
            return;
        }

        // i -> ip str's length
        for (int i = 1; i < 4; i++) {
            if (s.length() < i) continue;
            String str = s.substring(0, i);
            // corner case
            if (str.charAt(0) == '0' && str.length() > 1) continue;
            int ipNum = Integer.valueOf(str);
            if (ipNum <= 255) {
                ipAddr.add(ipAddr.size() <= 0 ? str : "." + str);
                restoreHelper(s.substring(i), num + 1, ipAddr, res);
                // backtracking
                ipAddr.remove(ipAddr.size() - 1);
            }
        }
    }

}
