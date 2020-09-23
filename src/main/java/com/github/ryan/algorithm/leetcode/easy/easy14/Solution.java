package com.github.ryan.algorithm.leetcode.easy.easy14;

import java.util.Arrays;

/**
 * @author Ryan-hou
 * @description:
 * @className: Solution
 * @date February 22,2017
 */
public class Solution {

    /**
     * 方法一:
     * 巧用 indexOf() API方法,从字符串尾部逐渐向前缩短,以求得最长前缀串
     */
    private static String longestCommonPrefixOne(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        String prefix = strs[0];
        int i = 1;
        while(i < strs.length){
            while(strs[i].indexOf(prefix) != 0)
                prefix = prefix.substring(0, prefix.length()-1);
            i++;
        }
        return prefix;
    }

    /**
     * 方法二:
     * 先排序,排序后只需要对比第一个和最后一个字符串即可
     */
    private static String longestCommonPrefixTwo(String[] strs) {
        StringBuilder result = new StringBuilder();
        if (strs != null && strs.length > 0) {
            Arrays.sort(strs);
            char[] a = strs[0].toCharArray();
            char[] b = strs[strs.length - 1].toCharArray();

            for (int i = 0; i < a.length; i++) {
                if (b.length > i && b[i] == a[i]) {
                    result.append(b[i]);
                } else {
                    return result.toString();
                }
            }
            return result.toString();
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String[] test = {"hello1", "hello2", "hello3", "hello4"};
        String commonPrefix = longestCommonPrefixTwo(test);
        System.out.println("Longest Common prefix: " + commonPrefix);
    }
}
