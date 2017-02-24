package com.leetcode.ryan.algorithm.easy226;

/**
 * @author Ryan-hou
 * @description:
 * @className: Solution
 * @date February 24,2017
 */
public class Solution {

    // Definition for a binary tree node.
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

    }

    /**
     * 思路:
     * 比较简单,实际上是二叉树的后续遍历
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        TreeNode temp;
        if (root != null) {
            invertTree(root.left);
            invertTree(root.right);
            temp = root.right;
            root.right = root.left;
            root.left = temp;
        }
        return root;
    }

}
