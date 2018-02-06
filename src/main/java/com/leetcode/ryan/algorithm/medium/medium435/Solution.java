package com.leetcode.ryan.algorithm.medium.medium435;

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

    private static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    /**
     * 思路一：
     * 使用动态规划，思路与LIS（Longest Increasing Subsequence）类似
     * 时间复杂度：O(n^2)
     * @param intervals
     * @return
     */
    public static int eraseOverlapIntervalsUseDp(Interval[] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, (o1, o2) -> {
                    if (o1.start != o2.start) {
                        return o1.start - o2.start;
                    } else {
                        return o1.end - o2.end;
                    }
                }
        );

        // memo[i]表示使用intervals[0...i]的区间能构成的最长不重叠区间序列
        int[] memo = new int[intervals.length];
        Arrays.fill(memo, 1);

        for (int i = 1; i < intervals.length; i++) {
            // memo[i]
            for (int j = 0; j < i; j++) {
                if (intervals[i].start >= intervals[j].end) {
                    memo[i] = Math.max(memo[i], 1 + memo[j]);
                }
            }
        }

        int res = 1;
        for (int i = 1; i < memo.length; i++) {
            res = Math.max(res, memo[i]);
        }
        return intervals.length - res;
    }

    /**
     * 思路二：
     * 使用贪心算法
     * 时间复杂度O(nlogn) (需要先排序)
     * 每次选择中，每个区间的结尾很重要，结尾越小，后面越有可能容纳更多区间
     * 按照区间的结尾排序，每次选择结尾最早的，且和前一个区间不重叠的区间
     *
     * 贪心选择性质证明：
     * 使用反证法
     * 贪心算法为A，最优算法为O，发现A完全能代替O，且不影响求出最优解
     *
     * @param intervals
     * @return
     */
    public static int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, (o1, o2) -> {
            if (o1.end != o2.end) {
                return o1.end - o2.end;
            } else {
                return o1.start - o2.start;
            }
        });

        int res = 1;
        int pre = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start >= intervals[pre].end) {
                res++;
                pre = i;
            }
        }
        return intervals.length - res;
    }


    public static void main(String[] args) {
        // [1,2], [2,3], [3,4], [1,3]
        Interval[] intervals = {new Interval(1, 2)
                , new Interval(2, 3)
                , new Interval(3, 4)
                , new Interval(1, 3)};

        log.info("Erase {} overlap intervals.", eraseOverlapIntervals(intervals));
    }


}
