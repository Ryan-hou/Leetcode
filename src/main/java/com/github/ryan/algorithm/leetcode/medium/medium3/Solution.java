package com.github.ryan.algorithm.leetcode.medium.medium3;

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

    /**
     * 使用滑动窗口，时间复杂度为O(n)
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        char[] charArray = s.toCharArray();
        int[] freq = new int[256]; // 256个ascii字符，用来记录字符重复
        int l = 0, r = -1; // 滑动窗口为s[l,r]
        int res = 0;

        while (l < charArray.length) {
            // 数组操作注意数组下标
            if (r + 1 < charArray.length && freq[charArray[r+1]] == 0) {
                //r++;
                //freq[s.charAt(r)]++;
                freq[charArray[++r]]++;
            } else {
                //freq[charArray[l]]--;
                //l++;
                freq[charArray[l++]]--;
            }

            res = Math.max(res, r-l+1);
        }
        return res;
    }

    public static void main(String[] args) {
        String test = "pwwkew";
        System.out.println(lengthOfLongestSubstring(test));
    }
}
