package com.github.ryan.leetcode.algorithm.medium.medium102;

import com.github.ryan.personal.algorithm.component.TreeNode;
import com.github.ryan.personal.algorithm.util.TreeUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date January 29,2018
 */
@Slf4j
public class Solution {

    private static class LevelNode {
        int level;
        TreeNode node;

        public LevelNode(int level, TreeNode node) {
            this.level = level;
            this.node = node;
        }
    }

    /**
     * 使用队列和迭代实现层序遍历
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;

        Deque<LevelNode> q = new ArrayDeque<>();
        q.add(new LevelNode(0, root));
        while (!q.isEmpty()) {
            TreeNode node = q.peek().node;
            int level = q.peek().level;
            q.poll();

            if (level == res.size()) {
                res.add(new ArrayList<>());
            }

            res.get(level).add(node.val);
            if (node.left != null) {
                q.add(new LevelNode(level + 1, node.left));
            }
            if (node.right != null) {
                q.add(new LevelNode(level + 1, node.right));
            }
        }

        return res;
    }

    /**
     * 使用递归实现层序遍历
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderWithRecur(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelTraversal(root, 0, res);
        return res;
    }

    private static void levelTraversal(TreeNode root, int depth, List<List<Integer>> res) {
        if (root == null) {
            return;
        }

        if (res.size() == depth) {
            res.add(new ArrayList<>());
        }

        res.get(depth).add(root.val);
        levelTraversal(root.left, depth + 1, res);
        levelTraversal(root.right, depth + 1, res);
    }


    public static void main(String[] args) {
        int[] arrays = {3, 9, 20, 99, 0, 15, 7};
        TreeNode root = TreeUtil.createTreeFromArray(arrays, 0);
        List<List<Integer>> res = levelOrderWithRecur(root);
        log.info("level order result = {},", res);
    }
}
