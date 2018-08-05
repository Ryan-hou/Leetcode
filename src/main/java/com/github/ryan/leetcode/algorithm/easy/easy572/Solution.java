package com.github.ryan.leetcode.algorithm.easy.easy572;

import com.github.ryan.personal.algorithm.component.TreeNode;
import com.github.ryan.personal.algorithm.util.TreeUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date March 02,2018
 */
@Slf4j
public class Solution {


    /**
     * 思路一：
     * 二叉数的先序遍历
     *
     * For each node during pre-order traversal of s, use a recursive
     * function isSame to validate if sub-tree started with this node
     * is the same with t;
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isSubtree(TreeNode s, TreeNode t) {
        // quick finish
        if (s == null) { return false; }
        if (isSame(s, t)) { return true; }

        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    // 思路2: 使用迭代先序遍历二叉树
    // Use iterative way for isSubtree（pre-order use iterative）
    private static boolean isSubtree2(TreeNode s, TreeNode t) {
        assert s != null && t != null;

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(s);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (isSame(node, t)) {
                return true;
            }

            if (node.left != null) { q.offer(node.left); }
            if (node.right != null) { q.offer(node.right); }
        }
        return false;
    }

    private static boolean isSame(TreeNode s, TreeNode t) {
//        if (s == null && t == null) return true;
//        if (s == null || t == null) return false;
        if (s == null || t == null) return s == t;
        if (s.val != t.val) return false;
        return isSame(s.left, t.left) && isSame(s.right, t.right);
    }

    public static void main(String[] args) {
        TreeNode s = TreeUtil.createTreeFromArray(new int[]{3, 4, 5, 1, 2, 0, 0}, 0);
        TreeNode t = TreeUtil.createTreeFromArray(new int[]{4, 1, 2}, 0);
        log.info("t is subtree of s? {}", isSubtree2(s, t));
    }
}
