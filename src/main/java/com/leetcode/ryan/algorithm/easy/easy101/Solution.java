package com.leetcode.ryan.algorithm.easy.easy101;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Ryan-hou
 * @description:
 * @className: Solution
 * @date February 28,2017
 */
public class Solution {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 法一:
     * 根据对称二叉树的性质,使用二叉树的遍历来解决
     * 1) 中序遍历的结果为回文序列
     * 2) 先序遍历和后续遍历的结果互为逆序列
     * 代码略
     */


    /**
     * 法二:
     * 使用递归来描述对称树的性质(声明式语言的特征,代码简洁)
     */
    public static boolean isSymmetricTwo(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }
    private static boolean isMirror(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        return (p.val == q.val) && isMirror(p.left, q.right) && isMirror(p.right, q.left);
    }


    /**
     * 法三:
     * 使用队列和迭代,类似 BFS 的思路
     */
    public static boolean isSymmetricThree(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        if (root == null) return true;
        q.add(root.left);
        q.add(root.right);
        while(q.size() > 1) {
            TreeNode left = q.poll(),
                    right = q.poll();
            if (left == null && right == null) continue;
            if (left == null || right == null) return false;
            if (left.val != right.val) return false;
            q.add(left.left);
            q.add(right.right);
            q.add(left.right);
            q.add(right.left);
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode oneLeft = new TreeNode(2);
        TreeNode oneRight = new TreeNode(2);
        TreeNode twoLeft = new TreeNode(3);
        TreeNode twoRight = new TreeNode(3);

        root.left = oneLeft;
        root.right = oneRight;
        oneLeft.left = twoLeft;
        oneRight.right = twoRight;

        System.out.println("The tree is symmetric? " + isSymmetricThree(root));
    }
}
