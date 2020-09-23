package com.github.ryan.algorithm.leetcode.hard.hard23;

import com.github.ryan.algorithm.personal.component.ListNode;

import java.util.PriorityQueue;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date June 06,2019
 */
public class Solution {

    // use min heap:
    // space complexity: O(n): n is the number of all lists's members
    // time complexity: O(nlogk): k is length of lists
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(-1);
        if (lists == null) return dummy.next;

        // PriorityQueue<ListNode> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        PriorityQueue<ListNode> q = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        for (ListNode node : lists) {
            if (node != null) {
                q.offer(node);
            }
        }

        ListNode tail = dummy;
        while (!q.isEmpty()) {
            ListNode cur = q.poll();
            tail.next = new ListNode(cur.val);
            tail = tail.next;
            if (cur.next != null) {
                q.offer(cur.next);
            }
        }
        return dummy.next;
    }


    // use merge sort thought
    // space complexity: O(n) -> n is the number of all lists's members
    // time complexity: O(nlogk) -> k is the length of lists
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int s, int e) {
        if (s >= e) return lists[e];
        int mid = s + (e - s) / 2;
        ListNode n1 = merge(lists, s, mid);
        ListNode n2 = merge(lists, mid + 1, e);
        return merge(n1, n2);
    }

    private ListNode merge(ListNode n1, ListNode n2) {
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (n1 != null && n2 != null) {
            if (n1.val < n2.val) {
                tail.next = new ListNode(n1.val);
                tail = tail.next;
                n1 = n1.next;
            } else {
                tail.next = new ListNode(n2.val);
                tail = tail.next;
                n2 = n2.next;
            }
        }

        while (n1 != null) {
            tail.next = new ListNode(n1.val);
            tail = tail.next;
            n1 = n1.next;
        }

        while (n2 != null) {
            tail.next = new ListNode(n2.val);
            tail = tail.next;
            n2 = n2.next;
        }

        return head.next;
    }
}
