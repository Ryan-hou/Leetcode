package com.leetcode.ryan.algorithm.medium3;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ryan-hou
 * @description:
 * @className: Solution
 * @date February 26,2017
 */
public class Solution {

    /**
     * 法一:
     * 利用 HashSet 保存不重复的字符,定义两个指针,先后移动,取指针间最大的距离
     * 时间复杂度O(n)
     */
    public static int lengthOfLongestSubstringOne(String s) {
        if (s == null) return 0;

        int i = 0, j = 0, max = 0;
        Set<Character> set = new HashSet<>();

        while (j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                max = Math.max(max, set.size());
            } else {
                // 发现重复,移动指针i,直到重复的位置,重新开始计数
                set.remove(s.charAt(i++));
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String test = "pwwkew";
        System.out.println(lengthOfLongestSubstringOne(test));
    }
}
