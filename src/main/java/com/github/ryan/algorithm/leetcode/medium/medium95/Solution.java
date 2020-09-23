package com.github.ryan.algorithm.leetcode.medium.medium95;

import com.github.ryan.algorithm.personal.component.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 24,2019
 */
public class Solution {

    public List<TreeNode> generateTrees(int n) {
        if (n < 1) return new ArrayList<>();
        return generate(n, 0);
    }

    private List<TreeNode> generate(int n, int offset) {
        List<TreeNode> res = new ArrayList<>();
        if (n == 0) {
            res.add(null);
            return res;
        }

        for (int i = 1; i <= n; i++) {
            List<TreeNode> left = generate(i - 1, offset);
            List<TreeNode> right = generate(n - i, i + offset);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i + offset);
                    root.left = l;
                    root.right = r;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
