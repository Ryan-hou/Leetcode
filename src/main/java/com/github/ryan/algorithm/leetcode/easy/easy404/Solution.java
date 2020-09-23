package com.github.ryan.algorithm.leetcode.easy.easy404;

import com.github.ryan.algorithm.personal.component.TreeNode;

public class Solution {

    public int sumOfLeftLeaves(TreeNode root) {
        int sum = 0;
        if (root == null) return sum;
        if (root.left != null
                && root.left.left == null && root.left.right == null) {
            sum += root.left.val;
        }
        sum += (sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right));
        return sum;
    }

}
