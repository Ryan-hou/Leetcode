package com.github.ryan.leetcode.algorithm.medium.medium113;

import com.github.ryan.personal.algorithm.component.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 15,2019
 */
public class Solution {

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        res.clear();
        if (root == null) return res;
        pathSum(root, sum, new ArrayList<>());
        return res;
    }

    private void pathSum(TreeNode root, int sum, List<Integer> list) {
        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                list.add(root.val);
                res.add(new ArrayList<>(list));
                list.remove(list.size() - 1);
            }
            return;
        }

        if (root.left != null) {
            list.add(root.val);
            pathSum(root.left, sum - root.val, list);
            list.remove(list.size() - 1);
        }
        if (root.right != null) {
            list.add(root.val);
            pathSum(root.right, sum - root.val, list);
            list.remove(list.size() - 1);
        }
    }
}
