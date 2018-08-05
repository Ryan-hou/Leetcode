package com.github.ryan.leetcode.algorithm.easy.easy112;

import com.github.ryan.personal.algorithm.component.TreeNode;
import com.github.ryan.personal.algorithm.util.TreeUtil;
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
     * 使用递归解法：
     * 递归函数的语义：在root为根节点的路径中，是否存在从根节点到叶子节点的路径，路径上的节点的和为sum
     * @param root
     * @param sum
     * @return
     */
    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        // 判断是否为叶子节点
        if (root.left == null && root.right == null) {
            return root.val == sum;
        }

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    public static void main(String[] args) {
        int[] nodes = {3, 2, 4, 1, 0, 6, 0};
        TreeNode root = TreeUtil.createTreeFromArray(nodes, 0);
        log.info("result = {}", hasPathSum(root, 13));
    }
}
