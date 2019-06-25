package com.github.ryan.leetcode.algorithm.hard.hard25;

import com.github.ryan.personal.algorithm.component.ListNode;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 25,2019
 */
public class Solution {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode nextPre = null;
        while (pre != null) {
            nextPre = pre.next;
            ListNode p = pre;
            for (int i = 0; i < k && p != null; i++) {
                p = p.next;
            }
            if (p == null) break;

            doReverse(pre, p);
            pre = nextPre;
        }
        return dummy.next;
    }

    private void doReverse(ListNode pre, ListNode last) {
        ListNode p;
        while (pre.next != last) {
            p = pre.next;
            pre.next = p.next;
            p.next = last.next;
            last.next = p;
        }
    }

}
