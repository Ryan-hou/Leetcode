package com.github.ryan.personal.algorithm.component;


/**
 * @author ryan.houyl@gmail.com
 * @description: Definition for singly-linked list
 * @className: ListNode
 * @date January 26,2018
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    /**
     * 新增构造链表的构造器，方便进行测试
     * 推荐使用工具类的方法：
     * {@link com.github.ryan.personal.algorithm.util.LinkedListUtil}
     */

    // 链表节点的构造函数
    // 使用arr为参数，构建一个链表，当前的ListNode为链表的头节点
//    public ListNode(int[] arr) {
//        if (arr == null || arr.length == 0) {
//            throw new IllegalArgumentException("arr can't be empty");
//        }
//
//        this.val = arr[0];
//        ListNode cur = this;
//        for (int i = 1; i < arr.length; i++) {
//            cur.next = new ListNode(arr[i]);
//            cur = cur.next;
//        }
//    }

    // 以当前节点为头节点的链表信息字符串
//    @Override
//    public String toString() {
//        StringBuilder res = new StringBuilder();
//        ListNode cur = this;
//        while (cur != null) {
//            res.append(cur.val + "->");
//            cur = cur.next;
//        }
//        res.append("NULL");
//        return res.toString();
//    }
}
