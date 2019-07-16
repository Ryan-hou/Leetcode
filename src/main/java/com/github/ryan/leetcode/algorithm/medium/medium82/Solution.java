package com.github.ryan.leetcode.algorithm.medium.medium82;

import com.github.ryan.personal.algorithm.component.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 16,2019
 */
public class Solution {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;

        ListNode node = head;
        int val = node.val;
        Set<Integer> dupset = new HashSet<>();
        while (node.next != null) {
            ListNode next = node.next;
            if (next.val == val && !dupset.contains(val)) {
                dupset.add(val);
            } else {
                val = next.val;
            }
            node = next;
        }

        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        ListNode cur = head;
        while (cur != null) {
            while (cur != null && dupset.contains(cur.val)) {
                cur = cur.next;
            }

            prev.next = cur;
            prev = cur;
            if (cur != null) cur = cur.next;
        }
        return dummy.next;
    }
}
