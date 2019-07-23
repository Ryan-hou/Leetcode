package com.github.ryan.leetcode.algorithm.medium.medium86;

import com.github.ryan.personal.algorithm.component.ListNode;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Solution
 * @date July 23,2019
 */
public class Solution {

    // use leetcode's solution method
    public ListNode partition(ListNode head, int x) {

        ListNode beforeDummy = new ListNode(0);
        ListNode before = beforeDummy;
        ListNode afterDummy = new ListNode(0);
        ListNode after = afterDummy;

        while (head != null) {
            if (head.val < x) {
                before.next = head;
                before = before.next;
            } else {
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }

        after.next = null;
        // link before and after list
        before.next = afterDummy.next;
        return beforeDummy.next;
    }
}
