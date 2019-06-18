package com.github.ryan.leetcode.algorithm.medium.medium208;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 18,2019
 */
public class Trie {

    private final Node root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (cur.next[idx] == null) {
                cur.next[idx] = new Node();
            }
            cur = cur.next[idx];
        }
        cur.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return contains(word, false);
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return contains(prefix, true);
    }

    private boolean contains(String str, boolean isPrefix) {
        Node cur = root;
        for (int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i) - 'a';
            if (cur.next[idx] == null) {
                return false;
            }
            cur = cur.next[idx];
        }
        return isPrefix ? true : cur.isWord;
    }

    private static class Node {

        boolean isWord;
        Node[] next;

        public Node() {
            isWord = false;
            next = new Node[26];
        }
    }
}
