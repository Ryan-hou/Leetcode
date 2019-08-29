package com.github.ryan.leetcode.algorithm.medium.meidum863;

import com.github.ryan.personal.algorithm.component.TreeNode;

import java.util.*;

public class Solution {

    // key -> cur node, value: cur node's parent
    private Map<TreeNode, TreeNode> parentMap;
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        parentMap = new HashMap<>();
        dfs(root, null);
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(target);
        Set<TreeNode> seen = new HashSet<>();
        seen.add(target);
        int dist = 0;
        // BFS
        while (!q.isEmpty()) {
            if (dist == K) {
                List<Integer> res = new ArrayList<>();
                for (TreeNode node : q) {
                    res.add(node.val);
                }
                return res;
            } else {
                int sz = q.size();
                for (int i = 0; i < sz; i++) {
                    TreeNode cur = q.poll();
                    if (cur.left != null && !seen.contains(cur.left)) {
                        q.offer(cur.left);
                        seen.add(cur.left);
                    }
                    if (cur.right != null && !seen.contains(cur.right)) {
                        q.offer(cur.right);
                        seen.add(cur.right);
                    }
                    TreeNode par = parentMap.get(cur);
                    if (par != null && !seen.contains(par)) {
                        q.offer(par);
                        seen.add(par);
                    }
                }
                dist += 1;
            }
        }
        return new ArrayList<>();
    }


    private void dfs(TreeNode cur, TreeNode parent) {
        if (cur == null) return;

        // cur != null
        parentMap.put(cur, parent);
        dfs(cur.left, cur);
        dfs(cur.right, cur);
    }
}
