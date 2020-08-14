package com.github.ryan.leetcode.algorithm.easy.easy687;

import com.github.ryan.personal.algorithm.component.TreeNode;

public class Solution {

    private int res;

    public int longestUnivaluePath(TreeNode root) {
        res = 0;
        longest(root);
        return res;
    }

    // 返回以 node 节点为根的往左或者往右的最长路径长
    private int longest(TreeNode node) {
        if (node == null) return 0;

        int left = longest(node.left);
        int right = longest(node.right);
        boolean sameLeft = false, sameRight = false;
        if (node.left != null && node.left.val == node.val) {
            sameLeft = true;
            left += 1;
        }
        if (node.right != null && node.right.val == node.val) {
            sameRight = true;
            right += 1;
        }

        if (sameLeft && sameRight) {
            res = Math.max(res, left + right);
            return Math.max(left, right);
        } else if (sameLeft) {
            res = Math.max(res, left);
            return left;
        } else if (sameRight) {
            res = Math.max(res, right);
            return right;
        } else {
            return 0;
        }
    }

}
