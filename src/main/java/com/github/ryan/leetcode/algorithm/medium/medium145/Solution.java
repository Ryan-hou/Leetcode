package com.github.ryan.leetcode.algorithm.medium.medium145;

import com.github.ryan.personal.algorithm.component.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution {

    // recursive solution
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorder(res, root);
        return res;
    }

    private void postorder(List<Integer> res, TreeNode root) {
        if (root == null) return;
        postorder(res, root.left);
        postorder(res, root.right);
        res.add(root.val);
    }

    // Iterative solution
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            // right -> node -> left
            while (root != null) {
                if (root.right != null) {
                    stack.push(root.right);
                }
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            if (!stack.isEmpty() && root.right == stack.peek()) {
                // swap node and it's right child
                stack.pop();
                stack.push(root);
                root = root.right;
            } else {
                // leftmost leaf
                res.add(root.val);
                root = null;
            }
        }

        return res;
    }
}
