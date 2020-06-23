package com.github.ryan.leetcode.algorithm.hard.hard1320;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    // key -> index of word with current left finger and right finger
    // res -> min distance at this key
    private Map<String, Integer> map = new HashMap<>();

    public int minimumDistance(String word) {
        return dfs(word, 0, null, null);
    }

    private int dfs(String word, int start, int[] l, int[] r) {
        if (start >= word.length())  return 0;
        String key = getKey(start, l, r);
        if (map.containsKey(key)) {
            return map.get(key);
        }

        char cur = word.charAt(start);
        int i = (cur - 'A') / 6;
        int j = (cur - 'A') % 6;
        int useLeft = dfs(word, start + 1, new int[] {i, j}, r) + dis(l, i, j);
        int useRight = dfs(word, start + 1, l, new int[] {i, j}) + dis(r, i, j);
        int res = Math.min(useLeft, useRight);
        map.put(key, res);
        return res;
    }

    private String getKey(int start, int[] l, int[] r) {
        return start + "#" + Arrays.toString(l) + Arrays.toString(r);
    }

    private int dis(int[] cur, int i, int j) {
        if (cur == null) return 0;
        return Math.abs(cur[0] - i) + Math.abs(cur[1] - j);
    }

}
