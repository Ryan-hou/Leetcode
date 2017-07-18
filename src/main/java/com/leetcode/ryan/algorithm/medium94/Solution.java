package com.leetcode.ryan.algorithm.medium94;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author yanlin.hou@ucarinc.com
 * @description:
 * @className: Solution
 * @date July 17,2017
 */
@Slf4j
public class Solution {

    // Definition for a binary tree node
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    private static void doInorderTraversal(TreeNode root, List<Integer> res) {
        if (root == null) return;
        doInorderTraversal(root.left, res);
        res.add(root.val);
        doInorderTraversal(root.right, res);
    }

    /**
     * Recursive solution
     * @param root
     * @return
     */
    private static List<Integer> inorderTraversalOne(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        doInorderTraversal(root, result);
        return result;
    }

    /**
     * Iterative solution
     * @param root
     * @return
     */
    private static List<Integer> inorderTraversalTwo(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> traversalStack = new ArrayDeque<>();
        while (root != null || !traversalStack.isEmpty()) {
            if (root != null) {
                traversalStack.push(root);
                root = root.left;
            } else {
                root = traversalStack.pop();
                result.add(root.val);
                root = root.right;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode left1 = new TreeNode(4);
        TreeNode left12 = new TreeNode(6);
        TreeNode right1 = new TreeNode(5);
        TreeNode rigth12 = new TreeNode(7);
        root.left = left1;
        left1.right = left12;
        root.right = right1;
        right1.left = rigth12;
        List<Integer> integers = inorderTraversalTwo(root);
        log.info("result = {}", integers);
    }
}
