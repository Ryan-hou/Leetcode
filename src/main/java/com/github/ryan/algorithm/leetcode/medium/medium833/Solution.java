package com.github.ryan.algorithm.leetcode.medium.medium833;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        // key -> index in S, value -> source and target string at this index
        Map<Integer, String[]> dict = new HashMap<>();
        for (int i = 0; i < indexes.length; i++) {
            if (S.startsWith(sources[i], indexes[i])) {
                dict.put(indexes[i], new String[] { sources[i], targets[i] });
            }
        }

        StringBuilder b = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            if (dict.containsKey(i)) {
                String[] pair = dict.get(i);
                b.append(pair[1]);
                i += pair[0].length() - 1;
            } else {
                b.append(S.charAt(i));
            }
        }
        return b.toString();
    }

}
