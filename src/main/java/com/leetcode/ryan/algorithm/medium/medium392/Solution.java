package com.leetcode.ryan.algorithm.medium.medium392;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date June 01,2018
 */
@Slf4j
public class Solution {

    // 定义两个指针分别指向两个字符串，时间复杂度为O(s+t)
    public static boolean isSubsequence(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        if (s.length() > t.length()) {
            return false;
        }

        int si = 0, ti = 0;
        while (si < s.length() && ti < t.length()) {

            // 数组一定要考虑清楚下标起始值和越界情况(charAt)
            while (s.charAt(si) != t.charAt(ti)) {
                ti++;

                if (ti == t.length()) {
                    return false;
                }
            }

            si++;
            ti++;
        }
        return si == s.length() ? true : false;
    }

    public static void main(String[] args) {
        String s = "axc", t = "ahbgdc";
        log.info("isSubsequence ? {}", isSubsequence(s, t));
    }
}
