package com.github.ryan.algorithm.leetcode.medium.medium199;

import com.github.ryan.algorithm.personal.component.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date December 08,2018
 */
public class Solution {

    /**
     * Because the tree topography is unknown ahead of time,
     * it is not possible to design an algorithm that visits asymptotically fewer than nn nodes.
     * Therefore, we should try to aim for a linear time solution.
     * With that in mind, let's consider a few equally-efficient solutions. (BFS & DFS)
     *
     */

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
            TreeNode node = pair.node;
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

    // 法四：elegant BFS
    public List<Integer> rightSideView4(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            while (size > 0) {
                TreeNode cur = q.poll();
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }

                if (size == 1) {
                    res.add(cur.val);
                }
                size--;
            }
        }
        return res;
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

    // 法三：DFS
    public List<Integer> rightSideView3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;


        // key:level -> value:right most value at key level
        Map<Integer, Integer> viewMap = new HashMap<>();
        Deque<Pair> stack = new ArrayDeque<>();
        stack.push(Pair.makePair(0, root));
        // Use DFS(root - right - left)
        while (!stack.isEmpty()) {
            Pair pair = stack.pop();
            TreeNode curNode = pair.node;
            int level = pair.level;
            if (!viewMap.containsKey(level)) {
                viewMap.put(level,  curNode.val);
            }
            if (curNode.left != null) {
                stack.push(Pair.makePair(level + 1, curNode.left));
            }
            if (curNode.right != null) {
                stack.push(Pair.makePair( level + 1, curNode.right));
            }
        }

        for (int i = 0; i < viewMap.size(); i++) {
            res.add(viewMap.get(i));
        }
        return res;
    }

}
