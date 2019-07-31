package com.github.ryan.leetcode.algorithm.medium.medium147;

import com.github.ryan.personal.algorithm.component.ListNode;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 31,2019
 */
public class Solution {

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = pre.next;
        ListNode insertNode;
        while (cur.next != null) {
            insertNode = cur.next;

            if (insertNode.val <= pre.next.val) {
                // insert to the head
                cur.next = insertNode.next;
                insertNode.next = pre.next;
                pre.next = insertNode;
            } else if (insertNode.val >= cur.val) {
                cur = insertNode;
            } else {
                // pre.next.val < insertNode.val < cur.val
                // find insertNode's proper position
                while (pre != cur && insertNode.val > pre.next.val) {
                    pre = pre.next;
                }
                // insertNode.val <= pre.next.val
                cur.next = insertNode.next;
                insertNode.next = pre.next;
                pre.next = insertNode;
                pre = dummy;
            }
        }
        return dummy.next;
    }
}
