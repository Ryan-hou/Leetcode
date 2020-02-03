package com.github.ryan.leetcode.algorithm.medium.medium222;

import com.github.ryan.personal.algorithm.component.TreeNode;

public class Solution {

    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        int leftDepth = getDepth(root, true);
        int rightDepth = getDepth(root, false);
        if (leftDepth == rightDepth) {
            // quick return
            return (int) Math.pow(2.0, leftDepth) - 1;
        }

        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private int getDepth(TreeNode root, boolean goLeft) {
        if (root == null) return 0;

        return goLeft ? 1 + getDepth(root.left, true)
                : 1 + getDepth(root.right, false);
    }
}
