package com.github.ryan.algorithm.leetcode.medium.medium784;

import java.util.*;

public class Solution {

    /**
     * 图论建模 -> 字符串为顶点，字符串转移为边
     * @param S
     * @return
     */
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        // BFS
        Queue<String> q = new ArrayDeque<>();
        q.offer(S);
        visited.add(S);
        res.add(S);
        while (!q.isEmpty()) {
            String cur = q.poll();

            char[] chs = cur.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                if (!Character.isDigit(chs[i])) {
                    char old = chs[i];
                    if (Character.isLowerCase(chs[i])) {
                        chs[i] = Character.toUpperCase(chs[i]);
                    } else {
                        chs[i] = Character.toLowerCase(chs[i]);
                    }
                    String next = new String(chs);
                    if (!visited.contains(next)) {
                        visited.add(next);
                        q.offer(next);
                        res.add(next);
                    }

                    // reset char
                    chs[i] = old;
                }
            }
        } // end while

        return res;
    }

}
