package com.github.ryan.leetcode.algorithm.hard.hard212;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 12,2019
 */
public class Solution {

    private static class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word = null;
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, res);
            }
        }
        return res;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode p = root;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (p.next[idx] == null) {
                    p.next[idx] = new TrieNode();
                }
                p = p.next[idx];
            }
            p.word = word;
        }
        return root;
    }

    private void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
        char ch = board[i][j];
        if (ch == '#' || p.next[ch - 'a'] == null) return;
        p = p.next[ch - 'a'];
        if (p.word != null) {
            res.add(p.word);
            p.word = null;
        }

        board[i][j] = '#';
        if (i > 0) dfs(board, i - 1, j, p, res);
        if (j > 0) dfs(board, i, j - 1, p, res);
        if (i < board.length - 1) dfs(board, i + 1, j, p, res);
        if (j < board[0].length - 1) dfs(board, i, j + 1, p, res);
        board[i][j] = ch;
    }
}
