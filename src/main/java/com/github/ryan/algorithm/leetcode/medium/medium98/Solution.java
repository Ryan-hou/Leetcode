package com.github.ryan.algorithm.leetcode.medium.medium98;

import com.github.ryan.algorithm.personal.component.TreeNode;
import com.github.ryan.algorithm.personal.util.TreeUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date February 24,2018
 */
@Slf4j
public class Solution {

    // 思路一： BST中序遍历后为由小到大的顺序

    /**
     * 思路二：
     *
     * Recursively iterating over the tree while defining interval [minVal, maxVal]
     * for each node which value must fit in.
     * @param root
     * @return
     */
    public static boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        if (root.val >= maxVal || root.val <= minVal) {
            return false;
        }
        return isValidBST(root.left, minVal, root.val)
                && isValidBST(root.right, root.val, maxVal);
    }

    public static void main(String[] args) {
        //int[] nodes = {2, 1, 3, 0, 0, 5, 4};
        int[] nodes = {10, 5, 15, 0, 0, 6, 20};
        TreeNode tree = TreeUtil.createTreeFromArray(nodes, 0);

        //log.info("Tree = {}", TreeUtil.levelOrderTree(tree));
        log.info("Tree is BST? {}", isValidBST(tree));
    }

}
