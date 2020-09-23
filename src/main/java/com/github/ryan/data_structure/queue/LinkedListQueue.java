package com.github.ryan.data_structure.queue;

import java.util.NoSuchElementException;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className LinkedListQueue
 * 使用链表实现queue，
 * head节点删除元素(O(1)) --> 队首出队
 * tail节点插入元素(O(1)) --> 队尾入队
 *
 * @date August 08,2018
 */
public class LinkedListQueue<E> implements Queue<E> {

    // 不使用dummy head，注意判断队列为空的情形(head==null,tail==null)
    private Node<E> head, tail;
    private int size;

    public LinkedListQueue() {
        // 字段赋零值，可以省略
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // 队尾入队-->操作尾节点
    @Override
    public void offer(E e) {
        if (tail == null) {
            tail = new Node<>(e);
            head = tail;
        } else {
            tail.next = new Node<>(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            throw new NoSuchElementException("Can't deque from an empty queue.");
        }

        Node<E> retNode = head;
        head = head.next;
        retNode.next = null;
        // 队列只有一个元素的情景
        if (head == null) {
            tail = null;
        }
        size--;
        return retNode.e;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Can't deque from an empty queue.");
        }
        return head.e;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");

        Node cur = head;
        while (cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL tail");
        return res.toString();
    }

    // 私有静态成员内部类：用来表示外围类所代表对象的组件
    private static class Node<E> {

        E e;
        Node next;

        Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        Node(E e) {
            this(e, null);
        }

        Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    public static void main(String[] args) {

        Queue<Integer> queue = new LinkedListQueue<>();

        for (int i = 0; i < 10; i++) {
            queue.offer(i);
            System.out.println(queue);

            if (i % 3 == 2) {
                queue.poll();
                System.out.println(queue);
            }
        }
    }
}
