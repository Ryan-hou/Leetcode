package com.github.ryan.algorithm.leetcode.medium.medium109;

import com.github.ryan.algorithm.personal.component.ListNode;
import com.github.ryan.algorithm.personal.component.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 11,2019
 */
public class Solution {

    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        Integer[] res = new Integer[list.size()];
        res = list.toArray(res);
        if (res.length < 1) return null;
        return convertArray2BST(res, 0, res.length - 1);
    }

    private TreeNode convertArray2BST(Integer[] res, int l, int r) {
        if (l > r)  return null;

        int mid = l + (r - l) / 2;
        TreeNode root = new TreeNode(res[mid]);
        root.left = convertArray2BST(res, l, mid - 1);
        root.right = convertArray2BST(res, mid + 1, r);
        return root;
    }
}
