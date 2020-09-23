package com.github.ryan.algorithm.leetcode.medium.medium2;

/**
 * @author Ryan-hou
 * @description:
 * @className: Solution
 * @date February 27,2017
 */
public class Solution {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * 法一:
     * 比较直接,把链表转为数字,相加后再转为链表,但是注意,链表转为数字相加时int类型会溢出,
     * 需要使用 BigInteger 类,效率比较低
     */

    /**
     * 法二:
     * 从低位到高位,一位一位的处理链表的数据,得到每一位的数字后,用尾插法插入的结果链表中
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null,  node = null;
        int carry = 0, remainder = 0, sum = 0;
        head = node = new ListNode(0);

        while (l1 != null || l2 != null || carry != 0) {
            sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
            carry = sum / 10;
            remainder = sum % 10;
            node = node.next = new ListNode(remainder); // 尾插法建立单链表
            l1 = (l1 != null ? l1.next : null);
            l2 = (l2 != null ? l2.next : null);
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        ListNode l11 = new ListNode(4);
        ListNode l111 = new ListNode(3);
        l1.next = l11; l11.next = l111;

        ListNode l2 = new ListNode(5);
        ListNode l22 = new ListNode(6);
        ListNode l222 = new ListNode(4);
        l2.next = l22; l22.next = l222;

        ListNode result = addTwoNumbers(l1, l2);
        while (result != null) {
            System.out.print(result.val);
            result = result.next;
        }
    }
}
