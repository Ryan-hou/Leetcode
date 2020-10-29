package com.github.ryan.algorithm.leetcode.medium.medium1286;

import java.util.LinkedList;
import java.util.List;

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator obj = new CombinationIterator(characters, combinationLength);
 * String param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
public class CombinationIterator {

    private List<String> combinations;
    private int n;
    private int len;
    private String characters;


    public CombinationIterator(String characters, int combinationLength) {
        this.characters = characters;
        n = characters.length();
        len = combinationLength;
        // 使用链表方便移除链首元素
        combinations = new LinkedList<>();

        dfs(0, new StringBuilder());
    }

    private void dfs(int first, StringBuilder cur) {
        if (cur.length() == len) {
            combinations.add(cur.toString());
            return;
        }

        for (int i = first; i < n; i++) {
            cur.append(characters.charAt(i));
            dfs(i + 1, cur);
            // backtracking
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    public String next() {
        return combinations.remove(0);
    }

    public boolean hasNext() {
        return !combinations.isEmpty();
    }
}
