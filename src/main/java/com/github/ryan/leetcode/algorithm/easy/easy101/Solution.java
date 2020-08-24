package com.github.ryan.leetcode.algorithm.easy.easy101;

import com.github.ryan.personal.algorithm.component.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Ryan-hou
 * @description:
 * @className: Solution
 * @date February 28,2017
 */
public class Solution {

    /**
     * 法一:
     * 使用递归来描述对称树的性质
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }
    private static boolean isMirror(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        return (p.val == q.val)
                && isMirror(p.left, q.right)
                && isMirror(p.right, q.left);
    }

    /**
     * 法二:
     * 使用队列模拟对称节点入队
     */
    public static boolean isSymmetric2(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> q = new LinkedList<>();
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

    /**
     * 法三:
     * 层序遍历，每一层都是回文序列
     * 注意：层序遍历时，左右孩子为空的情况，也需要入队进行回文判断
     */
    public boolean isSymmetric3(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int sz = q.size();
            List<TreeNode> levelRes = new ArrayList<>(sz);
            for (int i = 0; i < sz; i++) {
                TreeNode cur = q.poll();
                levelRes.add(cur);
                if (cur != null) {
                    q.offer(cur.left);
                    q.offer(cur.right);
                }
            }
            if (!isPalindrome(levelRes)) {
                return false;
            }
        }
        return true;
    }

    private boolean isPalindrome(List<TreeNode> list) {
        int start = 0, end = list.size() - 1;
        while (start < end) {
            TreeNode pre = list.get(start);
            TreeNode post = list.get(end);
            start++;
            end--;
            if (pre == null && post == null) continue;
            if (pre == null || post == null) return false;
            if (pre.val != post.val) return false;
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

        System.out.println("The tree is symmetric? " + isSymmetric2(root));
    }
}
