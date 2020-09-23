package com.github.ryan.algorithm.leetcode.hard.hard315;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 16,2019
 */
public class Solution {

    private TreeNode root;

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        // count of this val if it duplicates
        int count;
        // count of all numbers rooted from current
        // node and are smaller than the val
        int small = 0;

        TreeNode(int val) {
            this.val = val;
            count = 1;
        }
    }

    // use BST
    public List<Integer> countSmaller(int[] nums) {
        root = null;
        LinkedList<Integer> res = new LinkedList<>();
        if (nums == null || nums.length < 1) {
            return res;
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            res.addFirst(add(nums[i]));
        }
        return res;
    }

    // return the number of smaller elements than val
    private int add(int val) {
        if (root == null) {
            root = new TreeNode(val);
            return 0;
        }
        return add(root, val);
    }

    private int add(TreeNode node, int val) {
        if (node == null) {
            return 0;
        }

        if (node.val == val) {
            node.count++;
            return node.small;
        } else if (val < node.val) {
            node.small += 1;
            if (node.left == null) {
                TreeNode left = new TreeNode(val);
                node.left = left;
                return 0;
            } else {
                return add(node.left, val);
            }
        } else {
            // val > node.val
            if (node.right == null) {
                TreeNode right = new TreeNode(val);
                node.right = right;
                return node.count + node.small;
            } else {
                return node.count + node.small + add(node.right, val);
            }
        }
    }
}
