package com.github.ryan.algorithm.leetcode.medium.medium1087;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {


    public String[] expand(String S) {
        List<String> res = new ArrayList<>();
        dfs(0, S, new StringBuilder(), res);
        Collections.sort(res);
        return res.toArray(new String[res.size()]);
    }

    private void dfs(int cur, String s, StringBuilder sb, List<String> res) {
        if (cur >= s.length()) {
            res.add(sb.toString());
            return;
        }

        if (s.charAt(cur) == '{') {
            // multiple letters
            int end = cur;
            while (s.charAt(end) != '}') {
                end++;
            }
            String sub = s.substring(cur + 1, end);
            String[] chs = sub.split(",");
            for (String ch : chs) {
                sb.append(ch);
                dfs(end + 1, s, sb, res);
                // backtracking
                sb.deleteCharAt(sb.length() - 1);
            }
        } else {
            // single letter
            sb.append(s.charAt(cur));
            dfs(cur + 1, s, sb, res);
            // backtracking
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
