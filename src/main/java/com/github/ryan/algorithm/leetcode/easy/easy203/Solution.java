package com.github.ryan.algorithm.leetcode.easy.easy203;

import com.github.ryan.algorithm.personal.util.LinkedListUtil;
import com.github.ryan.algorithm.personal.component.ListNode;

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
            head = head.next;
            delNode.next = null; // help gc
        }

        if (head == null) {
            return null;
        }

        // head != null && head.val != val
        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = prev.next.next;
                delNode.next = null; // help gc
            } else {
                prev = prev.next;
            }
        }
        return head;
    }

    public static ListNode removeElementsWithDummy(ListNode head, int val) {
        // 构造虚拟头节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = prev.next.next;
                delNode.next = null; // help gc
            } else {
                prev = prev.next;
            }
        }
        return dummy.next;
    }

    // 使用递归 -》链表本身也是一个递归结构
    public static ListNode removeElementsRecursive(ListNode head, int val) {
        if (head == null) return null;

        ListNode newHead = removeElementsRecursive(head.next, val);
        if (head.val == val) {
            head.next = null; // help gc
            return newHead;
        } else {
            head.next = newHead;
            return head;
        }
    }

    public static void main(String[] args) {
        // 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6
        int[] nodes = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = LinkedListUtil.createLinkedList(nodes, nodes.length);
        LinkedListUtil.printLinkedList(head);

        int val = 6;
        head = removeElementsRecursive(head, val);
        LinkedListUtil.printLinkedList(head);
    }

}
