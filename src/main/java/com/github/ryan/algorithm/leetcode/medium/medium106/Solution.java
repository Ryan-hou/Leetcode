package com.github.ryan.algorithm.leetcode.medium.medium106;

import com.github.ryan.algorithm.personal.component.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 28,2019
 */
public class Solution {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length < 1) return null;

        // key -> inorder value, value -> inorder value's index
        Map<Integer, Integer> inmap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inmap.put(inorder[i], i);
        }
        return build(postorder.length - 1, 0, inorder.length - 1, inorder, postorder, inmap);
    }

    private TreeNode build(int end, int l, int r, int[] inorder, int[] postorder, Map<Integer, Integer> inmap) {
        if (l > r || end < 0) return null;

        TreeNode root = new TreeNode(postorder[end]);
        int idx = inmap.get(postorder[end]);
        root.left = build(end - (r - idx + 1), l, idx - 1, inorder, postorder, inmap);
        root.right = build(end - 1, idx + 1, r, inorder, postorder, inmap);
        return root;
    }
}
