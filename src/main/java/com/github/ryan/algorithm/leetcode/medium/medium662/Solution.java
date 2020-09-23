package com.github.ryan.algorithm.leetcode.medium.medium662;

import com.github.ryan.algorithm.personal.component.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    // BFS
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));
        int begin = 0, end = 0;
        int res = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                Pair cur = q.poll();
                if (i == 0) {
                    begin = cur.position;
                }
                if (i == sz - 1) {
                    end = cur.position;
                    res = Math.max(res, end - begin + 1);
                }
                if (cur.node.left != null) {
                    q.offer(new Pair(cur.node.left, cur.position * 2));
                }
                if (cur.node.right != null) {
                    q.offer(new Pair(cur.node.right, cur.position * 2 + 1));
                }
            } // end for
        } // end while
        return res;
    }

    private static class Pair {
        TreeNode node;
        int position; // from left to right
        Pair(TreeNode node, int position) {
            this.node = node;
            this.position = position;
        }
    }
}
