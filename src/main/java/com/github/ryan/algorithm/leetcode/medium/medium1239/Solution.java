package com.github.ryan.algorithm.leetcode.medium.medium1239;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    private int res;

    public int maxLength(List<String> arr) {

        Map<String, int[]> dict = new HashMap<>();
        for (String s : arr) {
            int[] freq = new int[26];
            for (char ch : s.toCharArray()) {
                freq[ch -'a']++;
            }

            dict.put(s, freq);
        }
        res = 0;
        dfs(0, 0, new ArrayList<>(), arr, dict);
        return res;
    }

    private void dfs(int start, int len, List<String> cur, List<String> arr, Map<String, int[]> dict) {
        if (res >= 26) return; // quick finish

        for (int i = start; i < arr.size(); i++) {
            String str = arr.get(i);
            if (isValid(dict.get(str)) && noIntersection(cur, str, dict)) {
                cur.add(str);
                len += str.length();
                res = Math.max(res, len);
                dfs(i + 1, len, cur, arr, dict);
                // backtracking
                cur.remove(cur.size() - 1);
                len -= str.length();
            }
        }
        // return;
    }

    private boolean noIntersection(List<String> cur, String str, Map<String, int[]> dict) {
        int[] newFreq = dict.get(str);
        for (String s : cur) {
            for (int i = 0; i < newFreq.length; i++) {
                if (newFreq[i] >= 1 && dict.get(s)[i] >= 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int[] freq) {
        for (int f : freq) {
            if (f > 1) {
                return false;
            }
        }
        return true;
    }

}
