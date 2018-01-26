package com.leetcode.ryan.algorithm.medium24;

import com.leetcode.ryan.personal.component.ListNode;
import com.leetcode.ryan.personal.util.LinkedListUtil;

/**
 * @author Ryan-hou
 * @description:
 * @className: Solution
 * @date March 02,2017
 */
public class Solution {


    /**
     * 法一:
     * 注意限定条件: 算法只能使用常数空间; 不能修改节点的数值
     * 思路: 使用递归, 同时注意链表操作的断链顺序
     */
    public static ListNode swapPairs(ListNode head) {
        // 递归出口
        if (head == null || head.next == null) return head;
        ListNode n = head.next;
        head.next = swapPairs(head.next.next);
        n.next = head;
        return n;
    }

    /**
     * 法二：
     * 使用迭代：定义四个指针节点：
     * node1: 当前要交换位置的第一个节点
     * node2: 当前要交换位置的第二个节点
     * pre : 要交换位置的第一个节点的前一个节点
     * next : 要交换位置的第二个节点的下一个节点
     * 然后把pre设置为node1节点（交换完 node1,node2后的靠后的一个节点）
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     * 分析清楚每一个指针的含义，在断链操作时记得保存相关的信息(或者注意断链的操作顺序)
     * （每一个变量的含义都要清晰明确）
     * @param head
     * @return
     */
    public static ListNode swapPairsWithIter(ListNode head) {

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;

        while (pre.next != null && pre.next.next != null) {
            ListNode node1 = pre.next;
            ListNode node2 = node1.next;
            ListNode next = node2.next;

            node2.next = node1;
            node1.next = next;
            pre.next = node2;

            pre = node1;
        }
        return dummy.next;
    }

    public static void main(String[] args) {

        int[] nodes = {1, 2, 3, 4, 5};
        ListNode head = LinkedListUtil.createLinkedList(nodes, nodes.length);
        LinkedListUtil.printLinkedList(head);

        System.out.println("Swap Pairs Results: ");
        head = swapPairsWithIter(head);
        LinkedListUtil.printLinkedList(head);
    }

}
