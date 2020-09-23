package com.github.ryan.algorithm.leetcode.medium.medium56;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 12,2019
 */
@Slf4j
public class Solution {

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) return intervals;

        Arrays.sort(intervals, (a1, a2) ->
                a1[1] == a2[1] ? a1[0] - a2[0] : a1[1] - a2[1]);
        List<int[]> res = new ArrayList<>();
        int len = intervals.length;
        int[] cur = intervals[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            if (intervals[i][1] >= cur[0]) {
                // merge
                if (cur[0] > intervals[i][0]) {
                    cur[0] = intervals[i][0];
                }
            } else {
                // intervals[i][1] < cur[0]
                res.add(cur);
                cur = intervals[i];
            }
        }
        res.add(cur);

        int[][] ret = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            ret[i] = res.get(i);
        }
        return ret;
    }
}
