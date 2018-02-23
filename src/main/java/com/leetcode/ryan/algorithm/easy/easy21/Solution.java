package com.leetcode.ryan.algorithm.easy.easy21;

import com.leetcode.ryan.personal.component.ListNode;
import com.leetcode.ryan.personal.util.LinkedListUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date February 23,2018
 */
@Slf4j
public class Solution {

    /**
     * 思路：仿照归并排序算法中的归并操作，使用尾插法建立单链表
     *
     * 时间复杂度：O(n) n为两个链表的长度
     * 空间复杂度：O(n)
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        ListNode p = l1;
        ListNode q = l2;

        while (p != null && q != null) {
            if (p.val < q.val) {
                tail.next = p;
                tail = p;
                p = p.next;
            } else {
                tail.next = q;
                tail = q;
                q = q.next;
            }
        }

        while (p != null) {
            tail.next = p;
            tail = p;
            p = p.next;
        }

        while (q != null) {
            tail.next = q;
            tail = q;
            q = q.next;
        }

        return dummyHead.next;
    }


    public static void main(String[] args) {
        int[] nodes = {1, 2, 4};
        ListNode l1 = LinkedListUtil.createLinkedList(nodes, 3);
        int[] nodes2 = {1, 3, 4};
        ListNode l2 = LinkedListUtil.createLinkedList(nodes2, 3);
        ListNode head = mergeTwoLists(l1, l2);
        LinkedListUtil.printLinkedList(head);
    }
}
