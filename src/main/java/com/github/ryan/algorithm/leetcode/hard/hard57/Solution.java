package com.github.ryan.algorithm.leetcode.hard.hard57;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 23,2019
 */
public class Solution {

    // time complexity: O(n)
    // space complexity: O(n)
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length == 0) return new int[][] { newInterval };

        List<int[]> ints = new ArrayList<>(Arrays.asList(intervals));
        ints.add(getInsertPos(intervals, newInterval), newInterval);
        return mergeIntervals(ints).toArray(new int[][] {{ 0 }});
    }


    // LC 35 Search Insert Position
    private int getInsertPos(int[][] ins, int[] newIn) {
        int len = ins.length;
        if (newIn[0] <= ins[0][0]) return 0;
        if (newIn[0] >= ins[len - 1][0]) return len;

        int l = 0;
        int r = len - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (ins[mid][0] == newIn[0]) return mid;
            if (ins[mid][0] > newIn[0]) {
                r = mid - 1;
            } else {
                // ins[mid][0] < newIn[0]
                l = mid + 1;
            }
        }
        return l;
    }

    // LC 56 Merge Intervals
    private List<int[]> mergeIntervals(List<int[]> ints) {
        List<int[]> res = new ArrayList<>();
        int[] lastInt = ints.get(0);
        res.add(lastInt);
        for (int i = 1; i < ints.size(); i++) {
            int[] curInt = ints.get(i);
            if (curInt[0] <= lastInt[1]) {
                lastInt[1] = Math.max(lastInt[1], curInt[1]);
            } else {
                res.add(curInt);
                lastInt = curInt;
            }
        }
        return res;
    }
}
