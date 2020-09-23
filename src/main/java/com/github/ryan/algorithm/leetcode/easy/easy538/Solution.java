package com.github.ryan.algorithm.leetcode.easy.easy538;

import com.github.ryan.algorithm.personal.component.TreeNode;
import com.github.ryan.algorithm.personal.util.TreeUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date March 02,2018
 */
@Slf4j
public class Solution {


    /**
     * 思路：
     * BST中序遍历结果为升序数组
     *
     * We can do a reverse inorder traversal to traverse the nodes of the tree
     * in descending order. In the process, we keep track of the running sum of
     * all nodes which we have traversed thus far.
     *
     * @param root
     * @return
     */
    private static int sum;
    public static TreeNode convertBST(TreeNode root) {
        convert(root);
        return root;
    }

    private static void convert(TreeNode cur) {
        if (cur == null) { return; }
        convert(cur.right);
        cur.val += sum;
        sum = cur.val; // keep track
        convert(cur.left);
    }

    public static void main(String[] args) {

        int[] nodes = {6, 4, 7, 2, 0, 0, 8};
        TreeNode root = TreeUtil.createTreeFromArray(nodes, 0);
        root = convertBST(root);
        log.info("convert result = {}", TreeUtil.levelOrderTree(root));
    }
}
