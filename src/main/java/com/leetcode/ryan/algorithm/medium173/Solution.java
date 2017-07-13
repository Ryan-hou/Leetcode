package com.leetcode.ryan.algorithm.medium173;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author yanlin.hou@ucarinc.com
 * @description:
 * @className: Solution
 * @date July 12,2017
 */
public class Solution {

    // Definition for binary tree
    private static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        // BST 中序遍历结果为从小到大排序
        public static void inOrder(TreeNode root) {
            if (root.left != null) inOrder(root.left);
            System.out.print(" " + root.val);
            if (root.right != null) inOrder(root.right);
        }
    }

    private static class BSTIterator {

        Deque<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            stack = new ArrayDeque<>();
            pushAll(root);
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /** @return the next smallest number */
        public int next() {
            TreeNode node = stack.pop();
            if (node.right != null) {
                pushAll(node.right);
            }
            return node.val;
        }

        private void pushAll(TreeNode node) {
            for (; node != null; stack.push(node), node = node.left);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        TreeNode left1 = new TreeNode(4);
        TreeNode right1 = new TreeNode(7);
        root.left = left1;
        root.right = right1;
        TreeNode left11 = new TreeNode(2);
        TreeNode right12 = new TreeNode(5);
        left1.left = left11;
        left1.right = right12;
        TreeNode rigth11 = new TreeNode(9);
        right1.right = rigth11;

        TreeNode.inOrder(root);
        System.out.println();
        System.out.println("BSTIterator works: ------------");
        BSTIterator iterator = new BSTIterator(root);
        while (iterator.hasNext()) {
            System.out.print(" " + iterator.next());
        }
        System.out.println();
    }
}
