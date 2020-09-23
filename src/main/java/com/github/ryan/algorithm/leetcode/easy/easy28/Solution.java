package com.github.ryan.algorithm.leetcode.easy.easy28;

/**
 * @author Ryan-hou
 * @description:
 * @className: Solution
 * @date March 04,2017
 */
public class Solution {

    /**
     * 法一:
     * 母串不动,右移子串;匹配失败后,子串右移一个字符,继续从子串的头部开始比较
     * 定义两个指针分别代表母串和子串的位置,然后移动匹配,时间复杂度为 O(m*n) m,n分别为子串和母串的长度
     * 优点: 思路简洁, 参考别人的实现方式修改后为如下方式,很优雅简洁
     */
    public static int strStr(String haystack, String needle) {
        for (int i = 0, haystackLength = haystack.length(); ; i++) {
            // i 为指向haystack的位置指针, j 为指向 needle 的位置指针
            for (int j = 0, needleLength = needle.length(); ; j++) {
                if (j == needleLength) return i;
                if (i + j == haystackLength) return -1;
                if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
    }



    private static void getNextArray(String needle, int[] next) {
        // next[] 数组为 模式串(needle)的子串S(从模式串起始处到当前匹配位置的前一个字符) 的最长匹配前缀串和后缀串的长度
        // next[0] 为 －1；
        /**
         * 假设 next[n] = k, 则 needle[0 ~ k-1] same as needle[n-k ~ n-1]
         * if needle[k] == needle[n] then next[n+1] = next[n] + 1 = k + 1
         * else 缩小  0~k 的范围为  0 ~ next[k]: needle[0 ~ next[k]-1]
         *  if needle[next[k]] == needle[n] then next[n+1] = next[k] + 1
         *  else next[next[k]]: ....
         *  until: next[0] = -1, next[n+1] = 0;
         */
        char[] source = needle.toCharArray();
        for (int i = 0, sourceLength = source.length; i < sourceLength; i++) {
            if (i == 0) {
                next[i] = -1;
            } else {
                putRightLength4Next(source, next, i, i-1);
            }

        }
    }

    private static void putRightLength4Next(char[] source, int[] next, int i, int preNext) {
        if (next[preNext] == -1) {
            // 递归出口
            next[i] = 0;
        } else {
            if (source[i - 1] == source[next[preNext]]) {
                next[i] = next[preNext] + 1;
            } else {
                // 递归调用
                preNext = next[preNext];
                putRightLength4Next(source, next, i, preNext);
            }
        }
    }

    private static int KMP(String haystack, String needle, int[] next) {
        int i = 0, j = 0;
        char[] source = needle.toCharArray();
        char[] destination = haystack.toCharArray();
        while (j < source.length && j < destination.length) {
            if (source[j] == destination[i]) {
                i++;
                j++;
            } else {
                j = next[j];
                if (j == -1) {
                    j = 0;
                    i++;
                }
            }
        }
        if (j == source.length) return i - source.length;
        else return -1;
    }

    /**
     * 方法二:
     * 使用 KMP 算法
     * 第一种方法的弊端在于每次匹配失败后,母串仅移动一个字符,子串则从头开始比较;KMP算法优化了比较失败后的操作,
     * 通过next数组记录了子串的最长前缀串和后缀串,在比较失败时,左移母串到next数组的对应位置继续匹配子串,加快了
     * 比较操作,时间复杂度为 O(m+n)
     */
    public static int strStrTwo(String haystack, String needle) {
        if (haystack == null || needle == null || haystack.length() <
                needle.length())
            return -1;
        if ("".equals(needle))
            return 0;
        int[] next = new int[needle.length()];
        getNextArray(needle, next);
        return KMP(haystack, needle, next);
    }


    public static void main(String[] args) {
        String haystack = "abcabcd";
        String needle = "bcd";
        int index = strStr(haystack, needle);
        System.out.println("Method1-Index: " + index);
        System.out.println("Method2-Index: " + strStr(haystack, needle));
    }
}
