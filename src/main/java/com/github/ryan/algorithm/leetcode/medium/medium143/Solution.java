package com.github.ryan.algorithm.leetcode.medium.medium143;

import com.github.ryan.algorithm.personal.component.ListNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 17,2019
 */
public class Solution {

    // use deque: 选择适当的数据结构的重要性
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        Deque<ListNode> deque = new LinkedList<>();
        ListNode node = head.next;
        while (node != null) {
            deque.offer(node);
            node = node.next;
        }

        ListNode prev = head;
        int count = 1;
        while (!deque.isEmpty()) {
            ListNode cur = (count % 2 == 0 ? deque.pollFirst() : deque.pollLast());
            prev.next = cur;
            prev = cur;
            count++;
        }
        prev.next = null; // be careful
    }
}
