package com.leetcode.ryan.algorithm.easy.easy83;

import com.leetcode.ryan.personal.component.ListNode;
import com.leetcode.ryan.personal.util.LinkedListUtil;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date January 26,2018
 */
public class Solution {

    /**
     * 时间复杂度： O(n)
     * 空间复杂度： O(1)
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;

        ListNode cur = head;
        ListNode next = cur.next;
        while (next != null) {
            if (cur.val == next.val) {
                cur.next = next.next;
                next.next = null;
                next = cur.next;
            } else {
                cur = cur.next;
                next = cur.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {

        int[] nodes = {1, 1, 2, 3, 3, 3, 5};
        ListNode head = LinkedListUtil.createLinkedList(nodes, nodes.length);
        LinkedListUtil.printLinkedList(head);

        head = deleteDuplicates(head);
        LinkedListUtil.printLinkedList(head);
    }


}
