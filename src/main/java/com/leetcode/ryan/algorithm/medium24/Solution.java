package com.leetcode.ryan.algorithm.medium24;

/**
 * @author Ryan-hou
 * @description:
 * @className: Solution
 * @date March 02,2017
 */
public class Solution {


    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

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

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        ListNode nodeOne = new ListNode(2);
        ListNode nodeTwo = new ListNode(3);
        ListNode nodeThree = new ListNode(4);
        root.next = nodeOne;
        nodeOne.next = nodeTwo;
        nodeTwo.next = nodeThree;

        ListNode result = swapPairs(root);
        System.out.print("Swap Pairs Results: ");
        do {
            System.out.print(result.val);
            result = result.next;
        } while (result != null);
    }

}
