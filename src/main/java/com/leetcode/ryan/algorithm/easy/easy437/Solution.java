package com.leetcode.ryan.algorithm.easy.easy437;

import com.leetcode.ryan.personal.component.TreeNode;
import com.leetcode.ryan.personal.util.TreeUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date January 30,2018
 */
@Slf4j
public class Solution {


    /**
     * 注意题目条件：
     * 1）路径不限于从root到leaf，但是保证从父节点到子节点
     * 2）节点数字可以为负数
     *
     * 思路：
     * 使用递归解法，递归函数语义： 以root节点为根，和为sum的路径总数
     * @param root
     * @param sum
     * @return
     */
    public static int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        int res = findPath(root, sum);
        res += pathSum(root.left, sum);
        res += pathSum(root.right, sum);
        return res;
    }

    /**
     * 在以node为根节点的二叉树中，寻找包含node的路径，和为sum
     * 返回这样的路径个数
     * @param node
     * @param sum
     * @return
     */
    private static int findPath(TreeNode node, int sum) {
        if (node == null) {
            return 0;
        }

        int res = 0;
        if (node.val == sum) {
            res += 1;
        }

        res += findPath(node.left, sum - node.val);
        res += findPath(node.right, sum - node.val);
        return res;
    }

    public static void main(String[] args) {
        //[10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
        //[1,null,2,null,3,null,4,null,5], sum=3
        int[] nodes = {1, 0, 2, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5};
        TreeNode root = TreeUtil.createTreeFromArray(nodes, 0);
        int result = pathSum(root, 3);
        log.info("path sum = {}", result);
    }
}
