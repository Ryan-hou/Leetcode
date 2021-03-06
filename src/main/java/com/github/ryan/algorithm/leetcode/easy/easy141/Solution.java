package com.github.ryan.algorithm.leetcode.easy.easy141;

import com.github.ryan.algorithm.personal.util.LinkedListUtil;
import com.github.ryan.algorithm.personal.component.ListNode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date March 01,2018
 */
@Slf4j
public class Solution {

    // 使用快慢指针
    public static boolean hasCycle(ListNode head) {

        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int[] nodes = {1, 2, 3, 4};

        ListNode head = LinkedListUtil.createLinkedList(nodes, nodes.length);
        LinkedListUtil.printLinkedList(head);

        // 尾节点指向头节点，构成环
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = head;

        log.info("Has cycle ? = {}", hasCycle(head));
    }

}
