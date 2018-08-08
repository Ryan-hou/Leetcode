package com.github.ryan.personal.data_structure.linkedlist;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className LinkedList
 * 记录了头指针的位置，因此对于头部的操作都是O(1)的
 *
 * @date August 08,2018
 */
public class LinkedList<E> {

    // private Node<E> head;
    // 使用虚拟头节点
    private Node<E> dummyHead;
    private int size;

    public LinkedList() {
//        // 字段赋零值，可以省略
//        head = null;
//        size = 0;
        dummyHead = new Node<>(null, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    // 在链表的index(0-based)位置添加新的节点元素e
    // 不是链表的常规操作，练习用
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed, illegal index.");
        }

        Node prev = dummyHead; // dummyHead即为index=0之前的节点
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        prev.next = new Node<>(e, prev.next);
        size++;
//
//        if (index == 0) {
//            // 头节点单独处理(使用dummy head可以省去)
//            addFirst(e);
//        } else {
//            Node<E> prev = head;
//            for (int i = 0; i < index - 1; i++) {
//                prev = prev.next;
//            }
//
////            Node<E> node = new Node<>(e);
////            node.next = prev.next;
////            prev.next = node;
//            prev.next = new Node<>(e, prev.next);
//            size++;
//        }
    }

    // O(n)
    public void addLast(E e) {
        add(size, e);
    }

    // O(1)
    public void addFirst(E e) {
//        Node<E> node = new Node<>(e);
//        node.next = head;
//        head = node;
//        head = new Node<>(e, head);
//        size++;
        add(0, e);
    }

    // 获得链表第index(0-based)位置的元素
    // 非链表常规操作
    private E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed, illegal index.");
        }

        Node<E> cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size);
    }

    // 修改链表第index(0-based)位置的元素e，非链表常规操作
    private void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed, illegal index.");
        }

        Node<E> cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    public boolean contains(E e) {
        Node<E> cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
//        Node cur = dummyHead.next;
//        while (cur != null) {
//            res.append(cur + "->");
//        }
//        res.append("NULL");

        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            res.append(cur + "->");
        }
        res.append("NULL");
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
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2, 666);
        System.out.println(linkedList);
    }
}
