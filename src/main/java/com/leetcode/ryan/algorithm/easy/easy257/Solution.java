package com.leetcode.ryan.algorithm.easy.easy257;

import com.leetcode.ryan.personal.component.TreeNode;
import com.leetcode.ryan.personal.util.TreeUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date January 30,2018
 */
@Slf4j
public class Solution {


    /**
     * 使用递归解法：
     * 递归函数的语义： 返回以root为根节点的所有的root-to-left的路径
     *
     * @param root
     * @return
     */
    public static List<String> binaryTreePaths(TreeNode root) {

        List<String> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        // 叶子节点（递归出口）
        if (root.left == null && root.right == null) {
            res.add(root.val + "");
            return res;
        }

        List<String> leftPaths = binaryTreePaths(root.left);
        for (int i = 0; i < leftPaths.size(); i++) {
            res.add(root.val + "->" + leftPaths.get(i));
        }

        List<String> rightPaths = binaryTreePaths(root.right);
        for (int i = 0; i < rightPaths.size(); i++) {
            res.add(root.val + "->" + rightPaths.get(i));
        }
        return res;
    }



    public static void main(String[] args) {
        int[] nodes = {3, 4, 5, 0, 6, 7, 8};
        TreeNode root = TreeUtil.createTreeFromArray(nodes, 0);
        log.info("All paths = {}", binaryTreePaths(root));
    }

}
