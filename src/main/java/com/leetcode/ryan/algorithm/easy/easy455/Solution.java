package com.leetcode.ryan.algorithm.easy.easy455;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date February 06,2018
 */
@Slf4j
public class Solution {

    /**
     * 使用贪心算法：
     * 时间复杂度为 O(nlogn),需要先对数组进行排序
     * @param g
     * @param s
     * @return
     */
    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int si = s.length - 1;
        int gi = g.length - 1;
        int res = 0;

        while (si >= 0 && gi >= 0) {
            if (s[si] >= g[gi]) {
                res++;
                si--;
                gi--;
            } else {
                gi--;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] g = {1, 2, 3};
        int[] s = {1, 2};
        log.info("Content children size = {}", findContentChildren(g, s));
    }
}
