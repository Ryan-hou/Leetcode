package com.github.ryan.leetcode.algorithm.medium.medium1110;

import com.github.ryan.personal.algorithm.component.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> res = new ArrayList<>();
        if (root == null) return res;
        if (to_delete == null || to_delete.length < 1) {
            res.add(root);
            return res;
        }

        Set<Integer> set = new HashSet<>();
        for (int d : to_delete) {
            set.add(d);
        }
        preOrder(set, res, false, root);
        return res;
    }

    // return need to delete root node or not
    private boolean preOrder(Set<Integer> set, List<TreeNode> res, boolean hasParent, TreeNode root) {
        if (root == null) return false;

        boolean del = set.contains(root.val);
        if (!del && !hasParent) {
            res.add(root);
        }

        if (preOrder(set, res, !del, root.left)) root.left = null;
        if (preOrder(set, res, !del, root.right)) root.right = null;
        return del;
    }

}
