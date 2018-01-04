package com.leetcode.ryan.algorithm.medium103;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date July 18,2017
 */
@Slf4j
public class Solution {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * Recursive solution: DFS
     * @param root
     * @return
     */
    private static List<List<Integer>> zigzagLevelOrderOne(TreeNode root) {
        List<List<Integer>> sol = new ArrayList<>();
        travel(root, sol, 0);
        return sol;
    }

    private static void travel(TreeNode curr, List<List<Integer>> sol, int level) {
        if (curr == null) return; // 递归出口

        if (sol.size() <= level) {
            List<Integer> newLevel = new ArrayList<>();
            sol.add(newLevel);
        }
        List<Integer> collection = sol.get(level);
        if (level % 2 == 0) collection.add(curr.val);
        else collection.add(0, curr.val);

        travel(curr.left, sol, level + 1);
        travel(curr.right, sol, level + 1);
    }

    /**
     * Iterative solution
     * @param root
     * @return
     */
    private static List<List<Integer>> zigzagLevelOrderTwo(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) return results;

        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        boolean isForward = true;
        int levelNumNodes = 1;
        List<Integer> result = new ArrayList<>();

        while (!que.isEmpty()) {
            TreeNode node = que.poll();

            // From left to right
            if (isForward) {
                result.add(node.val);
            } else {
                // From right to left
                result.add(0, node.val);
            }

            if (node.left != null)
                que.add(node.left);
            if (node.right != null)
                que.add(node.right);

            --levelNumNodes;
            // New level
            if (levelNumNodes == 0) {
                results.add(result);
                levelNumNodes = que.size();

                if (levelNumNodes != 0) {
                    result = new ArrayList<>();
                }

                // Change direction
                isForward = !isForward;
            }
        }
        return results;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode left1 = new TreeNode(4);
        TreeNode left12 = new TreeNode(6);
        TreeNode right1 = new TreeNode(5);
        TreeNode rigth12 = new TreeNode(7);
        root.left = left1;
        left1.right = left12;
        root.right = right1;
        right1.left = rigth12;

        List<List<Integer>> lists = zigzagLevelOrderTwo(root);
        log.info("lists = {}", lists);
    }
}
