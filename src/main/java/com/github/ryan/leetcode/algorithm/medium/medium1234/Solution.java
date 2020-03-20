package com.github.ryan.leetcode.algorithm.medium.medium1234;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int balancedString(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : s.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        int len = s.length();
        int avg = len / 4;
        int extraCount = 0;
        // key -> char, value -> count of this extra char
        Map<Character, Integer> extraMap = new HashMap<>();
        for (char ch : freq.keySet()) {
            extraMap.put(ch, 0);
            int sub = freq.get(ch) - avg;
            if (sub > 0) {
                extraCount += sub;
                extraMap.put(ch, sub);
            }
        }

        if (extraCount == 0) {
            return 0;
        }

        // Sliding window
        int res = len;
        int l = 0, r = 0;
        while (r < len) {
            char rCh = s.charAt(r);
            if (extraMap.get(rCh) > 0) {
                extraCount--;
            }
            extraMap.put(rCh, extraMap.get(rCh) - 1);
            r++;
            while (extraCount == 0) {
                res = Math.min(res, r - l);
                char lCh = s.charAt(l);
                if (extraMap.get(lCh) >= 0) {
                    extraCount++;
                }
                extraMap.put(lCh, extraMap.get(lCh) + 1);
                l++;
            }
        }

        return res;
    }

}
