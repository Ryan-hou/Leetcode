package com.github.ryan.algorithm.leetcode.easy.easy235;

import com.github.ryan.algorithm.personal.component.TreeNode;
import com.github.ryan.algorithm.personal.util.TreeUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date January 31,2018
 */
@Slf4j
public class Solution {

    /**
     * 使用递归解法：
     * 递归函数语义：在以root为根节点的树中，查找p和q的最近公共祖先
     *
     * 递归出口：当p和q不同时存在于root的左子树或者右子树中时，root节点为最近公共祖先
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        assert (p != null && q != null);

        if (root == null) {
            return null;
        }

        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        // p,q存在于以root为根节点的树上，否则需要判断p,q是否真实存在于该树上
        return root;
    }

    public static void main(String[] args) {
        int[] nodes = {6, 2, 8, 0, 4, 7, 9, 0, 0, 3, 5, 0, 0, 0, 0};
        TreeNode root = TreeUtil.createTreeFromArray(nodes, 0);
        TreeNode claNode = lowestCommonAncestor(root, new TreeNode(3), new TreeNode(5));
        log.info("CLA node = {}", claNode.val);
    }
}
