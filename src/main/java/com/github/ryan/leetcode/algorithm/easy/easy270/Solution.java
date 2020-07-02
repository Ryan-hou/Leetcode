package com.github.ryan.leetcode.algorithm.easy.easy270;

import com.github.ryan.personal.algorithm.component.TreeNode;

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
