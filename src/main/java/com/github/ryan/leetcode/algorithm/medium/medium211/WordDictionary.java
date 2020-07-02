package com.github.ryan.leetcode.algorithm.medium.medium211;

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
public class WordDictionary {

    private static class TrieNode {
        boolean isWord;
        TrieNode[] children;
        final int N = 26;

        TrieNode() {
            children = new TrieNode[N];
        }
    }

    private TrieNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode cur = root;
        for (char ch : word.toCharArray()) {
            if (cur.children[ch - 'a'] == null) {
                cur.children[ch - 'a'] = new TrieNode();
            }
            cur = cur.children[ch - 'a'];
        }
        cur.isWord = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return dfs(root, word, 0);
    }

    private boolean dfs(TrieNode cur, String word, int i) {
        if (cur == null) return false;
        if (i == word.length()) return cur.isWord;

        char ch = word.charAt(i);
        if (ch == '.') {
            for (int j = 0; j < 26; j++) {
                if (dfs(cur.children[j], word, i + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            return dfs(cur.children[ch - 'a'], word, i + 1);
        }
    }
}
