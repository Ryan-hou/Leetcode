package com.github.ryan.algorithm.leetcode.hard.hard425;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    private String[] words;
    private int N;


    public List<List<String>> wordSquares(String[] words) {
        this.words = words;
        N = words[0].length();

        Trie trie = new Trie(words);

        List<List<String>> res = new ArrayList<>();
        for (String w: words) {
            ArrayList<String> square = new ArrayList<>();
            square.add(w);
            dfs(1, square, res, trie);
        }
        return res;
    }

    private void dfs(int step, ArrayList<String> square, List<List<String>> res, Trie trie) {
        if (step == N) {
            // shallow copy is enough, because String is final
            res.add((List<String>) square.clone());
            return;
        }

        StringBuilder prefix = new StringBuilder();
        // word square is symmetrical across its diagonal
        for (String s : square) {
            prefix.append(s.charAt(step));
        }

        for (String w : trie.listWordsByPrefix(prefix.toString())) {
            square.add(w);
            dfs(step + 1, square, res, trie);
            // backtracking
            square.remove(square.size() - 1);
        }
        // return
    }

    private static class Trie {

        Node root;

        Trie(String[] words) {
            root = new Node();

            for (String w : words) {
                Node cur = root;
                for (char ch : w.toCharArray()) {
                    if (cur.children[ch - 'a'] == null) {
                        cur.children[ch - 'a'] = new Node();
                    }
                    cur = cur.children[ch - 'a'];
                    cur.words.add(w);
                }
            }
        }

        List<String> listWordsByPrefix(String prefix) {
            Node cur = root;
            for (char ch : prefix.toCharArray()) {
                if (cur.children[ch - 'a'] == null) {
                    return new ArrayList<>();
                }
                cur = cur.children[ch - 'a'];
            }
            return cur.words;
        }

        class Node {
            Node[] children;
            List<String> words;
            Node() {
                children = new Node[26];
                words = new ArrayList<>();
            }
        }
    }

}
