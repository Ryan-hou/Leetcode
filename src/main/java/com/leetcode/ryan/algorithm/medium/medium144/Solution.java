package com.leetcode.ryan.algorithm.medium.medium144;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date July 18,2017
 */
@Slf4j
public class Solution {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * Recursive solution
     * @param root
     * @return
     */
    private static List<Integer> preorderTraversalOne(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        doPreOrderTraversal(root, result);
        return result;
    }

    private static void doPreOrderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) return;
        result.add(root.val);
        doPreOrderTraversal(root.left, result);
        doPreOrderTraversal(root.right, result);
    }

    /**
     * Iterative solution
     * @param root
     * @return
     */
    private static List<Integer> preorderTraversalTwo(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                result.add(root.val);
                if (root.right != null) {
                    stack.push(root.right);
                }
                root = root.left;
            } else {
                root = stack.pop();
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
        List<Integer> integers = preorderTraversalTwo(root);
        log.info("result = {}", integers);
    }
}
