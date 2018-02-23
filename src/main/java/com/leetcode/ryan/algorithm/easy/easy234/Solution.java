package com.leetcode.ryan.algorithm.easy.easy234;

import com.leetcode.ryan.personal.component.ListNode;
import com.leetcode.ryan.personal.util.LinkedListUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author ryan.houyl@gmail.com
 * @description:
 * @className: Solution
 * @date February 23,2018
 */
@Slf4j
public class Solution {


    /**
     * 把链表数据转存到数组，在数组中使用对撞指针来判断
     *
     * 时间复杂度：O(n)
     * 空间复杂度: O(n)
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {

        if (head == null) return true;

        // ----  把链表数据转存到数组 --------
        int length = 0;
        ListNode cur = head;
        while (cur != null) {
            length += 1;
            cur = cur.next;
        }

        int[] nodes = new int[length];
        cur = head;
        int i = 0;
        while (cur != null) {
            nodes[i++] = cur.val;
            cur = cur.next;
        }

        // 使用数组判断 palindrome
        int l = 0, r = length - 1;
        while (l < r) {
            if (nodes[l] != nodes[r]) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }


    /**
     * 题目没有要求传入的链表不允许修改，所以为了空间复杂度降为O(1)，修改链表结构
     *
     * 思路：
     * 通过快慢指针找到链表的中间位置，然后反转中间位置之后的链表
     * 根据palindrome的性质，反转后链表数据和链表的前半部分一致(对称)
     * @param head
     * @return
     */
    public static boolean isPalindrome2(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast == null) {
            slow = slow.next;
        }

        slow = reverse(slow);
        while (slow != null && head.val == slow.val) {
            head = head.next;
            slow = slow.next;
        }

        return slow == null;
    }

    private static ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        int[] nodes = {2, 3, 4, 5, 5, 4, 3, 2};
        ListNode list = LinkedListUtil.createLinkedList(nodes, nodes.length);
        log.info("Node = {} is palindrome? {}", Arrays.toString(nodes), isPalindrome2(list));

    }
}
