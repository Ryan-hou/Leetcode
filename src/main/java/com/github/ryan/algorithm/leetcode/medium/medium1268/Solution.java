package com.github.ryan.algorithm.leetcode.medium.medium1268;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    private static class TrieNode {
        boolean isWord = false;
        TrieNode[] children = new TrieNode[26];
    }

    private static class Trie {

        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode cur = root;
            for (char ch : word.toCharArray()) {
                if (cur.children[ch - 'a'] == null) {
                    cur.children[ch - 'a'] = new TrieNode();
                }
                cur = cur.children[ch - 'a'];
            }
            cur.isWord = true;
        }

        public TrieNode search(String word) {
            TrieNode cur = root;
            for (char ch : word.toCharArray()) {
                if (cur.children[ch - 'a'] == null) {
                    return null;
                }
                cur = cur.children[ch - 'a'];
            }
            return cur;
        }

        public static List<String> preOrder(TrieNode node, String prefix) {
            List<String> res = new ArrayList<>();
            if (node == null) return res;

            if (node.isWord) res.add(prefix);
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    String newPrefix = prefix + (char) (i + 'a');
                    // recursion
                    List<String> subList = preOrder(node.children[i], newPrefix);
                    res.addAll(subList);
                }
            }
            return res;
        }

    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList<>(searchWord.length());
        Trie t = new Trie();
        for (String p : products) {
            t.insert(p);
        }

        for (int i = 1; i <= searchWord.length(); i++) {
            String prefix = searchWord.substring(0, i);
            TrieNode node = t.search(prefix);
            List<String> curList = Trie.preOrder(node, prefix);
            res.add(curList.subList(0, Math.min(3, curList.size())));

        }
        return res;
    }

}
