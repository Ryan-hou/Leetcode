package com.github.ryan.leetcode.algorithm.medium.medium677;

import java.util.HashMap;
import java.util.Map;

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
public class MapSum {

    private static class Node {

        int count;
        Map<Character, Node> next;

        Node(int count) {
            this.count = count;
            next = new HashMap<>();
        }

        Node() {
            this(0);
        }
    }

    private Node root;

    /** Initialize your data structure here. */
    public MapSum() {
        root = new Node();
    }

    public void insert(String key, int val) {
        assert key != null && val > 0;
        Node cur = this.root;
        for (char ch : key.toCharArray()) {
            if (!cur.next.containsKey(ch)) {
                cur.next.put(ch, new Node());
            }
            cur = cur.next.get(ch);
        }
        cur.count = val;
    }

    public int sum(String prefix) {
        assert prefix != null;
        Node cur = this.root;
        for (char ch : prefix.toCharArray()) {
            if (!cur.next.containsKey(ch)) {
                return 0;
            }
            cur = cur.next.get(ch);
        }

        return sum(cur);
    }

    private int sum(Node node) {
        int res = node.count;
        for (Map.Entry<Character, Node> entry : node.next.entrySet()) {
            res += sum(entry.getValue());
        }
        return res;
    }
}
