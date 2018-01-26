package com.leetcode.ryan.algorithm.easy.easy237;

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
     * 思路：
     * 因为我们只能访问待删除的的节点，无法获取待删除节点的前一个节点，所以这里要采用非常规做法，修改链表节点的值
     * 时间复杂度： O(1)
     * 空间复杂度: O(1)
     * @param node
     */
    public static void deleteNode(ListNode node) {
        if (node == null) return;
        if (node.next == null) {
           node = null;
           return;
        }

        node.val = node.next.val;
        ListNode delNode = node.next;
        node.next = delNode.next;

        delNode.next = null;
    }


    public static void main(String[] args) {
        int[] nodes = {1, 4, 55, 7, 9};
        ListNode head = LinkedListUtil.createLinkedList(nodes, nodes.length);
        LinkedListUtil.printLinkedList(head);
        ListNode delNode = head.next.next; // 55

        deleteNode(delNode);
        LinkedListUtil.printLinkedList(head);
    }
}
