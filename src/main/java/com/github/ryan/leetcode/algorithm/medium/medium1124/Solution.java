package com.github.ryan.leetcode.algorithm.medium.medium1124;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int longestWPI(int[] hours) {
        int res = 0;
        int sum = 0;
        // key -> sum of days (well-performing day is 1, if not then -1)
        // value -> the index of the day
        Map<Integer, Integer> sumMap = new HashMap<>();
        for (int i = 0; i < hours.length; i++) {
            sum += (hours[i] > 8 ? 1 : -1);
            if (sum > 0) {
                res = i + 1;
            } else {
                // sum <= 0
                sumMap.putIfAbsent(sum, i);
                if (sumMap.containsKey(sum - 1)) {
                    res = Math.max(res, i - sumMap.get(sum - 1));
                }
            }
        }
        return res;
    }

}
