package com.github.ryan.algorithm.leetcode.easy.easy953;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Solution {

    public boolean isAlienSorted(String[] words, String order) {
        if (words == null || words.length <= 1) return true;
        // key(index) -> character, value -> character order
        int[] map = new int[26];
        for (int i = 0; i < 26; i++) {
            char ch = order.charAt(i);
            map[ch - 'a'] = i;
        }

        String start = words[0];
        for (int i = 1; i < words.length; i++) {
            String cur = words[i];
            if (!isOrdered(start, cur, map)) {
                return false;
            }
            start = cur;
        }
        return true;
    }

    private boolean isOrdered(String start, String cur, int[] map) {
        for (int i = 0; i < start.length(); i++) {
            char sch = start.charAt(i);
            if (i >= cur.length()) {
                return false;
            } else {
                char cch = cur.charAt(i);
                if (map[sch - 'a'] > map[cch - 'a']) {
                    return false;
                } else if (map[sch - 'a'] < map[cch - 'a']) {
                    return true;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        log.info("is Sorted ? {}", new Solution().isAlienSorted(new String[] { "hello", "leetcode"},
                "hlabcdefgijkmnopqrstuvwxyz"));
    }
}
