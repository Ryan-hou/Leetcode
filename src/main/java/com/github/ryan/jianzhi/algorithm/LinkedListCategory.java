package com.github.ryan.jianzhi.algorithm;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className LinkedListCategory
 * @date October 09,2018
 */
public class LinkedListCategory {

    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
     * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
     * 需要考虑各种可能的情况：
     * 1->2->3
     * 1->2->2
     * 1->2->2->3
     * 2->2
     * 1->2->2->3->3->4
     * 在删除节点时，想清楚待删除节点的前一个节点和删除节点后的下一个节点，以及指针如何往后移动这三件事
     */
    public ListNode deleteDuplication(ListNode pHead) {


        ListNode dummy = new ListNode(-1);
        dummy.next = pHead;
        ListNode prev = dummy; // prev指针为待删除节点的前一个节点
        while (prev.next != null) {

            ListNode cur = prev.next; // 当前待检查的节点
            if (cur.next == null || cur.next.val != cur.val) {
                prev = cur;
            } else {
                // cur.next.val == cur.val
                do {
                    cur = cur.next;
                } while(cur.next != null && cur.next.val == cur.val);

                prev.next = cur.next;
                // prev还不能后移，考虑1->2->2->3->3->4这种情况
            }
        }
        return dummy.next;
    }
}
