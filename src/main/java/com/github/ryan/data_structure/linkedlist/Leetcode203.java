package com.github.ryan.data_structure.linkedlist;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className Leetcode203
 * @date August 09,2018
 */
public class Leetcode203 {

    // Remove all elements from a linked list of integers that have value val.
    // Input:  1->2->6->3->4->5->6, val = 6
    // Output: 1->2->3->4->5


    // 法一：使用最直接的链表操作
    public ListNode removeElements(ListNode head, int val) {
        // 要删除一个节点，需要知道该节点的前一个节点
        // 如果当前节点为头节点，不存在前一个节点，需要单独讨论

        while (head != null && head.val == val) {
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        if (head == null) {
            return null;
        }

        // 头节点非待删节点
        ListNode prev = head;
        while (prev.next != null) {

            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
                // 这里prev不需要移动
                // prev = prev.next;
            } else {
                prev = prev.next;
            }
        }
        return head;
    }

    // 法二：构造虚拟头节点
    public ListNode removeElements2(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        // 构造头节点的前一个节点--虚拟节点，因此不需要单独讨论头节点的情况
        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            } else {
                prev = prev.next;
            }
        }
        return dummyHead.next;
    }

    // 法三：使用递归，链表本身也是递归结构
    // 递归方法的语义：删除以head为头节点的链表中所有值为val的节点
    public ListNode removeElements3(ListNode head, int val) {
        // 递归出口--原问题分解后最基本的问题
        if (head == null) return null;

        // 把原问题分解为更小的问题
        head.next = removeElements3(head.next, val);
        // 根据更小的问题的解组织原问题
        return head.val == val ? head.next : head;
    }

    // 通过打印日志可视化递归的调用过程
    public ListNode removeElements3WithPrint(ListNode head, int val, int depth) {
        String depthString = generateDepthString(depth);
        System.out.print(depthString);
        System.out.println("Call: remove " + val + " in " + head);

        if (head == null) {
            System.out.print(depthString);
            System.out.println("Return: " + head);
            return head;
        }

        ListNode res = removeElements3WithPrint(head.next, val, depth + 1);
        System.out.print(depthString);
        System.out.println("After remove " + val + ": " + res);

        ListNode ret;
        if (head.val == val) {
            ret = res;
        } else {
            head.next = res;
            ret = head;
        }

        System.out.print(depthString);
        System.out.println("Return: " + ret);
        return ret;
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println("Initial:" + head);
        System.out.println();

        ListNode res = new Leetcode203().removeElements3WithPrint(head, 6, 0);

        System.out.println();
        System.out.println("Result: " + res);
    }

}
