package com.github.ryan.leetcode.algorithm.easy.easy671;

import com.github.ryan.personal.algorithm.component.TreeNode;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    private Set<Integer> set = new HashSet<>();

    private void traversal(TreeNode root) {
        if (root == null) return;
        set.add(root.val);
        traversal(root.left);
        traversal(root.right);
    }

    public int findSecondMinimumValue(TreeNode root) {
        traversal(root);
        long res = Long.MAX_VALUE;
        int min = root.val;
        for (int i : set) {
            if (min < i && i < res) {
                res = i;
            }
        }
        return res == Long.MAX_VALUE ? -1 : (int) res;
    }

}
