package com.github.ryan.leetcode.algorithm.medium.medium19;

import com.github.ryan.personal.algorithm.component.ListNode;
import com.github.ryan.personal.algorithm.util.LinkedListUtil;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date January 26,2018
 */
public class Solution {



    // 思路一：先遍历一遍链表，得到链表节点的总数，然后把倒数n转为正数 length-n+1


    /**
     * 思路二：
     * 只遍历一遍链表，定义两个指针，两个指针的间距为 n+1,同时移动两个指针，得到待删除节点的前一个节点
     * 与数组算法中常见的双指针/滑动窗口思路一致
     * 题目保证了n的取值合法
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode p = dummy;
        ListNode q = dummy;
        for (int i = 0; i < n + 1; i++) {
            q = q.next;
        }

        while (q != null) {
            p = p.next;
            q = q.next;
        }

        ListNode delNode = p.next;
        p.next = delNode.next;
        delNode.next = null;

        return dummy.next;
    }


    public static void main(String[] args) {
        int[] nodes = {1, 2, 3, 4, 5};
        ListNode head = LinkedListUtil.createLinkedList(nodes, nodes.length);
        LinkedListUtil.printLinkedList(head);

        head = removeNthFromEnd(head, 3);
        LinkedListUtil.printLinkedList(head);
    }
}
