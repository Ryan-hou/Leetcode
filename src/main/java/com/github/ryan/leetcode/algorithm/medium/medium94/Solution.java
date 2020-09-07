package com.github.ryan.leetcode.algorithm.medium.medium94;

import com.github.ryan.personal.algorithm.component.TreeNode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date July 17,2017
 */
@Slf4j
public class Solution {


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
    private static List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        doInorderTraversal(root, result);
        return result;
    }

    /**
     * Iterative solution
     * @param root
     * @return
     */
    private static List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            result.add(cur.val);
            cur = cur.right;
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode left1 = new TreeNode(4);
        TreeNode left12 = new TreeNode(6);
        TreeNode right1 = new TreeNode(5);
        TreeNode right12 = new TreeNode(7);
        root.left = left1;
        left1.right = left12;
        root.right = right1;
        right1.left = right12;
        List<Integer> integers = inorderTraversal2(root);
        log.info("result = {}", integers);
    }
}
