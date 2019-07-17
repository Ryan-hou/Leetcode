package com.github.ryan.leetcode.algorithm.medium.medium116;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 17,2019
 */
public class Solution {

    // 法1： 层序遍历
    public Node connect(Node root) {
        if (root == null) return null;

        Queue<Node> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                Node node = q.poll();
                // be careful of this if
                if (i != sz - 1) {
                    node.next = q.peek();
                }
                if (node.left != null) {
                    q.offer(node.left);
                    q.offer(node.right);
                }
            }
        }
        return root;
    }

    // 法2：preorder
    public Node connect2(Node root) {
        if (root == null || root.left == null) return root;
        root.left.next = root.right;
        if (root.next != null) {
            root.right.next = root.next.left;
        }
        connect2(root.left);
        connect2(root.right);
        return root;
    }

    private class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val,Node _left,Node _right,Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
