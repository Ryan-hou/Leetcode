package com.github.ryan.algorithm.leetcode.medium.medium366;

import com.github.ryan.algorithm.personal.component.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    // return root's level, leaf is level 0
    private int dfs(TreeNode root, List<List<Integer>> res) {
        if (root == null) {
            return -1; // 递归出口
        }

        int level = Math.max(dfs(root.left, res), dfs(root.right, res)) + 1;
        if (res.size() <= level) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.val);
        return level;
    }

}
