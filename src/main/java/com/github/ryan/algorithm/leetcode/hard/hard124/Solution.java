package com.github.ryan.algorithm.leetcode.hard.hard124;

import com.github.ryan.algorithm.personal.component.TreeNode;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 23,2019
 */
public class Solution {

    private int sum;

    public int maxPathSum(TreeNode root) {
        sum = root.val;
        helper(root);
        return sum;
    }

    // 返回以root节点为根的二叉树的最大pathSum(根节点和左右子树不能同时存在)
    // 如果为负数则返回0
    private int helper(TreeNode root) {
        if (root == null) return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        int[] arr = { root.val, root.val + left, root.val + right, root.val + left + right, sum };
        sum = max(arr);
        return Math.max(0, Math.max(root.val + left, root.val + right));
    }

    private int max(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
}
