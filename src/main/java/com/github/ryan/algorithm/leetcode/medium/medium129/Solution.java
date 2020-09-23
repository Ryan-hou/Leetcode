package com.github.ryan.algorithm.leetcode.medium.medium129;

import com.github.ryan.algorithm.personal.component.TreeNode;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 25,2019
 */
public class Solution {

    public int sumNumbers(TreeNode root) {
        return dfs(root, 0, 0);
    }

    private int dfs(TreeNode root, int sum, int num) {
        if (root == null) return 0;
        num = num * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum + num;
        }
        return dfs(root.left, sum, num) + dfs(root.right, sum, num);
    }

}
