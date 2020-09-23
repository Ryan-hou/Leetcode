package com.github.ryan.algorithm.leetcode.easy.easy530;

import com.github.ryan.algorithm.personal.component.TreeNode;
import com.github.ryan.algorithm.personal.util.TreeUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date March 07,2018
 */
@Slf4j
public class Solution {

    private static int minDiff = Integer.MAX_VALUE;
    private static TreeNode prev = null;

    /**
     * 思路：
     * Since this is a BST, the inorder traversal of its nodes results in a sorted list of values.
     * Thus, the minimum absolute difference must occur in any adjacently traversed nodes.
     * @param root
     * @return
     */
    public static int getMinimumDifference(TreeNode root) {
        inorder(root);
        return minDiff;
    }

    private static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        if (prev != null) {
            minDiff = Math.min(minDiff, root.val - prev.val);
        }
        prev = root;
        inorder(root.right);
    }

    public static void main(String[] args) {
        int[] nodes = {1, 0, 3, 0, 0, 2, 0};
        TreeNode root = TreeUtil.createTreeFromArray(nodes, 0);
        log.info("Min diff = {}", getMinimumDifference(root));
    }

}
