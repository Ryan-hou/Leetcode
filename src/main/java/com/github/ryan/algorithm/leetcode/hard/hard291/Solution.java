package com.github.ryan.algorithm.leetcode.hard.hard291;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

    public boolean wordPatternMatch(String pattern, String s) {
        return dfs(pattern, 0, s, 0, new HashMap<>(), new HashSet<>());
    }


    // 从 p[pidx], s[sidx] 开始尝试匹配, 看是否可以成功
    private boolean dfs(String p, int pidx, String s, int sidx, Map<Character, String> dict, Set<String> used) {
        if (pidx >= p.length() || sidx >= s.length()) {
            return pidx >= p.length() && sidx >= s.length();
        }

        if (dict.containsKey(p.charAt(pidx))) {
            String match = dict.get(p.charAt(pidx));
            return s.startsWith(match, sidx) && dfs(p, pidx + 1, s, sidx + match.length(), dict, used);
        } else {
            for (int i = sidx + 1; i + (p.length() - pidx - 1) <= s.length(); i++) {
                String prefix = s.substring(sidx, i);
                if (!used.contains(prefix)) {
                    dict.put(p.charAt(pidx), prefix);
                    used.add(prefix);
                    if (dfs(p, pidx + 1, s, i, dict, used)) {
                        return true;
                    }

                    // backtracking
                    dict.remove(p.charAt(pidx));
                    used.remove(prefix);
                }
            } // end for
            return false;
        }
    }
}
