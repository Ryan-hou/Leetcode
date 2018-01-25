package com.leetcode.ryan.algorithm.medium92;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date January 25,2018
 */
public class Solution {

    // Definition for singly-linked list
    private static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }


    /**
     * 操作链表一定要注意断链顺序，不要丢失要查找的节点
     * 在断开一个链节点时，思考下断开该节点后丢失的信息保存了吗？？？
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

        // 1->2->3->4->5->NULL,
        ListNode head = new ListNode(1);
        ListNode one = new ListNode(2);
        ListNode two = new ListNode(3);
        ListNode three = new ListNode(4);
        ListNode four = new ListNode(5);
        head.next = one;
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = null;


        // 1->4->3->2->5->NULL
        head = reverseBetween(head, 2, 4);

        while (head != null) {
            System.out.print(head.val + " >> ");
            head = head.next;
            if (head == null) {
                System.out.print("null");
            }
        }
    }

}
