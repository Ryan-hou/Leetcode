package com.github.ryan.leetcode.algorithm.medium.medium951;

import com.github.ryan.personal.algorithm.component.TreeNode;

public class Solution {

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) return root1 == root2;
        if (root1.val != root2.val) return false;

        return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)
                || (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
    }
}
