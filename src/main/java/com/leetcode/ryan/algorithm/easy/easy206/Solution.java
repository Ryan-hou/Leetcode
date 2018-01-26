package com.leetcode.ryan.algorithm.easy.easy206;

import com.leetcode.ryan.personal.component.ListNode;
import com.leetcode.ryan.personal.util.LinkedListUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date January 25,2018
 */
@Slf4j
public class Solution {

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
        int[] nodes = {100, 1, 2, 3};
        ListNode head = LinkedListUtil.createLinkedList(nodes, nodes.length);
        LinkedListUtil.printLinkedList(head);

        head = reverseListWithCreate(head);
        LinkedListUtil.printLinkedList(head);
    }

}
