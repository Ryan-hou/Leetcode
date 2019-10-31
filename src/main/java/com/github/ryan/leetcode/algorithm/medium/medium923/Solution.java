package com.github.ryan.leetcode.algorithm.medium.medium923;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int threeSumMulti(int[] A, int target) {
        int n = 1000000007;
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            int sum = target - A[i];
            // key -> a number of A, value -> count of this key
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i + 1; j < A.length; j++) {
                count = (count + map.getOrDefault(sum - A[j], 0)) % n;
                map.put(A[j], map.getOrDefault(A[j], 0) + 1);
            }
        }
        return count;
    }

}
