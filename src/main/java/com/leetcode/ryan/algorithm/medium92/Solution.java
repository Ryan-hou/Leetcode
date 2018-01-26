package com.leetcode.ryan.algorithm.medium92;

import com.leetcode.ryan.personal.component.ListNode;
import com.leetcode.ryan.personal.util.LinkedListUtil;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date January 25,2018
 */
public class Solution {


    /**
     * 操作链表一定要注意断链顺序，不要丢失要查找的节点
     * 在断开一个链节点时，思考下断开该节点后丢失的信息保存了吗？？？
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * @param head
     * @param m
     * @param n
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0); // create a dummy node to mark the head of this list
        dummy.next = head;
        // make a pointer pre as a marker for the node before reversing
        ListNode pre = dummy;
        for (int i = 0; i < m - 1; i++) pre = pre.next;

        ListNode start = pre.next; // a point to the beginning of a sub-list that will be reversed.
        ListNode then = start.next; // a point to a node that will be reversed

        // always insert 'then' to the front (pre.next)
        for (int i = 0; i < n - m; i++) {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {


        // 1->2->3->4->5->NULL
        int[] nodes = {1, 2, 3, 4, 5};
        ListNode head = LinkedListUtil.createLinkedList(nodes, nodes.length);
        LinkedListUtil.printLinkedList(head);

        // 1->4->3->2->5->NULL
        head = reverseBetween(head, 2, 4);
        LinkedListUtil.printLinkedList(head);
    }

}
