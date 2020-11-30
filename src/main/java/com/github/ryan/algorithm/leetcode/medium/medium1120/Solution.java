package com.github.ryan.algorithm.leetcode.medium.medium1120;

import com.github.ryan.algorithm.personal.component.TreeNode;

public class Solution {

    // for each node in the tree, we maintain 3 values for subtree
    // which use this node as root node
    private static class State {
        // count of nodes in the subtree
        int nodeCount;

        // sum of values in the subtree
        int valueSum;

        // max average found in the subtree
        double maxAvg;

        State(int nodeCount, int valueSum, double maxAvg) {
            this.nodeCount = nodeCount;
            this.valueSum = valueSum;
            this.maxAvg = maxAvg;
        }
    }

    public double maximumAverageSubtree(TreeNode root) {
        return postOrder(root).maxAvg;
    }

    private State postOrder(TreeNode root) {
        if (root == null) {
            return new State(0, 0, 0);
        }

        State left = postOrder(root.left);
        State right = postOrder(root.right);

        // process current node
        int nodeCount = left.nodeCount + right.nodeCount + 1;
        int sum = left.valueSum + right.valueSum + root.val;
        double maxAvg = Math.max(Math.max(left.maxAvg, right.maxAvg), sum * 1.0 / nodeCount);
        return new State(nodeCount, sum, maxAvg);
    }

}
