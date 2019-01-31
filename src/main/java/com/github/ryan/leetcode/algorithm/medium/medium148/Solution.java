package com.github.ryan.leetcode.algorithm.medium.medium148;

import com.github.ryan.personal.algorithm.component.ListNode;
import com.github.ryan.personal.algorithm.util.LinkedListUtil;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date January 31,2019
 */
public class Solution {

    // 链表归并排序
    // 时间复杂度O(nlogn) 空间复杂度O(logn)
    public ListNode sortList(ListNode head) {
        // 递归出口
        if (head == null || head.next == null) return head;

        ListNode mid = findMid(head);
        ListNode next = mid.next;
        mid.next = null; // 链表前后部分分离

        ListNode l = sortList(head);
        ListNode r = sortList(next);
        return merge(l, r);
    }

    private ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode merge(ListNode p, ListNode q) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (p != null && q != null) {
            if (p.val < q.val) {
                cur.next = p;
                cur = p;
                p = p.next;
            } else {
                cur.next = q;
                cur = q;
                q = q.next;
            }
        }

        while (p != null) {
            cur.next = p;
            cur = p;
            p = p.next;
        }

        while (q != null) {
            cur.next = q;
            cur = q;
            q = q.next;
        }
        return dummy.next;
    }


    public static void main(String[] args) {
        int[] nodes = {4, 2, 1, 3};
        ListNode head = LinkedListUtil.createLinkedList(nodes, 4);
        LinkedListUtil.printLinkedList(head);
        ListNode soredHead = new Solution().sortList(head);
        LinkedListUtil.printLinkedList(soredHead);

    }

}
