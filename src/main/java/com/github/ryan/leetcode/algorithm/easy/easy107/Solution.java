package com.github.ryan.leetcode.algorithm.easy.easy107;

import com.github.ryan.personal.algorithm.component.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 28,2019
 */
public class Solution {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        int maxLevel = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<Pair> q = new ArrayDeque<>();
        q.offer(new Pair(root, 0));
        while (!q.isEmpty()) {
            Pair p = q.poll();
            int level = p.level;
            TreeNode node = p.node;
            maxLevel = Math.max(maxLevel, level);
            if (map.get(level) == null) {
                map.put(level, new ArrayList<>());
            }
            map.get(level).add(node.val);
            if (node.left != null) {
                q.offer(new Pair(node.left, level + 1));
            }
            if (node.right != null) {
                q.offer(new Pair(node.right, level + 1));
            }
        }

        for (int i = maxLevel; i >= 0; i--) {
            res.add(map.get(i));
        }
        return res;
    }

    private static final class Pair {
        TreeNode node;
        int level;
        Pair(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }


    // use while and for loop process level
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            // process one level
            int size = q.size();
            List<Integer> levelRes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                levelRes.add(node.val);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            res.addFirst(levelRes);
        }
        return res;
    }

}
