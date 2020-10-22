package com.github.ryan.algorithm.leetcode.medium.medium1593;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int maxUniqueSplit(String s) {
        return dfs(s, 0, 0, new HashSet<>(), new StringBuilder());
    }


    private int dfs(String s, int cur, int cnt, Set<String> visited, StringBuilder sb) {
        if (cur >= s.length()) {
            return cnt;
        }

        sb.append(s.charAt(cur));
        int max = dfs(s, cur + 1, cnt, visited, sb);
        if (visited.add(sb.toString())) {
            // sb 为新分割出来的子串
            max = Math.max(max, dfs(s, cur + 1, cnt + 1, visited, new StringBuilder()));
            visited.remove(sb.toString());
        }
        // backtracking
        sb.deleteCharAt(sb.length() - 1);
        return max;
    }

}
