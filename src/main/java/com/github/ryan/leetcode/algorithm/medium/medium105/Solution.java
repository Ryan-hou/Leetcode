package com.github.ryan.leetcode.algorithm.medium.medium105;

import com.github.ryan.personal.algorithm.component.TreeNode;
import com.github.ryan.personal.algorithm.util.TreeUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date March 19,2018
 */
@Slf4j
public class Solution {

    /**
     * 递归构造：
     * Say we have 2 arrays, PRE and IN
     * Preorder traversing implies that PRE[0] is the root node.
     * Then we can find this PRE[0] in IN, say it's IN[5]
     * Now we know that IN[5] is root, so we know that IN[0]-IN[4]
     * is on the left side, IN[6] to the end is on the right side.
     * Recursively doing this on subarrays, we can build a tree out of it.
     * @param preorder
     * @param inorder
     * @return
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return builder(0, 0, inorder.length - 1, preorder, inorder);
    }

    private static TreeNode builder(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        // 递归出口
        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = 0; // Index of current root in inorder
        // for循环可以使用Map来作为查找集进行优化
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
            }
        }

        root.left = builder(preStart + 1, inStart, inIndex - 1, preorder, inorder);
        root.right = builder(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
        return root;
    }

    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode root = buildTree(preorder, inorder);
        log.info("Level order tree, result = {}", TreeUtil.levelOrderTree(root));
    }
}
