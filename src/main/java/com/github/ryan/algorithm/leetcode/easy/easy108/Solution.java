package com.github.ryan.algorithm.leetcode.easy.easy108;

import com.github.ryan.algorithm.personal.component.TreeNode;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 28,2019
 */
public class Solution {

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length < 1) return null;
        return array2BST(nums, 0, nums.length - 1);
    }

    public TreeNode array2BST(int[] nums, int l, int r) {
        if (l > r) return null;
        int mid = l + (r - l) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = array2BST(nums, l, mid - 1);
        root.right = array2BST(nums, mid + 1, r);
        return root;
    }

}
