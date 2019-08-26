package com.github.ryan.leetcode.algorithm.easy.easy606;

import com.github.ryan.personal.algorithm.component.TreeNode;

public class Solution {

    public String tree2str(TreeNode t) {
        if (t == null) return "";
        if (t.left == null && t.right == null) return t.val + "";
        // t.left != null || t.right != null
        if (t.right == null) {
            // t.left != null
            // in preorder and t.left is not null
            // t.right don't influence tree's structure
            return t.val + "(" + tree2str(t.left) + ")";
        }
        // t.right != null
        return t.val + "(" + tree2str(t.left) + ")"
                + "(" + tree2str(t.right) + ")";
    }
}
