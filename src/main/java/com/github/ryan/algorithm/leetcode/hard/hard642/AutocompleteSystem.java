package com.github.ryan.algorithm.leetcode.hard.hard642;

import java.util.*;

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
public class AutocompleteSystem {

    private static class TrieNode {

        Map<Character, TrieNode> children;
        Map<String, Integer> freq;
        Set<String> dict;
        Queue<String> minHeap;
        boolean isEnd;

        TrieNode() {
            children = new HashMap<>();
            freq = new HashMap<>();
            dict = new HashSet<>();
            isEnd = false;
            minHeap = new PriorityQueue<>(new Comparator<String>() {
                @Override
                public int compare(String str1, String str2) {
                    if (freq.get(str1).equals(freq.get(str2))) {
                        // be careful about the order
                        return str2.compareTo(str1);
                    } else {
                        return freq.get(str1) - freq.get(str2);
                    }
                }
            });
        }
    } // end class TrieNode

    private TrieNode root;
    private TrieNode cur;
    private StringBuilder str;

    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        cur = root;
        str = new StringBuilder();
        for (int i = 0; i < sentences.length; i++) {
            this.addFreq(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        List<String> res = new LinkedList<>();
        if (c == '#') {
            // reset
            addFreq(str.toString(), 1);
            cur = root;
            str = new StringBuilder();
            return res;
        } else {
            str.append(c);
            TrieNode next = cur.children.get(c);
            if (next == null) {
                next = new TrieNode();
                cur.children.put(c, next);
                cur = next;
                return res;
            } else {
                cur = next;
                while (!cur.minHeap.isEmpty()) {
                    res.add(0, cur.minHeap.poll());
                }
                for (String str : res) {
                    cur.minHeap.offer(str);
                }
                return res;
            }
        }
    }

    private void addFreq(String s, int time) {
        TrieNode cur = root;
        for (char ch : s.toCharArray()) {
            TrieNode next = cur.children.get(ch);
            if (next == null) {
                next = new TrieNode();
                cur.children.put(ch, next);
            }
            // update cur
            cur = next;
            // update freq
            cur.freq.put(s, cur.freq.getOrDefault(s, 0) + time);
            if (cur.dict.contains(s)) {
                // corner case: update minHeap for new freq value
                List<String> tmpList = new ArrayList<>();
                while (!cur.minHeap.isEmpty()) {
                    tmpList.add(cur.minHeap.poll());
                }
                for (String str : tmpList) {
                    cur.minHeap.offer(str);
                }
            } else {
                if (cur.minHeap.size() < 3) {
                    cur.minHeap.offer(s);
                    cur.dict.add(s);
                } else {
                    if (cur.freq.get(s) > cur.freq.get(cur.minHeap.peek())
                            || (cur.freq.get(s).equals(cur.freq.get(cur.minHeap.peek()))
                            && s.compareTo(cur.minHeap.peek()) < 0)) {
                        String min = cur.minHeap.poll();
                        cur.dict.remove(min);
                        cur.dict.add(s);
                        cur.minHeap.offer(s);
                    }
                }
            }
        } // end for
        cur.isEnd = true;
    }
}
