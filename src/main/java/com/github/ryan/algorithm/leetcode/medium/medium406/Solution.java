package com.github.ryan.algorithm.leetcode.medium.medium406;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date April 10,2019
 */
public class Solution {


    // https://leetcode.com/problems/queue-reconstruction-by-height/discuss/243710/Java-solution-in-Chinese
    public int[][] reconstructQueue(int[][] people) {
        if (people == null
                || people.length == 0
                || people[0].length == 0)
            return new int[0][0];

        Arrays.sort(people,
                (p1, p2) -> p1[0] == p2[0]
                                ? p1[1] - p2[1]
                                : p2[0] - p1[0]);
        List<int[]> res = new LinkedList<>();
        for (int[] p : people) {
            res.add(p[1], p);
        }
        return res.toArray(people);
    }
}
