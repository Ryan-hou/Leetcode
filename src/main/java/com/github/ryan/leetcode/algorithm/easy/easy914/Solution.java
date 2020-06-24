package com.github.ryan.leetcode.algorithm.easy.easy914;

import java.util.Map;
import java.util.TreeMap;

public class Solution {

    public boolean hasGroupsSizeX(int[] deck) {
        TreeMap<Integer, Integer> dict = new TreeMap<>();
        for (int d : deck) {
            dict.put(d, dict.getOrDefault(d, 0) + 1);
        }
        int minDup = dict.firstEntry().getValue();
        if (minDup < 2) return false;

        minDupLabel:
        for (int i = 2; i <= minDup; i++) {
            for (Map.Entry<Integer, Integer> e : dict.entrySet()) {
                if (e.getValue() % i != 0) {
                    continue minDupLabel;
                }
            }
            return true;
        }
        return false;
    }

}
