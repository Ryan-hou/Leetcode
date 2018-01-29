package com.leetcode.ryan.personal.util;

import com.leetcode.ryan.personal.component.TreeNode;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: TreeUtil
 * @date January 29,2018
 */
public class TreeUtil {

    private TreeUtil() {}

    /**
     * 通过数组构建二叉树，数组元素0代表二叉树节点为null
     * 采用层序遍历的方式构建，从数组某个位置的元素开始生成树
     * @param array
     * @param start
     * @return
     */
    public static TreeNode createTreeFromArray(int[] array, int start) {
        if (array[start] == 0) {
            return null;
        }

        TreeNode root = new TreeNode(array[start]);
        int lnode = 2*start + 1;
        int rnode = 2*start + 2;
        if (lnode > array.length - 1) {
            root.left = null;
        } else {
            root.left = createTreeFromArray(array, lnode);
        }

        if (rnode > array.length - 1) {
            root.right = null;
        } else {
            root.right = createTreeFromArray(array, rnode);
        }

        return root;
    }

}
