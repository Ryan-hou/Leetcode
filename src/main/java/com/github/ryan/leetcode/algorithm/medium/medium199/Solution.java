package com.github.ryan.leetcode.algorithm.medium.medium199;

import com.github.ryan.personal.algorithm.component.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date December 08,2018
 */
public class Solution {

    // 法一：层序遍历(BFS)
    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        List<List<Integer>> allLevelRes = new ArrayList<>(); // 层序遍历二叉树的结果，每一层为allLevelRes的一个list
        Queue<Pair> q = new ArrayDeque<>();
        q.offer(Pair.makePair(0, root));

        // BFS
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int level = pair.level;
            TreeNode  node = pair.node;
            if (allLevelRes.size() <= level) {
                allLevelRes.add(new ArrayList<>());
            }
            allLevelRes.get(level).add(node.val);
            if (node.left != null) {
                q.offer(Pair.makePair(level + 1, node.left));
            }
            if (node.right != null) {
                q.offer(Pair.makePair(level + 1, node.right));
            }
        }

        for (int i = 0; i < allLevelRes.size(); i++) {
            List<Integer> levelRes = allLevelRes.get(i);
            res.add(levelRes.get(levelRes.size() - 1));
        }
        return res;
    }

    private static final class Pair {
        Integer level;
        TreeNode node;

        Pair(Integer level, TreeNode node) {
            this.level = level;
            this.node = node;
        }

        public static Pair makePair(Integer level, TreeNode node) {
            return new Pair(level, node);
        }
    }


    // 法二：使用递归
    public List<Integer> rightSideViewUseRecur(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        rightView(root, res, 0);
        return res;
    }

    // 递归语义：遍历以node为根,层数为level的二叉树，把从右边看到的第一个节点保存到res
    private void rightView(TreeNode node, List<Integer> res, int level) {

        if (node == null) return; // 递归出口
        if (res.size() == level) {
            res.add(node.val);
        }
        rightView(node.right, res, level + 1);
        rightView(node.left, res, level + 1);
    }

}
