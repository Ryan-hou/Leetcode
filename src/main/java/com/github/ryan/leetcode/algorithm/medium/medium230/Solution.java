package com.github.ryan.leetcode.algorithm.medium.medium230;

import com.github.ryan.personal.algorithm.component.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 12,2019
 */
public class Solution {

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> data = new ArrayList();
        inorder(root, data);
        return data.get(k - 1);
    }

    private void inorder(TreeNode root, List<Integer> data) {
        if (root == null) return;
        inorder(root.left, data);
        data.add(root.val);
        inorder(root.right, data);
    }
}
