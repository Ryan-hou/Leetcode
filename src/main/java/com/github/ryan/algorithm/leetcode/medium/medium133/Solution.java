package com.github.ryan.algorithm.leetcode.medium.medium133;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 31,2019
 */
public class Solution {

    // Definition for a Node.
    private static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val,List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        // key -> original node, value -> cloned node
        Map<Node, Node> dict = new HashMap<>();
        return bfs(node, dict);
    }

    private Node bfs(Node node, Map<Node, Node> dict) {
        if (node == null) return null;

        if (dict.containsKey(node)) {
            return dict.get(node);
        }

        Node copy = new Node();
        copy.val = node.val;
        dict.put(node, copy);
        if (node.neighbors == null) {
            return copy;
        }

        List<Node> list = new ArrayList<>();
        for (Node n : node.neighbors) {
            list.add(bfs(n, dict));
        }
        copy.neighbors = list;
        return copy;
    }
}
