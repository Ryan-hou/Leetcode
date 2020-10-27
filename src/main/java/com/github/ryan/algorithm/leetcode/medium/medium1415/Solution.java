package com.github.ryan.algorithm.leetcode.medium.medium1415;

public class Solution {

    private char[] chs = {'a', 'b', 'c'};
    private int count;

    public String getHappyString(int n, int k) {
        count = 0;
        StringBuilder b = new StringBuilder();
        return dfs(n, k, '0', b) ? b.toString() : "";
    }


    private boolean dfs(int n, int k, char last, StringBuilder b) {

        if (b.length() == n) {
            count++;
            return count == k;
        }

        for (char ch : chs) {
            if (ch != last) {
                b.append(ch);
                if (dfs(n, k, ch, b)) {
                    return true;
                }
                // backtracking
                b.deleteCharAt(b.length() - 1);
            }
        }
        return false;
    }

}
