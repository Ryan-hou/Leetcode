package com.github.ryan.data_structure.stack;

import com.github.ryan.data_structure.linkedlist.LinkedList;

/**
 * @author ryan.houyl@gmail.com
 * @description
 * @className LinkedListStack
 * 使用链表实现栈，链表的头指针对应栈顶
 * push/pop/peek 时间复杂度O(1)
 *
 * @date August 08,2018
 */
public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> list;

    public LinkedListStack() {
        list = new LinkedList<>();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: top ");
        res.append(list);
        return res.toString();
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new LinkedListStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
    }
}
