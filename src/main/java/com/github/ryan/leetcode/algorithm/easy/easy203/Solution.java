package com.github.ryan.leetcode.algorithm.easy.easy203;

import com.github.ryan.personal.algorithm.util.LinkedListUtil;
import com.github.ryan.personal.algorithm.component.ListNode;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date January 26,2018
 */
public class Solution {


    public static ListNode removeElements(ListNode head, int val) {

        // 待删除的节点在链首
        while (head != null && head.val == val) {
            ListNode delNode = head;
            head = delNode.next;
            delNode.next = null;
        }

        if (head == null) {
            return head;
        }

        ListNode cur = head;
        while (cur.next != null) {
            if (cur.next.val == val) {
                ListNode delNode = cur.next;
                cur.next = delNode.next;
                delNode.next = null;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    public static ListNode removeElementsWithDummy(ListNode head, int val) {
        // 构造虚拟头节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode cur = dummy;
        while (cur.next != null) {
            if (cur.next.val == val) {
                ListNode delNode = cur.next;
                cur.next = delNode.next;
                delNode.next = null;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        // 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6
        int[] nodes = {1, 1, 1, 2, 6, 3, 4, 5, 6};
        ListNode head = LinkedListUtil.createLinkedList(nodes, nodes.length);
        LinkedListUtil.printLinkedList(head);

        int val = 1;
        head = removeElementsWithDummy(head, val);
        LinkedListUtil.printLinkedList(head);
    }

}
