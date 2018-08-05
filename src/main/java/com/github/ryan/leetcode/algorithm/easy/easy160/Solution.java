package com.github.ryan.leetcode.algorithm.easy.easy160;

import com.github.ryan.personal.algorithm.component.ListNode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date February 28,2018
 */
@Slf4j
public class Solution {

    /**
     * 思路一：先计算出两个链表的长度，然后把长的链表的头移动到和短的链表同样长的位置，同时移动两个链表的头进行判断
     * 时间复杂度：O(n)
     * 空间复杂度: O(1)
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = length(headA), lenB = length(headB);
        // move headA and headB to the same start point
        while (lenA > lenB) {
            headA = headA.next;
            lenA--;
        }
        while (lenA < lenB) {
            headB = headB.next;
            lenB--;
        }
        // find the intersection until end
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    private static int length(ListNode node) {
        int length = 0;
        while (node != null) {
            node = node.next;
            length++;
        }
        return length;
    }

    // without knowing the difference in len
    // 通过交换链表头，把长的链表多余的节点先截取出去
    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        // boundary check
        if (headA == null || headB == null) {
            return null;
        }

        ListNode a = headA;
        ListNode b = headB;

        // if a，b have different len, then we will stop the loop after second iteration
        while (a != b) {
            // for the end of first iteration, we just reset the pointer to the head
            // of another linkedlist
            a = (a == null ? headB : a.next);
            b = (b == null ? headA : b.next);
        }
        return a;
    }

    public static void main(String[] args) {

    }
}
