package com.github.ryan.leetcode.algorithm.medium.medium450;

import com.github.ryan.personal.algorithm.component.TreeNode;

public class Solution {

    // One step right and then always left
    public int successor(TreeNode root) {
        assert root != null;
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        return root.val;
    }

    // One step left and then always right
    public int predecessor(TreeNode root) {
        assert root != null;
        root = root.left;
        while (root.right != null) {
            root = root.right;
        }
        return root.val;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else {
            // key == root.val
            // delete the current node

            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.right != null) {
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            } else {
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }

        }

        return root;
    }

}
