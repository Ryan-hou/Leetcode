package com.github.ryan.leetcode.algorithm.medium.medium236;

import com.github.ryan.personal.algorithm.component.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date January 02,2019
 */
public class Solution {

    // 法一：递归
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 递归出口
        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root; // p,q分布在节点两侧
        return left != null ? left : right;
    }

    // 法二：迭代
    // To find the lowest common ancestor,
    // we need to find where is p and q and a way to track their ancestors.
    // A parent pointer for each node found is good for the job.(Use map)
    // After we found both p and q, we create a set of p's ancestors.
    // Then we travel through q's ancestors, the first one appears in p's is our answer.
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        // key:curNode value:parentNode
        Map<TreeNode, TreeNode> map = new HashMap<>();
        map.put(root, null);

        // 先序遍历
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!map.containsKey(p) || !map.containsKey(q)) {
            TreeNode cur = stack.pop();
            if (cur.left != null) {
                map.put(cur.left, cur);
                stack.push(cur.left);
            }
            if (cur.right != null) {
                map.put(cur.right, cur);
                stack.push(cur.right);
            }
        }

        // p and p's ancestors
        Set<TreeNode> set = new HashSet<>();
        while (p != null) {
            set.add(p);
            p = map.get(p); // get p's parent
        }

        while (!set.contains(q)) {
            q = map.get(q);
        }

        return q;
    }


}
