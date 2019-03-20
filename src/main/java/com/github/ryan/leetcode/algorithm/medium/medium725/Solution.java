package com.github.ryan.leetcode.algorithm.medium.medium725;

import com.github.ryan.personal.algorithm.component.ListNode;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date March 20,2019
 */
public class Solution {

    public ListNode[] splitListToParts(ListNode root, int k) {
        if (k <= 0) return new ListNode[0];
        if (root == null) return new ListNode[k];

        ListNode[] res = new ListNode[k];
        ListNode cur = root;
        int len = lenOfList(root);
        int p = len / k;
        int q = p + 1, qCount = len % k;

        for (int i = 0; i < qCount; i++) {
            res[i] = cur;
            cur = processPerNode(res, cur, q, i);
        }

        for (int i = qCount; i < k; i ++) {
            res[i] = cur;
            cur = processPerNode(res, cur, p, i);
        }
        return res;
    }

    private int lenOfList(ListNode node) {
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }
        return len;
    }

    private ListNode processPerNode(ListNode[] res, ListNode cur, int n, int i) {

        ListNode tail = null;
        while (n > 0) {
            if (n == 1) {
                tail = cur;
            }
            cur = cur.next;
            n--;
        }
        if (tail != null) {
            tail.next = null;
        }
        return cur;
    }
}
