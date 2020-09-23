package com.github.ryan.algorithm.leetcode.medium.medium328;

import com.github.ryan.algorithm.personal.component.ListNode;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date August 15,2019
 */
public class Solution {

    // Put the odd nodes in a linked list and the even nodes in another.
    // Then link the evenList to the tail of the oddList.
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
