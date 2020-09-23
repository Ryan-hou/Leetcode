package com.github.ryan.algorithm.leetcode.medium.medium648;

import java.util.List;

public class Solution {

    private static class TrieNode {
        TrieNode[] children;
        String word;
        TrieNode() {
            children = new TrieNode[26];
        }
    }

    // use Trie
    public String replaceWords(List<String> dict, String sentence) {
        TrieNode trie = new TrieNode();
        for (String root : dict) {
            TrieNode cur = trie;
            for (char ch : root.toCharArray()) {
                if (cur.children[ch - 'a'] == null) {
                    cur.children[ch - 'a'] = new TrieNode();
                }
                cur = cur.children[ch - 'a'];
            }
            cur.word = root;
        }

        StringBuilder res = new StringBuilder();
        for (String word : sentence.split("\\s+")) {
            if (res.length() > 0) {
                res.append(" ");
            }

            TrieNode cur = trie;
            for (char ch : word.toCharArray()) {
                if (cur.children[ch - 'a'] == null || cur.word != null) {
                    break;
                }
                cur = cur.children[ch - 'a'];
            }
            res.append(cur.word != null ? cur.word : word);
        }
        return res.toString();
    }

}
