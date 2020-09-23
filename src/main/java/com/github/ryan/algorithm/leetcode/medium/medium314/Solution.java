package com.github.ryan.algorithm.leetcode.medium.medium314;

import com.github.ryan.algorithm.personal.component.TreeNode;
import javafx.util.Pair;

import java.util.*;

public class Solution {

    // BFS with node's row and col
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        // key -> col, value -> list of node val at this col
        Map<Integer, List<Integer>> colTable = new HashMap<>();
        // Pair: key -> TreeNode, value -> col of this node
        Queue<Pair<TreeNode, Integer>> q = new ArrayDeque<>();
        q.offer(new Pair<>(root, 0));
        while (!q.isEmpty()) {
            Pair<TreeNode, Integer> pair = q.poll();
            TreeNode cur = pair.getKey();
            int col = pair.getValue();
            colTable.computeIfAbsent(col, x -> new ArrayList<>()).add(cur.val);
            if (cur.left != null) {
                q.offer(new Pair<>(cur.left, col - 1));
            }
            if (cur.right != null) {
                q.offer(new Pair<>(cur.right, col + 1));
            }
        }

        List<Integer> cols = new ArrayList<>(colTable.keySet());
        Collections.sort(cols);
        for (int col : cols) {
            res.add(colTable.get(col));
        }
        return res;
    }

}
