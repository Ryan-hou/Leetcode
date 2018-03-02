package com.leetcode.ryan.algorithm.easy.easy543;

import com.leetcode.ryan.personal.component.TreeNode;
import com.leetcode.ryan.personal.util.TreeUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date March 01,2018
 */
@Slf4j
public class Solution {

    private static int max = 0;


    /**
     * For every node, length of longest path which pass it = MaxDepth of its
     * left subtree + MaxDepth of its right subtree
     * @param root
     * @return
     */
    public static int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return max;
    }

    private static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        // 更新max值
        max = Math.max(max, left + right);

        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        int[] nodes = {1, 2, 3, 4, 5, 0, 6};
        TreeNode root = TreeUtil.createTreeFromArray(nodes, 0);
        log.info("Diameter of Binary Tree = {}", diameterOfBinaryTree(root));
    }
}
