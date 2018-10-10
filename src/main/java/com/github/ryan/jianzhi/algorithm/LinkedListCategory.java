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

    // 输入一个链表，输出该链表中倒数第k个结点
    public ListNode FindKthToTail(ListNode head,int k) {
        // 注意k的合法性
        if (k <= 0) return null;

        // 倒数第k个节点即正数第length-k+1个节点
        // 定义两个指针，第一个指针先走k-1步，然后第二个指针开始与第一个指针一起走
        // 当第一个指针走到最后，第二个指针所在位置即为所求(length-k+1)
        ListNode first = head, second = head;
        for (int i = 1; i < k; i++) {
            if (first == null) return null;
            first = first.next;
        }
        if (first == null) return null;

        while (first.next != null) {
            first = first.next;
            second = second.next;
        }
        return second;
    }

    // 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
    public ListNode Merge(ListNode list1,ListNode list2) {

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        ListNode p = list1, q = list2;
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
}
