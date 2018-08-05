package com.github.ryan.leetcode.algorithm.medium.medium142;

import com.github.ryan.personal.algorithm.component.ListNode;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date March 01,2018
 */
public class Solution {

    /**
     * 使用快慢指针结合数学计算：
     *
     * Suppose the first meet at step k
     * The distance between the start node of list and the start node of cycle is s
     * The distance between the start node of cycle and first meeting node is m
     * The length of the Cycle is r
     *
     * then:
     * s + m = k
     * s + m + r = 2k
     * so:
     * s + m = r
     * then:
     * s = r - m
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode slow = head, fast = head, start = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                // s = r - m
                while (slow != start) {
                    slow = slow.next;
                    start = start.next;
                }
                return start;
            }
        }
        return null;
    }
}
