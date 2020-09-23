package com.github.ryan.algorithm.leetcode.medium.medium756;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        // dict[i][j] -> all chars with A + i and A + j as prefix
        Set<Character>[][] dict = new Set[7][7]; // A ~ G
        for (String str : allowed) {
            int i = str.charAt(0) - 'A';
            int j = str.charAt(1) - 'A';
            if (dict[i][j] == null) {
                dict[i][j] = new HashSet<>();
            }
            dict[i][j].add(str.charAt(2));
        }
        // DFS
        return dfs(dict, bottom, "", 0);
    }

    private boolean dfs(Set<Character>[][] dict, String bottom, String upLine, int idx) {
        if (bottom.length() == 1) return true;

        if (idx == bottom.length() - 1) {
            // process up line
            return dfs(dict, upLine, "", 0);
        }

        int i = bottom.charAt(idx) - 'A';
        int j = bottom.charAt(idx + 1) - 'A';
        if (dict[i][j] == null) return false;
        for (char ch : dict[i][j]) {
            // idx + 1 not idx++, be careful!
            if (dfs(dict, bottom, upLine + ch, idx + 1)) {
                return true;
            }
        }
        return false;
    }

}
