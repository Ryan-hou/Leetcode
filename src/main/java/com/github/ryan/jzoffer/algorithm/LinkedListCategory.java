package com.github.ryan.jzoffer.algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className LinkedListCategory
 * @date October 09,2018
 */
public class LinkedListCategory {

    private static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
     * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
     * 需要考虑各种可能的情况：
     * 1->2->3
     * 1->2->2
     * 1->2->2->3
     * 2->2
     * 1->2->2->3->3->4
     * 在删除节点时，想清楚待删除节点的前一个节点和删除节点后的下一个节点，以及指针如何往后移动这三件事
     */
    public ListNode deleteDuplication(ListNode pHead) {


        ListNode dummy = new ListNode(-1);
        dummy.next = pHead;
        ListNode prev = dummy; // prev指针为待删除节点的前一个节点
        while (prev.next != null) {

            ListNode cur = prev.next; // 当前待检查的节点
            if (cur.next == null || cur.next.val != cur.val) {
                prev = cur;
            } else {
                // cur.next.val == cur.val
                do {
                    cur = cur.next;
                } while(cur.next != null && cur.next.val == cur.val);

                prev.next = cur.next;
                // prev还不能后移，考虑1->2->2->3->3->4这种情况
            }
        }
        return dummy.next;
    }

    // 输入一个链表，输出该链表中倒数第k个结点
    public ListNode FindKthToTail(ListNode head,int k) {
        // 注意k的合法性
        if (k <= 0) return null;

        // 倒数第k个节点即正数第length-k+1个节点
        // 定义两个指针，第一个指针先走k-1步，然后第二个指针开始与第一个指针一起走
        // 当第一个指针走到最后，第二个指针所在位置即为所求(length-k+1)
        ListNode first = head, second = head;
        for (int i = 1; i < k; i++) {
            if (first == null) return null;
            first = first.next;
        }
        if (first == null) return null;

        while (first.next != null) {
            first = first.next;
            second = second.next;
        }
        return second;
    }

    // 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
    public ListNode Merge(ListNode list1,ListNode list2) {

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        ListNode p = list1, q = list2;
        while (p != null && q != null) {
            if (p.val < q.val) {
                cur.next = p;
                cur = p;
                p = p.next;
            } else {
                cur.next = q;
                cur = q;
                q = q.next;
            }
        }

        while (p != null) {
            cur.next = p;
            cur = p;
            p = p.next;
        }
        while (q != null) {
            cur.next = q;
            cur = q;
            q = q.next;
        }

        return dummy.next;
    }

    // 输入两个链表，找出它们的第一个公共结点
    // 可以借助各种数据结构

    // 法一：暴力解法，遍历第一个链表的每个节点，然后在第二个链表中查找是否存在相同的节点
    // 时间复杂度 O(mn),m,n为两个链表的长度，空间复杂度O(1)

    // 法二：找出2个链表的长度，然后让长的先走两个链表的长度差，然后再一起走（因为2个链表用公共的尾部）
    // 时间复杂度：O(max(m,n)), 空间复杂度O(1)
    public ListNode FindFirstCommonNode2(ListNode pHead1, ListNode pHead2) {

        int plen = listLength(pHead1);
        int qlen = listLength(pHead2);

        ListNode p = pHead1, q = pHead2;
        if (plen < qlen) {
            int gap = qlen - plen;
            while (gap-- > 0) {
                q = q.next;
            }
        } else if (qlen < plen) {
            int gap = plen - qlen;
            while (gap-- > 0) {
                p = p.next;
            }
        } // else 不做处理

        if (p == null || q == null) return null; // corner case
        while (p != q) {
            p = p.next;
            q = q.next;
        }
        return p;

    }

    // 法三：利用栈
    // 两条相交的链表呈Y型。可以从两条链表尾部同时出发，最后一个相同的结点就是链表的第一个相同的结点
    // 时间复杂度有O(max(m,n)), 空间复杂度为O(m + n)
    public ListNode FindFirstCommonNode3(ListNode pHead1, ListNode pHead2) {

        Deque<ListNode> pStack = new ArrayDeque<>();
        while (pHead1 != null) {
            pStack.push(pHead1);
            pHead1 = pHead1.next;
        }
        Deque<ListNode> qStack = new ArrayDeque<>();
        while (pHead2 != null) {
            qStack.push(pHead2);
            pHead2 = pHead2.next;
        }

        ListNode pre = null; // 第一个不重合的节点的前一个节点，即第一个公共节点
        while (!pStack.isEmpty() && !qStack.isEmpty()) {
            if (pStack.peek() == qStack.peek()) {
                pre = pStack.pop();
                qStack.pop();
            } else {
                return pre;
            }
        }
        return pre;
    }

    // 法四：利用哈希表(使用查找表优化暴力解法)
    // 可以采用hash的思想将其中一个转存为哈希表结构，这样建哈希表时间O(m)，
    // 而遍历链表时间O(n)，而遍历时查找哈希表的时间为O(1)，因此复杂度降为O(max(m,n))
    public ListNode FindFirstCommonNode4(ListNode pHead1, ListNode pHead2) {

        Set<ListNode> pSet  = new HashSet<>();
        while (pHead1 != null) {
            pSet.add(pHead1);
            pHead1 = pHead1.next;
        }

        while (pHead2 != null) {
            if (pSet.contains(pHead2)) {
                return pHead2;
            }
            pHead2 = pHead2.next;
        }

        return null;
    }

    // 法五：在法二的基础上改进，比较trick
    // 不需要记录链表长度，通过交换链表头指针把长度的影响消除
    public ListNode FindFirstCommonNode5(ListNode pHead1, ListNode pHead2) {
        ListNode p = pHead1, q = pHead2;
        while (p != q) {
            p = (p == null ? pHead2 : p.next);
            q = (q == null ? pHead1 : q.next);
        }
        return p;
    }

    private int listLength(ListNode node) {
        if (node == null) return 0;

        int length = 0;
        while (node.next != null) {
            length++;
            node = node.next;
        }
        return length;
    }

    /**
     * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList
     */
    // 法一：使用栈
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Deque<Integer> stack = new ArrayDeque<>();
        ListNode cur = listNode;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }

        ArrayList<Integer> res = new ArrayList<>(stack.size());
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
        return res;
    }

    // 法二：反转单链表，然后遍历
    // 法三：直接遍历链表，然后reverse结果

    // 法四：使用递归(和树一样，链表也是递归的结构)
    ArrayList<Integer> res = new ArrayList<>();
    public ArrayList<Integer> printListFromTailToHead4(ListNode listNode) {
        if (listNode == null) return res;
        printListFromTailToHead(listNode.next);
        res.add(listNode.val);
        return res;
    }


}
