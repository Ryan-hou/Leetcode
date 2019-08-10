package com.github.ryan.leetcode.algorithm.medium.medium210;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 10,2019
 */
public class Solution {

    private int position = 0;

    // use Topological sort, reference to leetcode207
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        int[] mark = new int[numCourses];
        List<Integer>[] schedule = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            schedule[i] = new ArrayList<>();
        }
        for (int[] seq : prerequisites) {
            schedule[seq[0]].add(seq[1]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (findCycle(i, mark, res, schedule)) {
                return new int[0];
            }
        }
        return res;
    }

    private boolean findCycle(int cur, int[] mark,
                              int[] res, List<Integer>[] schedule) {
        if (mark[cur] == 1) return true;
        if (mark[cur] == 2) return false;
        mark[cur] = 1;
        for (Integer pre : schedule[cur]) {
            if (findCycle(pre, mark, res, schedule)) {
                return true;
            }
        }
        mark[cur] = 2;
        res[position++] = cur;
        return false;
    }
}
