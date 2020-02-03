package com.github.ryan.leetcode.algorithm.easy.easy720;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    public String longestWord(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        return trie.dfs();
    }



    private static class Node {
        char ch;
        Node[] children;
        boolean isWord;
        String word;

        public Node(char ch) {
            this.ch = ch;

            isWord = false;
            children = new Node[26];
            word = null;
        }
    }

    private static class Trie {
        Node root;
        public Trie() {
            root = new Node('0');
        }

        public void insert(String word) {
            Node cur = root;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (cur.children[idx] == null) {
                    cur.children[idx] = new Node(ch);
                }
                cur = cur.children[idx];
            }
            cur.isWord = true;
            cur.word = word;
        }


        public String dfs() {
            String res = "";
            Deque<Node> stack = new ArrayDeque<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                Node node = stack.pop();
                if (node.isWord || node == root) {
                    if (node != root) {
                        String word = node.word;
                        if (word.length() > res.length()
                                || (word.length() == res.length() && word.compareTo(res) < 0)) {
                            res = word;
                        }
                    }

                    for (Node n : node.children) {
                        if (n != null) {
                            stack.push(n);
                        }
                    }
                }
            }

            return res;
        }
    } // end Trie class
}
