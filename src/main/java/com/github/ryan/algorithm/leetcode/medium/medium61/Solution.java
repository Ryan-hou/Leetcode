package com.github.ryan.algorithm.leetcode.medium.medium61;

import com.github.ryan.algorithm.personal.component.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 01,2019
 */
public class Solution {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) return head;

        int len = 0;
        // key -> curNode, value -> curNode's previous node
        Map<ListNode, ListNode> dict = new HashMap<>();
        dict.put(head, null);
        ListNode cur = head;
        ListNode tail = null;
        while (cur != null) {
            len += 1;
            if (cur.next == null) {
                tail = cur;
            } else {
                // cur.next != null
                dict.put(cur.next, cur);
            }
            cur = cur.next;
        }

        k = k % len;
        for (int i = 0; i < k; i++) {
            ListNode newTail = dict.get(tail);
            dict.put(tail, null);
            dict.put(head, tail);
            tail.next = head;
            head = tail;
            tail = newTail;
            tail.next = null;
        }
        return head;
    }
}
