package com.github.ryan.leetcode.algorithm.easy.easy226;

import com.github.ryan.personal.algorithm.component.TreeNode;
import com.github.ryan.personal.algorithm.util.TreeUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author Ryan-hou
 * @description:
 * @className: Solution
 * @date February 24,2017
 */
@Slf4j
public class Solution {


    /**
     * 思路:
     * 使用递归的方式，递归函数的语义为：反转以root为根节点的二叉树
     * @param root
     * @return
     */
    public static TreeNode invertTree(TreeNode root) {

        // 递归出口
        if (root == null) return null;

        invertTree(root.left);
        invertTree(root.right);

        TreeNode temp;
        temp = root.left;
        root.left = root.right;
        root.right = temp;

        return root;
    }

    public static void main(String[] args) {
        int[] nodes = {4, 2, 7, 1, 3, 6, 9};
        TreeNode root = TreeUtil.createTreeFromArray(nodes, 0);
        root = invertTree(root);
        List<Integer> result = TreeUtil.levelOrderTree(root);
        log.info("result = {}", result);
    }

}
