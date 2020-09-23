package com.github.ryan.algorithm.leetcode.easy.easy270;

import com.github.ryan.algorithm.personal.component.TreeNode;

public class Solution {

    public int closestValue(TreeNode root, double target) {
        int closest = root.val;
        while (root != null) {
            int val = root.val;
            closest = Math.abs(closest - target) < Math.abs(val - target) ? closest : val;
            root = (val > target ? root.left : root.right);
        }
        return closest;
    }

}
