package com.github.ryan.leetcode.algorithm.easy.easy104;

import com.github.ryan.personal.algorithm.component.TreeNode;
import com.github.ryan.personal.algorithm.util.TreeUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date January 30,2018
 */
@Slf4j
public class Solution {

    /**
     * 使用递归：二叉树本身就是递归定义的数据结构，使用递归来求解二叉树问题很直接
     * 该递归函数的语义为：计算以root为根节点的二叉树的最大深度
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        // 递归出口
        if (root == null) {
            return 0;
        }

        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
        // return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1
    }

    /**
     * 迭代：BFS，使用队列(层序遍历变形)
     * @param root
     * @return
     */
    public static int maxDepthBFS(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int depth = 0;
        while (!q.isEmpty()) {
            int size = q.size(); // 二叉树每一层的节点个数
            while (size-- > 0) {
                TreeNode node = q.poll();
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            //每处理完一层，深度加1
            depth++;
        }
        return depth;
    }

    /**
     * 迭代：DFS，使用栈(先序遍历变形)
     * @param root
     * @return
     */
    public static int maxDepthDFS(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> nodeStack = new ArrayDeque<>();
        Deque<Integer> nodeLevelStack = new ArrayDeque<>(); //对应节点在二叉树是第几层，也就是二叉树的深度
        nodeStack.push(root);
        nodeLevelStack.push(1);
        int depth = 0;

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int curLevel = nodeLevelStack.pop();
            depth = Math.max(curLevel, depth);
            if (node.right != null) {
                nodeStack.push(node.right);
                nodeLevelStack.push(curLevel + 1);
            }
            if (node.left != null) {
                nodeStack.push(node.left);
                nodeLevelStack.push(curLevel + 1);
            }
        }
        return depth;
    }

    public static void main(String[] args) {
        // [3,9,20,null,null,15,7]
        int[] nodes = {3, 9, 20, 0, 0, 15, 7};
        TreeNode root = TreeUtil.createTreeFromArray(nodes, 0);
        //log.info("max depth = {}", maxDepth(root));
        log.info("max depth = {}", maxDepthBFS(root));
        log.info("max depth = {}", maxDepthDFS(root));
    }
}
