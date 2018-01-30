package com.leetcode.ryan.algorithm.easy.easy104;

import com.leetcode.ryan.personal.component.TreeNode;
import com.leetcode.ryan.personal.util.TreeUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date January 30,2018
 */
@Slf4j
public class Solution {

    /**
     * 使用递归：
     * 该递归函数的语义为：计算以root为根节点的二叉树的最大深度
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        // 递归出口
        if (root == null) {
            return 0;
        }

        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
        // return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1
    }

    public static void main(String[] args) {
        // [3,9,20,null,null,15,7]
        int[] nodes = {3, 9, 20, 0, 0, 15, 7};
        TreeNode root = TreeUtil.createTreeFromArray(nodes, 0);
        log.info("max depth = {}", maxDepth(root));
    }
}
