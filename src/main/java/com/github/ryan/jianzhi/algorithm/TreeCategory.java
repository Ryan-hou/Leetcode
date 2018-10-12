package com.github.ryan.jianzhi.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className TreeCategory
 * @date October 08,2018
 */
public class TreeCategory {

    private static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 输入一棵二叉树，求该树的深度。
     * 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
     */
    public int treeDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(treeDepth(root.left), treeDepth(root.right)) + 1;
    }

    /**
     * 操作给定的二叉树，将其变换为源二叉树的镜像。
     * 输入描述:
     * 二叉树的镜像定义：源二叉树
     *    8
     *   /  \
     *  6   10
     *  / \  / \
     * 5 7 9 11
     * 镜像二叉树
     *    8
     *   /  \
     *  10  6
     *  / \  / \
     * 11 97  5
     */
    public void mirror(TreeNode root) {
        if (root == null) return;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        mirror(root.left);
        mirror(root.right);
    }

    /**
     * 请实现一个函数，用来判断一颗二叉树是不是对称的。
     * 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
     */
    public boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null)  return true;
        return isSymmetrical(pRoot.left, pRoot.right);
    }

    private boolean isSymmetrical(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;

        // t1!=null && t2!=null
        return t1.val == t2.val
                && isSymmetrical(t1.left, t2.right)
                && isSymmetrical(t1.right, t2.left);
    }

    /**
     * 输入一棵二叉树，判断该二叉树是否是平衡二叉树
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;

        // 使用递归，top-down方式，计算treeDepth的过程存在重复
        // 可以使用 bottom-up 的思路优化：
        // 从下往上遍历，如果子树是平衡二叉树，则返回子树的高度；
        // 如果发现子树不是平衡二叉树，则直接停止遍历，这样至多只对每个结点访问一次
        return Math.abs(treeDepth(root.left) - treeDepth(root.right)) <= 1
                && isBalanced(root.left)
                && isBalanced(root.right);
    }

    /**
     * 给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （
     * 5，3，7，2，4，6，8）中，按结点数值大小顺序第三小结点的值为4
     */
    // BST中序遍历为有序的
    public TreeNode KthNode(TreeNode pRoot, int k) {
        List<TreeNode> res = new ArrayList<>();
        inOrder(pRoot, res);
        if (k == 0 || res.size() < k) return null;

        return res.get(k - 1);
    }

    private void inOrder(TreeNode root, List<TreeNode> res) {
        if (root == null) return;
        inOrder(root.left, res);
        res.add(root);
        inOrder(root.right, res);
    }

    private int index = 0;
    public TreeNode KthNode2(TreeNode pRoot, int k) {
        if (pRoot != null) {
            TreeNode lnode = KthNode2(pRoot.left, k);
            if (lnode != null) return lnode;
            if (++index == k) return pRoot;
            TreeNode rnode = KthNode2(pRoot.right, k);
            if (rnode != null) return rnode;
        }
        return null;
    }


}
