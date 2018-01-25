package com.leetcode.ryan.algorithm.easy206;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date January 25,2018
 */
@Slf4j
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
     * Reverse recursively
     * 时间复杂度 O(n)
     * 空间复杂度 O(1)
     *
     * @param head
     * @return
     */
    public static ListNode reverseListWithRecur(ListNode head) {

        // 递归出口
        if (head == null || head.next == null) {
            return head;
        }

        ListNode headOfLeft = reverseListWithRecur(head.next);
        // 操作头节点和除头节点之外的反转链表
        head.next.next = head;
        head.next = null;
        return headOfLeft;
    }

    /**
     * 定义 pre,cur,next 三个指针，遍历链表修改指向
     *
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     * @param head
     * @return
     */
    public static ListNode reverseListWithIter(ListNode head) {

        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;

            cur.next = pre; // 修改指针指向
            // pre,cur 指针后移一个位置
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 遍历原链表，头插法建立新链表返回
     * 时间复杂度: O(n)
     * 空间复杂度： O（1）
     *
     * @param head
     * @return
     */
    public static ListNode reverseListWithCreate(ListNode head) {
        // 新链表的虚拟头节点
        ListNode newHead = new ListNode(0);

        while (head != null) {
            ListNode node = new ListNode(head.val);
            node.next = newHead.next;
            newHead.next = node;

            head = head.next;
        }
        return newHead.next;
    }



    public static void main(String[] args) {

        // 100 >> 1 >> 2 >> 3 >> null
        ListNode head = new ListNode(100);
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        head.next = one;
        one.next = two;
        two.next = three;
        three.next = null;


        head = reverseListWithCreate(head);

        while (head != null) {
            System.out.print(head.val + " >> ");
            head = head.next;
            if (head == null) {
                System.out.print("null");
            }
        }
    }

}
