package com.github.ryan.leetcode.algorithm.hard.hard99;

import com.github.ryan.personal.algorithm.component.TreeNode;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 01,2019
 */
public class Solution {

    private TreeNode pre;
    private TreeNode swap;

    public void recoverTree(TreeNode root) {
        if (root == null) return;
        pre = null;
        swap = new TreeNode(0);
        inorder(root);
        int temp = swap.left.val;
        swap.left.val = swap.right.val;
        swap.right.val = temp;
    }

    // BST -> inorder
    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        if (pre == null) {
            pre = root;
        } else if (pre.val >= root.val) {
            if (swap.left == null) {
                swap.left = pre;
            }
            swap.right = root;
        }
        pre = root;
        inorder(root.right);
    }
}
