package com.leetcode.ryan.algorithm.hard145;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * @author yanlin.hou@ucarinc.com
 * @description:
 * @className: Solution
 * @date July 20,2017
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

    /**
     * Recursive solution
     * @param root
     * @return
     */
    private static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        doPostorderTraversal(result, root);
        return result;
    }

    private static void doPostorderTraversal(List<Integer> result, TreeNode root) {
        if (root == null) return;  // 递归出口

        doPostorderTraversal(result, root.left);
        doPostorderTraversal(result, root.right);
        result.add(root.val);
    }

    /**
     * Iterative solution
     * preorder: root - left - right to root - right -left to reverse: postorder
     * @param root
     * @return
     */
    private static List<Integer> postorderTraversalTwo(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                result.add(root.val);
                if (root.left != null) stack.push(root.left);
                root = root.right;
            } else {
                root = stack.pop();
            }
        }
        Collections.reverse(result);
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

        List<Integer> integers = postorderTraversal(root);
        log.info("result = {}", integers);
    }
}
