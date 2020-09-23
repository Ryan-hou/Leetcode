package com.github.ryan.algorithm.leetcode.easy.easy575;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int distributeCandies(int[] candies) {
        if (candies == null || candies.length < 1) {
            return 0;
        }

        int num = candies.length / 2;
        Set<Integer> kindSet = new HashSet<>();
        for (int kind : candies) {
            kindSet.add(kind);
        }
        int kindNum = kindSet.size();
        return kindNum >= num ? num : kindNum;
    }

}
