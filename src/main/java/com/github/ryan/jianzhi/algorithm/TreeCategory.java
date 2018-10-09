package com.github.ryan.jianzhi.algorithm;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className TreeCategory
 * @date October 08,2018
 */
public class TreeCategory {

    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 输入一棵二叉树，求该树的深度。
     * 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
     */
    public int TreeDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(TreeDepth(root.left), TreeDepth(root.right)) + 1;
    }

    /**
     * 操作给定的二叉树，将其变换为源二叉树的镜像。
     输入描述:
     二叉树的镜像定义：源二叉树
        8
        /  \
        6   10
        / \  / \
        5 7 9 11
     镜像二叉树
        8
        /  \
       10  6
       / \  / \
      11 9 7  5
     */
    public void Mirror(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        Mirror(root.left);
        Mirror(root.right);
    }



}
