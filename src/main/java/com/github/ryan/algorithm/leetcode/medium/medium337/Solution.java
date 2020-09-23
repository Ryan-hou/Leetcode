package com.github.ryan.algorithm.leetcode.medium.medium337;

import com.github.ryan.algorithm.personal.component.TreeNode;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date April 10,2019
 */
public class Solution {

    // 法1：定义递归函数
    public int rob(TreeNode root) {
        return Math.max(robInclude(root), robExclude(root));
    }

    private int robInclude(TreeNode cur) {
        if (cur == null) return 0;
        return cur.val + robExclude(cur.left) + robExclude(cur.right);
    }

    private int robExclude(TreeNode cur) {
        if (cur == null) return 0;
        return rob(cur.left) + rob(cur.right);
    }


    // 法2
    // dfs all the nodes of the tree, each node return two number, int[] num,
    // num[0] is the max value while rob this node, num[1] is max value while not rob this value.
    // Current node return value only depend on its children's value.
    public int rob2(TreeNode root) {
        // nums[0]:rob root node / nums[1]: not rob root node
        int[] nums = dfs(root);
        return Math.max(nums[0], nums[1]);
    }

    private int[] dfs(TreeNode curNode) {
        if (curNode == null) return new int[2];
        int[] left = dfs(curNode.left);
        int[] right = dfs(curNode.right);
        int[] res = new int[2];
        res[0] = curNode.val + left[1] + right[1];
        res[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return res;
    }
}
