package com.github.ryan.leetcode.algorithm.easy.easy617;

import com.github.ryan.personal.algorithm.component.TreeNode;
import com.github.ryan.personal.algorithm.util.TreeUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date February 25,2018
 */
@Slf4j
public class Solution {

    public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

        if (t1 == null && t2 == null) {
            return null;
        }

        if (t1 != null && t2 != null) {
            t1.val += t2.val;

            TreeNode lRoot = mergeTrees(t1.left, t2.left);
            t1.left = lRoot;
            TreeNode rRoot = mergeTrees(t1.right, t2.right);
            t1.right = rRoot;
            return t1;
        }

        if (t1 == null) {
            t1 = t2;
        }
        return t1;
    }

    public static void main(String[] args) {
        int[] nodes1 = {1, 3, 2, 5, 0, 0, 0};
        TreeNode treeOne = TreeUtil.createTreeFromArray(nodes1, 0);
        int[] nodes2 = {2, 1, 3, 0, 4, 0, 7};
        TreeNode treeTwo = TreeUtil.createTreeFromArray(nodes2, 0);

        TreeNode trees = mergeTrees(treeOne, treeTwo);
        log.info("Merge result = {}", TreeUtil.levelOrderTree(trees));
    }
}
